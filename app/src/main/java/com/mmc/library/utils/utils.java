package com.mmc.library.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.ui.presenters.base.Message;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

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

    public static void get(Message msg,String url,Class<?> cls)throws IOException{
        Gson gson=new Gson();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Map<String,Object> resMap= gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
                Log.e("tag json",gson.toJson(resMap.get("data")));
                msg.obj = gson.fromJson(gson.toJson(resMap.get("data")),cls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getOfList(Message msg,String url)throws IOException{
        Gson gson=new Gson();
        Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Map<String,Object> resMap= gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
                msg.obj = gson.toJson(resMap.get("data"));
            }
    }

    public static void getOfList(Message msg,String url,String token)throws IOException{
        Gson gson=new Gson();
        Request request = new Request.Builder()
                .url(url+"?token="+token)
                .build();
        Response response = client.newCall(request).execute();
        if(response != null) {
            String result = response.body().string();
            Map<String,Object> resMap= gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
            msg.obj = gson.toJson(resMap.get("data"));
        }
    }

    public static void get(Message msg,String url,String token,Class<?> cls)throws IOException{
        Gson gson=new Gson();
        Request request = new Request.Builder()
                .url(url+"?token="+token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Map<String,Object> resMap= gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
                msg.obj = gson.fromJson(gson.toJson(resMap.get("data")),cls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void post(Message msg,HashMap map, String url,Class<?> cls){
            Gson gson=new Gson();
            String json= gson.toJson(map);
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
                Map<String,Object> resMap= gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
                msg.obj = gson.fromJson(gson.toJson(resMap.get("data")),cls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void post(Message msg,HashMap map,String url,String token,Class<?> cls)throws IOException{
        Gson gson=new Gson();
        String json= gson.toJson(map);
        RequestBody body=RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(url+"?token="+token).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            if(response != null) {
                String result = response.body().string();
                Map<String,Object> resMap= gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
                msg.obj = gson.fromJson(gson.toJson(resMap.get("data")),cls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public interface CallBack{

        void call(String json);
    }

    public static Class getSuperClassGenricType(Class clazz) {
                 return getSuperClassGenricType(clazz, 0);
             }

                 /**
     18      * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     19      * 如public BookManager extends GenricManager<Book>
     20      *
     21      * @param clazz clazz The class to introspect
     22      * @param index the Index of the generic ddeclaration,start from 0.
     23      */

                 public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

                 Type genType = clazz.getGenericSuperclass();
               if (!(genType instanceof ParameterizedType)) {
                         return Object.class;
                    }
                 Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

               if (index >= params.length || index < 0) {
                         return Object.class;
                    }
                 if (!(params[index] instanceof Class)) {
                         return Object.class;
                     }
        return (Class) params[index];
             }
}
