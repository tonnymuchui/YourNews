package com.yournews.yournews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.yournews.yournews.models.Articles;
import com.yournews.yournews.models.News;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {


    @NonNull

    private ArrayList<Articles> mArticle;
    private Context mcontext;

    public TrendingAdapter(Context context, ArrayList<Articles> article){
        mcontext = context;
        mArticle = article;
    }


    @NonNull
    @Override
    public TrendingAdapter.TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        TrendingAdapter.TrendingViewHolder viewHolder = new TrendingAdapter.TrendingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.TrendingViewHolder viewHolder, int i) {
        viewHolder.bindArticles(mArticle.get(i));
    }

    @Override
    public int getItemCount() {
        return mArticle.size();
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img)ImageView mImage;
        @BindView(R.id.desc)TextView mDesc;
        @BindView(R.id.author)TextView mAuthor;
        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mcontext = itemView.getContext();
        }
        public void bindArticles(Articles article){
            Picasso.get().load(article.getUrlToImage()).into(mImage);
            mDesc.setText(article.getDescription());
            mAuthor.setText(article.getAuthor());
        }

    }
}