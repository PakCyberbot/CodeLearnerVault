package com.example.practicapp;

import android.app.PendingIntent;
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

        // get the incoming intent
        Intent receivedIntent = getIntent();
        if(receivedIntent != null) {
            try {
                PendingIntent pendingIntent = getIntent().getParcelableExtra("pending_intent");
                Intent intent = new Intent("io.hextree.attacksurface.GIVE_FLAG");
                intent.putExtra("code",42);
                pendingIntent.send(this,0,intent);
            }catch (Exception e){

            }
        }
        
    }

}
