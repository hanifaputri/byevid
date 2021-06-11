package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.byevid.model.Article;

public class ArticleDetailActivity extends AppCompatActivity {
    private final static String TAG = "Article Detail Activity";
    private Article data;

    private TextView tv_content, tv_title, tv_duration;
    private ImageView img_thumb, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        tv_title = (TextView) findViewById(R.id.tv_article_detail_title);
        tv_duration = (TextView) findViewById(R.id.tv_article_detail_duration);
        img_thumb = (ImageView) findViewById(R.id.img_article_detail_thumb);

        // Retrieve intent object
        getIntentData();

        tv_content = (TextView) findViewById(R.id.tv_article_detail_content);
        tv_content.setText(Html.fromHtml(getString(R.string.article_detail)));

        btn_back = (ImageView) findViewById(R.id.btn_article_detail_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentData() {
        if (true) {
            data = (Article) getIntent().getSerializableExtra("Data");

            tv_title.setText(data.getTitle());
            tv_duration.setText(data.getDuration() + " menit");

            Glide.with(this)
                    .load(data.getImgUrl())
                    .fitCenter()
                    .centerCrop()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_thumb);
        } else {
            Log.w(TAG, "No intent data retrieved.");
        }
    }
}