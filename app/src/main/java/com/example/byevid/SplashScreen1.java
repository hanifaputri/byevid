package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menghilangkan Action Bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen1);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable () {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), SplashScreen2.class));
                finish();
            }
        }, 2500L); // Delay Splash Screen selama 2 detik

    }
}