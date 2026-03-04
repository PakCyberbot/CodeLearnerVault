package io.hextree.attacksurface.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;

/* loaded from: classes.dex */
public class Flag22Activity extends AppCompactActivity {
    public Flag22Activity() {
        this.name = "Flag 22 - Receive pending intent";
        this.tag = "PendingIntent";
        this.tagColor = R.color.red;
        this.flag = "TOVqB4voTI5Mo5VFynHunFNx6P1SCzoVLvEk5KvxFF7XMChBKfo2o5SwfeEicTd7";
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws PendingIntent.CanceledException {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra("PENDING");
        if (pendingIntent != null) {
            try {
                Intent intent = new Intent();
                intent.getExtras();
                intent.putExtra("success", true);
                this.f.addTag(intent);
                intent.putExtra("flag", this.f.appendLog(this.flag));
                pendingIntent.send(this, 0, intent);
                success(null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}