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
import com.yournews.yournews.models.News;

import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.myViewHolder> {

    private List<News> results;
    private Context context;

    public TrendingAdapter( Context context,List<News> results) {
        this.results = results;
        this.context = context;
    }


    @Override
    public TrendingAdapter.myViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup ,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder( myViewHolder viewHolder, int i) {
        viewHolder.author.setText(results.get(0).getAuthor());
        viewHolder.publishedAt.setText(results.get(i).getPublishedAt());
        viewHolder.title.setText(results.get(i).getTitle());
        viewHolder.desc.setText(results.get(i).getDescription());
        viewHolder.source.setText((CharSequence) results.get(i).getSource());

        Picasso.with(context).load("https://cdn.cnn.com/cnnnext/dam/assets/"+results.get(i)
                .getUrlToImage()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView author, publishedAt,title,desc,source,time;

        public myViewHolder(View itemView) {
            super(itemView);
    imageView = itemView.findViewById(R.id.img);
    author = itemView.findViewById(R.id.author);
    publishedAt = itemView.findViewById(R.id.publishedAt);
    title = itemView.findViewById(R.id.title);
    desc = itemView.findViewById(R.id.desc);
    source = itemView.findViewById(R.id.source);
    time = itemView.findViewById(R.id.time);
        }
    }
}
