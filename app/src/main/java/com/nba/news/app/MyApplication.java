package com.nba.news.app;

import android.app.Application;

/**
 * Created by allen on 16/12/16.
 */

public class MyApplication extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getContext(){
        return application;
    }
}
