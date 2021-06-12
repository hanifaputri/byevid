
package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.slider.Slider;

public class SplashScreen2 extends AppCompatActivity {

    private ViewPager vp_slide;
    private LinearLayout ll_mdot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        vp_slide = findViewById(R.id.vp_slidevp);
    }

}