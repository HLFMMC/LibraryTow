package com.mmc.library.utils;

import com.mmc.library.App;
import com.mmc.library.bean.Result;
import com.mmc.library.ui.presenters.base.Message;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class utils<T>{
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client=new OkHttpClient();

    /**
     * 获取单个实体
     * @param url
     * @param cls
     * @return
     */
    public Message get(String url,Class<?> cls) {
        Message msg = new Message();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Type type = TypeBuilder.newInstance(Result.class)
                        .addTypeParam(cls)
                        .build();
                Result<T> result1 = App.getInstance().getGson().fromJson(result,type);
                msg.obj = result1.data;
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj = null;
        return msg;
    }

    /**
     * 获取单个实体（带token）
     * @param url
     * @param token
     * @param cls
     * @return
     */
    public Message get(String url,String token,Class<?> cls){
        Message msg = new Message();
        Request request = new Request.Builder()
                .url(url+"?token="+token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Type type = TypeBuilder.newInstance(Result.class)
                        .addTypeParam(cls)
                        .build();
                Result<T> result1 = App.getInstance().getGson().fromJson(result,type);
                msg.obj = result1.data;
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj = null;
        return msg;
    }

    /**
     * 获取列表
     * @param url
     * @param cls
     * @return
     */
    public Message getOfList(String url,Class<T> cls) {
        Message msg = new Message();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Type type = TypeBuilder.newInstance(Result.class)
                        .beginSubType(List.class)
                        .addTypeParam(cls)
                        .endSubType()
                        .build();
                Result<List<T>> result1 = App.getInstance().getGson().fromJson(result,type);
                msg.obj = result1.data;
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj = null;
        return msg;
    }

    /**
     * 获取列表（带token）
     * @param url
     * @param token
     * @param cls
     * @return
     */
    public Message getOfList(String url,String token,Class<T> cls){
        Message msg = new Message();
        Request request = new Request.Builder()
                .url(url+"?token="+token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Type type = TypeBuilder.newInstance(Result.class)
                        .beginSubType(List.class)
                        .addTypeParam(cls)
                        .endSubType()
                        .build();
                Result<List<T>> result1 = App.getInstance().getGson().fromJson(result,type);
                msg.obj = result1.data;
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj = null;
        return msg;
    }

    /**
     * post
     * @param msg
     * @param url
     * @param cls
     * @return
     */
    public Message post(Message msg, String url, Class<T> cls){
        String json= App.getInstance().getGson().toJson(msg.obj);
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request
                .Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Type type = TypeBuilder.newInstance(Result.class)
                        .addTypeParam(cls)
                        .build();
                Result<T> result1 = App.getInstance().getGson().fromJson(result,type);
                msg.obj = result1.data;
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj = null;
        return msg;
    }

    /**
     * post(带token)
     * @param msg
     * @param url
     * @param token
     * @param cls
     * @throws IOException
     */
    public Message post(Message msg,String url,String token,Class<?> cls)throws IOException{
        String json= App.getInstance().getGson().toJson(msg.obj);
        RequestBody body=RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(url+"?token="+token).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Type type = TypeBuilder.newInstance(Result.class)
                        .addTypeParam(cls)
                        .build();
                Result<T> result1 = App.getInstance().getGson().fromJson(result,type);
                msg.obj = result1.data;
                return msg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj = null;
        return msg;
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
