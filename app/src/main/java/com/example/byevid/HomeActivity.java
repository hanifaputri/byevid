package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byevid.model.Statistic;
import com.example.byevid.network.ApiService;
import com.example.byevid.utils.CustomProgressDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "Home Activity";

    private TextView tv_positive, tv_recovered, tv_dead, tv_name;
    private LinearLayout btn_dial, btn_consult;
    private ImageView btn_check;
    private CustomProgressDialog dialog_loading;

    // Firebase instance
    private FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Navigation bar
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navbar);
        bottomNav.setSelectedItemId(R.id.navbar_home);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navbar_home:
                        break;
                    case R.id.navbar_hospital:
                        startActivity(new Intent(getApplicationContext(), HospitalActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_article:
                        startActivity(new Intent(getApplicationContext(), ArticleActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_account:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
                return true;
            }
        });

        // Get firebase instance
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        // Set name
        tv_name = (TextView) findViewById(R.id.tv_home_name);
        if (fUser != null) {
            for (UserInfo profile : fUser.getProviderData()) {
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                // If name not empty
                if (!name.isEmpty()) {
                    String arr[] = name.split(" ", 2);
                    String firstName = arr[0];
                    tv_name.setText("Hi, " + firstName.trim() + "!");
                } else {
                    tv_name.setText("Hi, User!");
                }
            }
        }

        // Show dialog
        dialog_loading = new CustomProgressDialog(this, R.layout.dialog_loading);
        dialog_loading.show();

        tv_positive = findViewById(R.id.tv_home_stat_positive);
        tv_recovered = findViewById(R.id.tv_home_stat_recovered);
        tv_dead = findViewById(R.id.tv_home_stat_death);

        btn_consult = findViewById(R.id.btn_home_consult);
        btn_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Konsultasi", Toast.LENGTH_SHORT).show();

            }
        });

        btn_dial = findViewById(R.id.btn_home_hotline);
        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:119"));
                startActivity(call);
            }
        });

        btn_check = findViewById(R.id.btn_home_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.halodoc.com/tanya-jawab-seputar-virus-corona/"));
                startActivity(browserIntent);
            }
        });

        // Fetch data from API
        getDataFromApi();
    }

    private void getDataFromApi() {
        ApiService.endpoint().getData()
            .enqueue(new Callback<List<Statistic>>() {
                @Override
                public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {
                    Log.d(TAG, response.toString());
                    if (response.isSuccessful()) {
                        List<Statistic> results = response.body();
                        showResult(results);
                        Log.d(TAG, results.toString());

                        // Hide loading dialog
                        dialog_loading.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<Statistic>> call, Throwable t) {
                    Log.d(TAG, t.toString());
                }
            });
    }

    private void showResult(List<Statistic> statModel) {
        Statistic result = statModel.get(0);
        tv_positive.setText(result.getPositif());
        tv_recovered.setText(result.getSembuh());
        tv_dead.setText(result.getMeninggal());
    }

}