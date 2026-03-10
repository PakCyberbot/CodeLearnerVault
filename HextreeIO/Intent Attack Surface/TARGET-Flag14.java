package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.R;
import io.hextree.attacksurface.SolvedPreferences;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/* loaded from: classes.dex */
public class Flag14Activity extends AppCompactActivity {
    public Flag14Activity() {
        this.name = "Flag 14 - Hijack web login";
        this.tag = "Deeplink";
        this.tagColor = R.color.pink;
        this.flag = "0iax/2Bz8vH9y4lpU6d2NJLnJfpjdLNAE9Cxd1JHHnuWseytzTwR70LTxH7bD0Pp";
        this.hideIntentDialog = true;
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws NoSuchAlgorithmException {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        if (intent.getAction() == null) {
            Log.i("Hextree", "browser intent");
            Intent intent2 = new Intent("android.intent.action.VIEW");
            String string = UUID.randomUUID().toString();
            SolvedPreferences.putString(getPrefixKey("challenge"), string);
            intent2.setData(Uri.parse("https://ht-api-mocks-lcfc4kr5oa-uc.a.run.app/android-app-auth?authChallenge=" + string));
            startActivity(intent2);
            return;
        }
        if (intent.getAction().equals("android.intent.action.VIEW")) {
            Uri data = intent.getData();
            String queryParameter = data.getQueryParameter("type");
            String queryParameter2 = data.getQueryParameter("authToken");
            String queryParameter3 = data.getQueryParameter("authChallenge");
            String string2 = SolvedPreferences.getString(getPrefixKey("challenge"));
            if (queryParameter == null || queryParameter2 == null || queryParameter3 == null || !queryParameter3.equals(string2)) {
                Toast.makeText(this, "Invalid login", 1).show();
                finish();
                return;
            }
            this.f.addTag(queryParameter);
            try {
                String strEncodeToString = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(queryParameter2.getBytes()));
                if (strEncodeToString.equals("a/AR9b0XxHEX7zrjx5KNOENTqbsPi6IsX+MijDA/92w=")) {
                    if (queryParameter.equals("user")) {
                        Toast.makeText(this, "User login successful", 1).show();
                    } else if (queryParameter.equals("admin")) {
                        Log.i("Flag14", "hash: " + strEncodeToString);
                        this.f.addTag(queryParameter2);
                        Toast.makeText(this, "Admin login successful", 1).show();
                        success(this);
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}