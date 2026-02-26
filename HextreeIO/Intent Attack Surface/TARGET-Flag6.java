package io.hextree.attacksurface.activities;

import android.os.Bundle;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;

/* loaded from: classes.dex */
public class Flag6Activity extends AppCompactActivity {
    public Flag6Activity() {
        this.name = "Flag 6 - Not exported";
        this.flag = "EUAgD7RzQhmEAesUPyoidyGIG4cEEOUr6irkQ0gFgQzdIerE00zPBmWMfCP+Ptx9";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        if ((getIntent().getFlags() & 1) != 0) {
            this.f.addTag("FLAG_GRANT_READ_URI_PERMISSION");
            success(this);
        }
    }
}