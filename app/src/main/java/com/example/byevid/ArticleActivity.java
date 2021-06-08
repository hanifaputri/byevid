package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.byevid.adapter.ArticleAdapter;
import com.example.byevid.model.Article;
import com.example.byevid.utils.SpacesItemDecoration;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private ArrayList<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

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
        articleList.add(new Article("Penerapan Social Distancing", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/social-distancing.jpeg?alt=media&token=475933d2-aebd-427d-b0f3-77468ea625db","http://google.com/"));
        articleList.add(new Article("Tips WFH Selama Masa Pandemi", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/work-from-home.jpeg?alt=media&token=9a135864-3b0d-4181-8ae8-1e394e81a784","http://google.com/"));
        articleList.add(new Article("Jenis Masker Anti COVID-19 yang Efektif", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/kids-medical-mask.jpeg?alt=media&token=ec14c6ea-92f1-4362-99e0-97ab2ac37916","http://google.com/"));
        articleList.add(new Article("Seberapa Pentingkah Vaksinasi?", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/covid_vaccine.png?alt=media&token=abb4f899-b17a-426c-b24a-28ee119fb5e4","http://google.com/"));
        articleList.add(new Article("Penerapan Social Distancing", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/social-distancing.jpeg?alt=media&token=475933d2-aebd-427d-b0f3-77468ea625db","http://google.com/"));
        articleList.add(new Article("Tips WFH Selama Masa Pandemi", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/work-from-home.jpeg?alt=media&token=9a135864-3b0d-4181-8ae8-1e394e81a784","http://google.com/"));
        articleList.add(new Article("Jenis Masker Anti COVID-19 yang Efektif", "7", "https://firebasestorage.googleapis.com/v0/b/byevid-1cb2e.appspot.com/o/kids-medical-mask.jpeg?alt=media&token=ec14c6ea-92f1-4362-99e0-97ab2ac37916","http://google.com/"));
    }
}