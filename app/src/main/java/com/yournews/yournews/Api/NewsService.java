package com.yournews.yournews.Api;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yournews.yournews.Constants;
import com.yournews.yournews.models.Articles;
import com.yournews.yournews.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {

    public static void getNews(Callback callback){
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        builder.addQueryParameter("apikey", Constants.API_KEY);
        String url = builder.build().toString();
        Log.d("URL",url);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public static ArrayList<Articles> findNews(Response response){
        ArrayList<Articles> articles= new ArrayList<>();
        try {
            String json = response.body().string();
            if (response.isSuccessful()){
                JSONObject jsonObject = new JSONObject(json);
                JSONArray newsJson = jsonObject.getJSONArray("articles");
                Type collectionType = new TypeToken<List<Articles>>() {}.getType();
                Gson gson = new GsonBuilder().create();
                articles = gson.fromJson(newsJson.toString(), collectionType);
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articles;
    }
}