package io.hextree.attacksurface.activities;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;

/* loaded from: classes.dex */
public class Flag8Activity extends AppCompactActivity {
    public Flag8Activity() {
        this.name = "Flag 8 - Do you expect a result?";
        this.tag = "ActivityResult";
        this.flag = "SswwbqGWnA950TVWt2lccPUGxr4PyWorpunFllh8DOY=";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Process", "id in stealing flag 8: " + Process.myPid());
        this.f = new LogHelper(this);
        ComponentName callingActivity = getCallingActivity();
        if (callingActivity != null) {
            if (callingActivity.getClassName().contains("Hextree")) {
                this.f.addTag("calling class contains 'Hextree'");
                success(this);
            } else {
                Log.i("Flag8", "access denied");
                setResult(0, getIntent());
            }
        }
    }
}