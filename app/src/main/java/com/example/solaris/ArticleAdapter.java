package com.example.solaris;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    List<ArticleList>articleLists;
    private Context mContext;
    private AdapterView.OnItemClickListener mListener;


    public ArticleAdapter(List<ArticleList> articleLists, Context ct) {
        this.articleLists = articleLists;
        this.mContext = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ly,parent,false);
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleList articleList=articleLists.get(position);
        Glide.with(mContext)
                .load(articleList.getImageUrl())
                .placeholder(R.drawable.default_img)
                .centerCrop()
                .into(holder.articleimg);

        holder.articlename.setText(articleList.getArticleName());
        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, Detail_lokasi.class);
            intent.putExtra("articleName", articleList.articleName);
            intent.putExtra("imageUrl", articleList.imageUrl);
            intent.putExtra("description", articleList.description);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articleLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView articleimg;
        TextView articlename;
        private Context context;
        public ViewHolder(Context mContext, @NonNull View itemView) {
            super(itemView);
            this.context = mContext;
            articleimg=itemView.findViewById(R.id.article_image);
            articlename=itemView.findViewById(R.id.article_name);
        }
    }
}