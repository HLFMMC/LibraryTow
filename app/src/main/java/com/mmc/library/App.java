package com.mmc.library;

import android.app.Application;

import com.google.gson.Gson;

/**
 * Created by hyj on 2017/3/4.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    Gson gson = null;
    public void init(){
        app = this;
        gson = new Gson();
    }

    public Gson getGson(){
        if(gson == null)
            gson = new Gson();
        return gson;
    }


    static App app;
    public static App getInstance(){
        return app;
    }
}
