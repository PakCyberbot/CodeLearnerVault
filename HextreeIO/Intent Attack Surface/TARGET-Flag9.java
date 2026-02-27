package io.hextree.attacksurface.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;

/* loaded from: classes.dex */
public class Flag9Activity extends AppCompactActivity {
    public Flag9Activity() {
        this.name = "Flag 9 - Receive result with flag";
        this.tag = "ActivityResult";
        this.flag = "dNI5JoHkAhFhZffA0i8PMsFGHGD1cN2NG6NXmmslJhQ=";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        ComponentName callingActivity = getCallingActivity();
        if (callingActivity == null || !callingActivity.getClassName().contains("Hextree")) {
            return;
        }
        Intent intent = new Intent("flag");
        this.f.addTag(intent);
        this.f.addTag(42);
        intent.putExtra("flag", this.f.appendLog(this.flag));
        setResult(-1, intent);
        finish();
        success(this);
    }
}