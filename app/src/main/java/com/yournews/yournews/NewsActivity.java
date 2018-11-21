package com.yournews.yournews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.yournews.yournews.Api.NewsService;
import com.yournews.yournews.models.Articles;

import java.io.IOException;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity {
    public static final   String TAG = NewsActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.imageButton) ImageButton mbutton;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private TrendingAdapter mtrendingAdapter;
    private SharedPreferences mSharedPreferences;
    public ArrayList<Articles> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getNews();

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent = new Intent(NewsActivity.this,Profile.class);
startActivity(intent);

            }
        });
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

