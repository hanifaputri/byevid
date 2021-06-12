package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreen3 extends AppCompatActivity {

    private Button btn_skip, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen3);

        btn_skip = findViewById(R.id.btn_skip_onboarding3);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
                overridePendingTransition(0,0);
            }
        });

        btn_next = findViewById(R.id.btn_next_onboarding3);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SplashScreen4.class));
                finish();
                overridePendingTransition(0,0);
            }
        });
    }
}
