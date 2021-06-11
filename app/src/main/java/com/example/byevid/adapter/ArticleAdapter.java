package com.example.byevid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.byevid.ArticleDetailActivity;
import com.example.byevid.R;
import com.example.byevid.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{
    private Context context;
    private List<Article> articleList;

    public ArticleAdapter(Context context, ArrayList<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_duration;
        ImageView thumbnail;
        LinearLayout ll_article;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_article = itemView.findViewById(R.id.ll_article);
            tv_title = itemView.findViewById(R.id.tv_list_article_title);
            tv_duration = itemView.findViewById(R.id.tv_list_article_duration);
            thumbnail = itemView.findViewById(R.id.img_list_article_thumb);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_article, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        final Article data = articleList.get(position);

        holder.tv_title.setText(data.getTitle());
        holder.tv_duration.setText(data.getDuration() + " menit");
        Glide.with(context)
                .load(data.getImgUrl())
                .fitCenter()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        // Click listener
        holder.ll_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Klik", Toast.LENGTH_SHORT).show();
                Intent detail = new Intent(context, ArticleDetailActivity.class);
                detail.putExtra("Data", data);
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
