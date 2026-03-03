package com.example.practicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

//        Register an intent-filter in AndroidManifest.xml
//        Create an Activity to receive and process the intent

        // get the incoming intent
        Intent intent = getIntent();
        if (intent != null &&
                "io.hextree.attacksurface.ATTACK_ME".equals(intent.getAction())) {

            intent.putExtra("token",1094795585);
            setResult(1337,intent);

        }

        finish();
    }

}
