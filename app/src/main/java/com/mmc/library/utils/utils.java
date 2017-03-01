package com.mmc.library.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class utils {
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client=new OkHttpClient();

    public static String get(String url)throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String get(String url,String token)throws IOException{
        Request request = new Request.Builder()
                .url(url+"?token="+token)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url,String json)throws IOException{
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(url).post(body).build();
        Response response=client.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url,String json,String token)throws IOException{
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(url+"?token="+token).post(body).build();
        Response response=client.newCall(request).execute();
        return response.body().string();
    }


    public static String put(String url,String json)throws IOException{
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(url).put(body).build();
        Response response=client.newCall(request).execute();
        return response.body().string();
    }

    public static String put(String url,String json,String token)throws IOException{
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(url+"?token="+token).put(body).build();
        Response response=client.newCall(request).execute();
        return response.body().string();
    }
}
