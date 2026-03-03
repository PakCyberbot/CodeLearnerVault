package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;

/* loaded from: classes.dex */
public class Flag11Activity extends AppCompactActivity {
    public Flag11Activity() {
        this.name = "Flag 11 - Respond to implicit intent";
        this.tag = "ImplicitIntent";
        this.flag = "OO4jpSDTSrgJ9c+o3AXXL+awhn5K0bqUYrQwJA870/c=";
        this.tagColor = R.color.red;
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
        if (intent != null && intent.getIntExtra("token", -1) == 1094795585) {
            this.f.addTag(1094795585);
            success(this);
        }
        super.onActivityResult(i, i2, intent);
    }
}