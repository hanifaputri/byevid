package com.example.byevid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.byevid.adapter.ArticleAdapter;
import com.example.byevid.model.Article;
import com.example.byevid.utils.SpacesItemDecoration;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private ArrayList<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        // Navigation bar
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navbar);
        bottomNav.setSelectedItemId(R.id.navbar_article);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navbar_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_hospital:
                        startActivity(new Intent(getApplicationContext(), HospitalActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navbar_article:
                        break;
                    case R.id.navbar_account:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        overridePendingTransition(0,0);
                        break;
                }
                return true;
            }
        });

        // Initialize data
        articleList = new ArrayList<>();
        addData();

        // Configure recycler view
        recyclerView = (RecyclerView) findViewById(R.id.rv_article_list);
        adapter = new ArticleAdapter(this, articleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemViewCacheSize(20);

        // Set column number
        int col = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, col));
        recyclerView.addItemDecoration(new SpacesItemDecoration(16, col));
    }
    
    private void addData() {
        articleList.add(new Article("Seberapa Pentingkah Vaksinasi?", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/covid_vaccine.png?alt=media&token=abb4f899-b17a-426c-b24a-28ee119fb5e4","http://google.com/"));
        articleList.add(new Article("Penerapan Social Distancing", "6", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/social-distancing.jpeg?alt=media&token=475933d2-aebd-427d-b0f3-77468ea625db","http://google.com/"));
        articleList.add(new Article("Tips WFH Selama Masa Pandemi", "5", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/work-from-home.jpeg?alt=media&token=9a135864-3b0d-4181-8ae8-1e394e81a784","http://google.com/"));
        articleList.add(new Article("Jenis Masker Anti COVID-19 yang Efektif", "6", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/kids-medical-mask.jpeg?alt=media&token=ec14c6ea-92f1-4362-99e0-97ab2ac37916","http://google.com/"));
        articleList.add(new Article("Cara Mencuci Tangan yang Efektif", "4", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/wash-hands.png?alt=media&token=9623d69d-8ddd-44db-b427-8864c076413a","http://google.com/"));
        articleList.add(new Article("Tips Pintar Hindari Bakteri dan Virus", "5", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/fight-viruses.png?alt=media&token=2a3b093c-935d-4b26-943b-701f144c6266","http://google.com/"));
        articleList.add(new Article("Perkembangan Mutasi Virus COVID-19", "4", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/covid-mutation.png?alt=media&token=31454e56-f06f-400e-8fde-767bb5be88b6","http://google.com/"));
        articleList.add(new Article("Yuk Kenali Jenis Vaksin di Indonesia", "3", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/vaccine-type.png?alt=media&token=a7385316-d5c9-4d04-9a83-fd3b663d2f79","http://google.com/"));
    }
}