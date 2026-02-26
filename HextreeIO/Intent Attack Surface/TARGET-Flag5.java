package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.os.Bundle;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
// INTENT Inside an Intent Challenge
/* loaded from: classes.dex */
public class Flag5Activity extends AppCompactActivity {
    Intent nextIntent = null;

    public Flag5Activity() {
        this.name = "Flag 5 - Intent in intent";
        this.flag = "Ffhbk756vZdA32t/W7TJh/fG5b4BX8d4VINhZXi5pKioI+oVxQcLW+LIe+qyVYdT";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        Intent intent = getIntent();
        Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
        if (intent2 == null || intent2.getIntExtra("return", -1) != 42) {
            return;
        }
        this.f.addTag(42);
        Intent intent3 = (Intent) intent2.getParcelableExtra("nextIntent");
        this.nextIntent = intent3;
        if (intent3 == null || intent3.getStringExtra("reason") == null) {
            return;
        }
        this.f.addTag("nextIntent");
        if (this.nextIntent.getStringExtra("reason").equals("back")) {
            this.f.addTag(this.nextIntent.getStringExtra("reason"));
            success(this);
        } else if (this.nextIntent.getStringExtra("reason").equals("next")) {
            intent.replaceExtras(new Bundle());
            startActivity(this.nextIntent);
        }
    }
}