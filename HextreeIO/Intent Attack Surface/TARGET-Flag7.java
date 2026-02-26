package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.os.Bundle;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;

/* loaded from: classes.dex */
public class Flag7Activity extends AppCompactActivity {
    public Flag7Activity() {
        this.name = "Flag 7 - Activity lifecycle tricks";
        this.flag = "t1jJ5eZC0pLG9TFJ/Hby3fgIBlDXrSYK7R/Im8CN1kgNNnc6zESDotCzuudZxZPN";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.f == null) {
            this.f = new LogHelper(this);
        }
        String action = getIntent().getAction();
        if (action == null || !action.equals("OPEN")) {
            return;
        }
        this.f.addTag("OPEN");
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        if (action == null || !action.equals("REOPEN")) {
            return;
        }
        this.f.addTag("REOPEN");
        success(this);
    }
}