package com.yournews.yournews.Api;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yournews.yournews.Constants;
import com.yournews.yournews.NewsActivity;
import com.yournews.yournews.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {
//    public String GET(OkHttpClient client, String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    private static OkHttpClient client = new OkHttpClient();
    public static void getNews(Callback callback){
        HttpUrl.Builder builder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        builder.addQueryParameter("apikey", Constants.API_KEY);
        String url = builder.build().toString();
        Log.d("URL",url);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public static ArrayList<News> findNews(Response response){
        ArrayList<News> news= new ArrayList<>();
        try {
            String jason = response.body().string();
            if (response.isSuccessful()){
                JSONObject jsonObject = new JSONObject(jason);
                JSONArray newsJson = jsonObject.getJSONArray("articles");
                Type type = new TypeToken<List<News>>() {}.getType();
                Gson gson = new GsonBuilder().create();
                news = gson.fromJson(newsJson.toString(), type);
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }
}