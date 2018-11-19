package com.yournews.yournews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.yournews.yournews.Api.NewsService;
import com.yournews.yournews.models.Articles;
import com.yournews.yournews.models.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity {
    public static final   String TAG = NewsActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private TrendingAdapter mtrendingAdapter;

    public ArrayList<Articles> articles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        getNews();
    }

    private void getNews() {

        final NewsService newsService = new NewsService();

        NewsService.getNews(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws  IOException {

                articles = newsService.findNews(response);
                NewsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mtrendingAdapter = new TrendingAdapter( NewsActivity.this, articles);
                        mRecyclerView.setAdapter(mtrendingAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                       mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}

