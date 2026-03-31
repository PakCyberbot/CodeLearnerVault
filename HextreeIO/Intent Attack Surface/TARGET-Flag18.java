package io.hextree.attacksurface.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;
import io.hextree.attacksurface.Utils;

/* loaded from: classes.dex */
public class Flag18Activity extends AppCompactActivity {
    public static String SECRET_FLAG = "giving-out-flags";

    public Flag18Activity() {
        this.name = "Flag 18 - Hijack broadcast intent";
        this.flag = "Ngjci4SC/yCeicKQWURFppzwSKDfWLZdx/WPnrBclZ8Ilkh1Qd4bVh+n9nigu1CU";
        this.tag = "BroadcastReceiver";
        this.tagColor = R.color.green;
        this.hideIntentDialog = true;
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        this.f.addTag(SECRET_FLAG);
        Intent intent = new Intent("io.hextree.broadcast.FREE_FLAG");
        intent.putExtra("flag", this.f.appendLog(this.flag));
        intent.addFlags(8);
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() { // from class: io.hextree.attacksurface.activities.Flag18Activity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent2) {
                String resultData = getResultData();
                Bundle resultExtras = getResultExtras(false);
                int resultCode = getResultCode();
                Log.i("Flag18Activity.BroadcastReceiver", "resultData " + resultData);
                Log.i("Flag18Activity.BroadcastReceiver", "resultExtras " + resultExtras);
                Log.i("Flag18Activity.BroadcastReceiver", "resultCode " + resultCode);
                if (resultCode != 0) {
                    Utils.showIntentDialog(context, "BroadcastReceiver.onReceive", intent2);
                    Flag18Activity flag18Activity = Flag18Activity.this;
                    flag18Activity.success(flag18Activity);
                }
            }
        }, null, 0, null, null);
        Toast.makeText(this, "Sent a broadcast io.hextree.broadcast.FREE_FLAG", 0).show();
    }
}