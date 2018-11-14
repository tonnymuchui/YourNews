package com.yournews.yournews;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.yournews.yournews.Api.Api;
import com.yournews.yournews.Api.NewsService;
import com.yournews.yournews.models.News;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Trending extends Fragment {

    private SweetAlertDialog sweetAlertDialog;
    private String path, response;
    private OkHttpClient okHttpClient;

    private News news;
    private List<News> data;
    private NewsService newsService;

    private RecyclerView recyclerView;
    private TrendingAdapter trendingAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trending, container, false);

sweetAlertDialog = new SweetAlertDialog (this , SweetAlertDialog.SUCCESS_TYPE);
newsService = new NewsService();
data = new ArrayList<>();
recyclerView = getId(R.id.recyclerView);
layoutManager = new LinearLayoutManager(this);
recyclerView.setLayoutManager(layoutManager);
recyclerView.setHasFixedSize(true);
    try {
        path = Api.BASE_URL+"top-headlines?sources=cnn&apiKey=a3826efc75cb44e283b878eb3cc03162";
        new GetDataFromServer().execute();
    }catch (IOException){

    }

    }
    public class getServerData extends AsyncTask<Void, Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                sweetAlertDialog.getProgressHelper().getBarColor(Color.parseColor("#26A65B"));
                sweetAlertDialog.setTitleText("Loading");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
            } catch (Exception e){

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (sweetAlertDialog.isShowing()){
                sweetAlertDialog.dismiss();
            }
trendingAdapter = new TrendingAdapter(Trending.this,data);
            recyclerView.setAdapter(trendingAdapter);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                okHttpClient = new OkHttpClient();
                response = newsService.GET(okHttpClient, path);
                Log.e("JSON:", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<News>>(){
                }.getType();
                news = gson.fromJson(response,News.class);
                if (data.isEmpty()){
                    for (int i = 0; i<news.getResults().size();i++){
                        data.add(news.getResults.get(i));

                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
