package com.greenfox.fuchsit.zerdareader;

import android.app.Application;

/**
 * Created by regnisalram on 2/16/17.
 */

public class ZerdaReaderApp extends Application{

    static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication() {
        return application;
    }
}
