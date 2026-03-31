package io.hextree.attacksurface.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;

/* loaded from: classes.dex */
public class Flag21Activity extends AppCompactActivity {
    public static String GIVE_FLAG = "io.hextree.broadcast.GIVE_FLAG";

    public Flag21Activity() {
        this.name = "Flag 21 - Hijack notification button";
        this.flag = "jUBJFmCQFOUsDC+hBOLu3D4rz7J74vUO3rEXLkH+FAygUFFjzpUS7avqDiQnD8Dy";
        this.tag = "BroadcastReceiver";
        this.tagColor = R.color.green;
        this.hideIntentDialog = true;
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_ID", "Hextree Notifications", 3);
        notificationChannel.setDescription("Notifications related to various intent attack surface features. Probably good idea to reverse engineer this notification.");
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        createNotificationChannel();
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        if (getIntent() == null) {
            return;
        }
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: io.hextree.attacksurface.activities.Flag21Activity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String resultData = getResultData();
                Bundle resultExtras = getResultExtras(false);
                int resultCode = getResultCode();
                Log.i("Flag18Activity.BroadcastReceiver", "resultData " + resultData);
                Log.i("Flag18Activity.BroadcastReceiver", "resultExtras " + resultExtras);
                Log.i("Flag18Activity.BroadcastReceiver", "resultCode " + resultCode);
                Toast.makeText(context, "Check the broadcast intent for the flag", 0).show();
                Flag21Activity flag21Activity = Flag21Activity.this;
                flag21Activity.success(null, flag21Activity);
            }
        };
        this.f.addTag(GIVE_FLAG);
        IntentFilter intentFilter = new IntentFilter(GIVE_FLAG);
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(broadcastReceiver, intentFilter, 2);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }
        Intent intent = new Intent(GIVE_FLAG);
        intent.putExtra("flag", this.f.appendLog(this.flag));
        NotificationCompat.Builder builderAddAction = new NotificationCompat.Builder(this, "CHANNEL_ID").setSmallIcon(R.drawable.hextree_logo).setContentTitle(this.name).setContentText("Reverse engineer classes Flag21Activity").setPriority(0).setAutoCancel(true).addAction(R.drawable.hextree_logo, "Give Flag", PendingIntent.getBroadcast(this, 0, intent, 201326592));
        if (ActivityCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") != 0) {
            if (Build.VERSION.SDK_INT >= 33) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
            }
        } else {
            NotificationManagerCompat.from(this).notify(1, builderAddAction.build());
            Toast.makeText(this, "Check your notifications", 0).show();
        }
    }
}