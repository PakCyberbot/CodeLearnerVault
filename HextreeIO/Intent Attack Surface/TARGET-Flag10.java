package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;

/* loaded from: classes.dex */
public class Flag10Activity extends AppCompactActivity {
    public Flag10Activity() {
        this.name = "Flag 10 - Hijack implicit intent with the flag";
        this.tag = "ImplicitIntent";
        this.tagColor = R.color.red;
        this.flag = "qq51kWPLVous73Vn3R6HuU1897f/Nq8tGvdjpJ7GQRW9/s9oCLN5lr9hjvVIHyUf";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        if (getIntent().getAction() == null) {
            Toast.makeText(this, "Sending implicit intent with the flag\nio.hextree.attacksurface.ATTACK_ME", 1).show();
            Intent intent = new Intent("io.hextree.attacksurface.ATTACK_ME");
            intent.addFlags(8);
            this.f.addTag(intent);
            intent.putExtra("flag", this.f.appendLog(this.flag));
            try {
                startActivity(intent);
                success(this);
            } catch (RuntimeException e) {
                e.printStackTrace();
                Toast.makeText(this, "No app found to handle the intent\nio.hextree.attacksurface.ATTACK_ME", 1).show();
                finish();
            }
        }
    }
}