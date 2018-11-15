package com.yournews.yournews;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.yournews.yournews.Api.NewsService;
import com.yournews.yournews.models.News;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity {
    private SweetAlertDialog sweetAlertDialog;
    private String path, response;
    private OkHttpClient okHttpClient;

    private News news;
    private ArrayList<News> data;
    private NewsService newsService;

    private RecyclerView recyclerView;
    private TrendingAdapter trendingAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getNews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sweetAlertDialog = new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE);
        newsService = new NewsService();
        data = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }

    public void getNews() {
        final NewsService newsService = new NewsService();
        NewsService.getNews(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    data = newsService.findNews(response);
                    Log.d("Response", response.body().toString());
                } catch (Exception e) {

                }
                NewsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        trendingAdapter = new TrendingAdapter(getApplicationContext(), data);
                        recyclerView.setAdapter(trendingAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }}







//        try {
//            path = Constants.BASE_URL+"top-headlines?sources=cnn&apiKey=a3826efc75cb44e283b878eb3cc03162";
//            new getNews.execute();
//        }catch (Exception e){
//
//        }


//
//private  class  GetDataFromServer extends AsyncTask<Void,Void,Void> {
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        try {
//            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#26A65B"));
//            sweetAlertDialog.setTitleText("Loading");
//            sweetAlertDialog.setCancelable(false);
//            sweetAlertDialog.show();
//        }catch (Exception e){}
//
//    }

//    @Override
//    protected void onPostExecute(Void result) {
//        super.onPostExecute(result);
//        if (sweetAlertDialog.isShowing()){
//            sweetAlertDialog.dismiss();
//        }
//        trendingAdapter = new TrendingAdapter(NewsActivity.this,data);
//        recyclerView.setAdapter(trendingAdapter);
//    }
//
//    @Override
//    protected  Void doInBackground(Void... voids) {
//        try {
//            okHttpClient = new OkHttpClient();
//            response = newsService.GET(okHttpClient,path);
//            Log.e("##JSON", response);
//            Gson gson = new GsonBuilder().create();
//            Type type = new  TypeToken<List<News>>(){}.getType();
//
//            news = gson.fromJson(response, News.class);
//            data.add(news);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
