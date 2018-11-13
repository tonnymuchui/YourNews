package com.yournews.yournews.Api;
import android.util.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}