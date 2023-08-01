package com.alperen.newnews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static List<ArticleEntity> articleList;

    public NewsAdapter(List<ArticleEntity> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        ArticleEntity article = articleList.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.descriptionTextView.setText(article.getDescription());
        //holder.urlTextView.setText(article.getUrl());

        Glide.with(holder.itemView.getContext())
                .load(article.getUrlToImage())
                .into(holder.imageImageView);


    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        Button urlTextView;
        ImageView imageImageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            urlTextView = itemView.findViewById(R.id.url);
            imageImageView = itemView.findViewById(R.id.picture);

            urlTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ArticleEntity article = articleList.get(position);
                        String url = article.getUrl();
                        if (url != null && !url.isEmpty()) {
                            openUrlInBrowser(url, v.getContext());
                        }
                    }
                }
            });
        }

        private void openUrlInBrowser(String url, Context context) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }
    }
}