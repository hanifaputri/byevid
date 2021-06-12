
package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen2 extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        setOnboardingItems();

        ViewPager2 onboardingViiewPager = findViewById(R.id.vp_slidevp);
        onboardingViiewPager.setAdapter(onboardingAdapter);
    }

    private void setOnboardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemOnboardSlide1 = new OnboardingItem();
        itemOnboardSlide1.setHeading("Statistik COVID-19");
        itemOnboardSlide1.setDesc("Pantau terus perkembangan pandemi COVID-19 di areamu");
        itemOnboardSlide1.setImg(R.drawable.img_splashscreen_1);

        OnboardingItem itemOnboardSlide2 = new OnboardingItem();
        itemOnboardSlide2.setHeading("Rumah Sakit Rujukan COVID-19");
        itemOnboardSlide2.setDesc("Kami akan membantu Anda menemukan rumah sakit rujukan COVID-19 di sekitar Anda.");
        itemOnboardSlide2.setImg(R.drawable.img_splashscreen_2);

        OnboardingItem itemOnboardSlide3 = new OnboardingItem();
        itemOnboardSlide3.setHeading("Artikel dan Berita");
        itemOnboardSlide3.setDesc("Lawan hoax! Kami akan menyajikan artikel dan berita terbaru yang akurat selama masa pandemi COVID-19");
        itemOnboardSlide3.setImg(R.drawable.img_splashscreen_3);

        onboardingItems.add(itemOnboardSlide1);
        onboardingItems.add(itemOnboardSlide2);
        onboardingItems.add(itemOnboardSlide3);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

}