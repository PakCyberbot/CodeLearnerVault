package io.hextree.attacksurface.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;

/* loaded from: classes.dex */
public class Flag23Activity extends AppCompactActivity {
    public Flag23Activity() {
        this.name = "Flag 23 - Hijack pending intent";
        this.tag = "PendingIntent";
        this.tagColor = R.color.red;
        this.flag = "6GqvZ4aOjqNSTlaifb21GPQlHbYwe6S/PkRgfYdLod0Q+Y7QTu55zAs3eixaRA6V";
        this.allowOpenActivity = true;
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action == null) {
            Toast.makeText(this, "Sending implicit intent with the flag\nio.hextree.attacksurface.MUTATE_ME", 1).show();
            Intent intent2 = new Intent("io.hextree.attacksurface.GIVE_FLAG");
            intent2.setClassName(getPackageName(), Flag23Activity.class.getCanonicalName());
            PendingIntent activity = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 33554432);
            Intent intent3 = new Intent("io.hextree.attacksurface.MUTATE_ME");
            intent3.addFlags(8);
            intent3.putExtra("pending_intent", activity);
            this.f.addTag(intent3);
            Log.i("Flag22Activity", intent3.toString());
            startActivity(intent3);
            return;
        }
        if (action.equals("io.hextree.attacksurface.GIVE_FLAG")) {
            this.f.addTag(action);
            if (intent.getIntExtra("code", -1) == 42) {
                this.f.addTag(42);
                success(this);
            } else {
                Toast.makeText(this, "Condition not met for flag", 0).show();
            }
        }
    }
}