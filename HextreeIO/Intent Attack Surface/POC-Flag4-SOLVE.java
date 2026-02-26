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
            switch (counting) {

                case 0:
                    launch("PREPARE_ACTION");
                    counting++;
                    break;

                case 1:
                    launch("BUILD_ACTION");
                    counting++;
                    break;

                case 2:
                    launch("GET_FLAG_ACTION");
                    counting++;
                    break;

                case 3:
                    // Final launch triggers success()
                    launch(null);
                    counting = 0; // reset for reuse
                    break;
            }

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
