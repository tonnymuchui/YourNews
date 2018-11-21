package com.yournews.yournews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import com.yournews.yournews.Api.NewsService;
import com.yournews.yournews.models.Articles;

import java.util.ArrayList;

import butterknife.BindView;

public class NewsDetails extends AppCompatActivity {
    public static final   String TAG = NewsDetails.class.getSimpleName();
    public ArrayList<Articles> articles = new ArrayList<>();
    @BindView(R.id.recyclerView2) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Intent intent = getIntent();
        String headline = intent.getStringExtra("SEARCH");


    }

}
