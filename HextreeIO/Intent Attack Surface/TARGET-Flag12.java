package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;

/* loaded from: classes.dex */
public class Flag12Activity extends AppCompactActivity {
    public Flag12Activity() {
        this.name = "Flag 12 - Careful intent conditions";
        this.tag = "ImplicitIntent";
        this.tagColor = R.color.red;
        this.flag = "7fMhFI6jFIsxEigigrq3J4VQVhhj9PL1V8LbUriEMssqCIUTj/Ynrsn4fvVXZJle";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        if (getIntent().getAction() == null) {
            Toast.makeText(this, "Sending implicit intent to\nio.hextree.attacksurface.ATTACK_ME", 1).show();
            Intent intent = new Intent("io.hextree.attacksurface.ATTACK_ME");
            intent.addFlags(8);
            try {
                startActivityForResult(intent, 42);
            } catch (RuntimeException e) {
                e.printStackTrace();
                Toast.makeText(this, "No app found to handle the intent\nio.hextree.attacksurface.ATTACK_ME", 1).show();
                finish();
            }
        }
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null || getIntent() == null || !getIntent().getBooleanExtra("LOGIN", false)) {
            return;
        }
        this.f.addTag("LOGIN");
        if (intent.getIntExtra("token", -1) == 1094795585) {
            this.f.addTag(1094795585);
            success(this);
        }
    }
}