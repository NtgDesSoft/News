package com.test.news.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.news.FirstFragment;
import com.test.news.R;
import com.test.news.model.Article;
import com.test.news.model.News;
import com.test.news.model.Source;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articleList ;
    private Context mContext;
    FirstFragment firstFragment;

    public NewsAdapter(List<Article> articleList, Context mContext,FirstFragment firstFragment) {
        this.articleList = articleList;
        this.mContext = mContext;
        this.firstFragment = firstFragment;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_row_news, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
       // final Object mPos= articleList.get(position).getSource().getId();
        final String mPos= articleList.get(position).getSource().getId();
        holder.author.setText(article.getAuthor());
        holder.title.setText(article.getTitle());
        holder.source.setText(article.getSource().getName());

        Glide.with(mContext)
                .load(article.getUrlToImage())
                .into(holder.img);
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click","CCC");
                //firstFragment.gotoNext(mPos);
                firstFragment.gotoNext(mPos);
            }
        });




    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView author,title,source;
        LinearLayout linear;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            linear = itemView.findViewById(R.id.linearMain);
            img = itemView.findViewById(R.id.image_item_news);
            author = itemView.findViewById(R.id.txtAuthor);
            title = itemView.findViewById(R.id.txtTitle);
            source = itemView.findViewById(R.id.txtSource);
        }
    }
}
