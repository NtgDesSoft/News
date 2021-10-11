package com.test.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.news.R;
import com.test.news.model.Article;

import java.util.List;

public class NewsDetailsAdapter extends RecyclerView.Adapter<NewsDetailsAdapter.NewsDetailsViewHolder> {
    private List<Article> articleList;
    private Context mContext;

    public NewsDetailsAdapter(List<Article> articleList, Context mContext) {
        this.articleList = articleList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_row_newsdetails, parent, false);

        return new NewsDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDetailsViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.author.setText(article.getAuthor());
        holder.title.setText(article.getTitle());
        holder.source.setText(article.getSource().getName());


        Glide.with(mContext)
                .load(article.getUrlToImage())
                .into(holder.img);

        //get the url of particular position
        final String url = article.getUrl();

        //pass id of particular cardview and open in Webview
        Log.i("UU",url);
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using implicit intent
//                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                mContext.startActivity(i);


                //Using Chrome Custom Tab
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int colorInt = Color.parseColor("#009688");
                builder.setToolbarColor(colorInt);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(mContext, Uri.parse(url));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class NewsDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView author,title,source;
        LinearLayout linear;
        public NewsDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            linear = itemView.findViewById(R.id.linearMain2);
            img = itemView.findViewById(R.id.image_newsdatails);
            author = itemView.findViewById(R.id.txtAuthor);
            title = itemView.findViewById(R.id.txtTitle);
            source = itemView.findViewById(R.id.txtSource);
        }
    }

}
