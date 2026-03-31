package io.hextree.attacksurface.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.Utils;
import io.hextree.attacksurface.activities.Flag17Activity;

/* loaded from: classes.dex */
public class Flag17Receiver extends BroadcastReceiver {
    public static String FlagSecret = "give-flag-17";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("Flag17Receiver.onReceive", Utils.dumpIntent(context, intent));
        if (isOrderedBroadcast()) {
            if (intent.getStringExtra("flag").equals(FlagSecret)) {
                success(context, FlagSecret);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("success", false);
            setResult(0, "Flag 17 Completed", bundle);
        }
    }

    private void success(Context context, String str) {
        Flag17Activity flag17Activity = new Flag17Activity();
        flag17Activity.f = new LogHelper(context);
        flag17Activity.f.addTag(str);
        flag17Activity.success(null, context);
        Bundle bundle = new Bundle();
        bundle.putBoolean("success", true);
        bundle.putString("flag", flag17Activity.f.appendLog(flag17Activity.flag));
        setResult(-1, "Flag 17 Completed", bundle);
    }
}