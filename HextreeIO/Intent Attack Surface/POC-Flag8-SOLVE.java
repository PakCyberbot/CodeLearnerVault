package com.example.practicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityHextree extends AppCompatActivity {
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
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag8Activity");

            startActivityForResult(intent,1337);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
