package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen2 extends AppCompatActivity {

    private Button btn_skip, btn_next;
    // Firebase
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        // Get firebase instance
        fAuth = FirebaseAuth.getInstance();
        // Redirect if user authenticated
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        btn_skip = findViewById(R.id.btn_skip_onboarding2);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
                overridePendingTransition(0,0);
            }
        });

        btn_next = findViewById(R.id.btn_next_onboarding2);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SplashScreen3.class));
                finish();
                overridePendingTransition(0,0);
            }
        });
    }

}
