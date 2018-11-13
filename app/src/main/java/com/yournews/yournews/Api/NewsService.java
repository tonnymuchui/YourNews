package com.yournews.yournews.Api;
import android.util.Log;

import com.yournews.yournews.models.Article;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {
    public static void findNews( Callback callback) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Api.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("api_key", Api.NEWS_API_KEY);
        String url = urlBuilder.build().toString();
        Log.d("URL", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
//    public static  ArrayList<Headline> processHeadline(Response response) throws JSONException,IOException {
//        ArrayList<Headline> headlines = new ArrayList<>();
//        Log.d("RESPONSE",response.body().toString());
//        try {
//            String jasonData = response.body().string();
//            if (response.isSuccessful()){
//                JSONObject newsJASON = new JSONObject(jasonData);
//                JSONArray jsonArray = newsJASON.optJSONArray("headline");
//                for (int i = 0;i < jsonArray.length();i++){
//            JSONObject jsonArray1= jsonArray.getJSONObject(i);
//            String status = jsonArray1.getString("status");
//            Integer totalResult = jsonArray1.getInt("totalResult");
//            ArrayList<Article> article = new ArrayList<>();
//
//            JSONArray articles = newsJASON.getJSONArray("articles");
//            for (int y =0;y < articles.length();y++){
//                JSONObject jsonArray2 = jsonArray.getJSONObject(y);
//            String author = jsonArray2.getString("author");
//             String title = jsonArray2.getString("title");
//             String description = jsonArray2.getString("description");
//              String url = jsonArray2.getString("url");
//              String urlToImage = jsonArray2.getString("urlToImage");
//              String publishedAt = jsonArray2.getString("publishedAt");
//              ArrayList<Source> source= new ArrayList<>();
//Article article1 = new Article(author,title,description,url,urlToImage,publishedAt,source);
//              JSONArray sources = newsJASON.getJSONArray("sources");
//              for (int x =0; x < sources.length();x++){
//                  JSONObject jsonArray3 = jsonArray.getJSONObject(x);
//                  Integer id = jsonArray3.getInt("id");
//                  String name = jsonArray3.getString("name");
//               Source source1 = new Source(id,name);
//              }
//            }
//            Headline headline = new Headline(status,totalResult,article);
//            headlines.add(headline);
//            headlines.add()
//
//                }
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}