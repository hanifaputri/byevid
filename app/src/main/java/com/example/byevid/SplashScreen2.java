
package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen2 extends AppCompatActivity {

    private Button btn_skip, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

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