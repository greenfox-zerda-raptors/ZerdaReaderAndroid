package com.greenfox.fuchsit.zerdareader;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by regnisalram on 2/16/17.
 */

public class ZerdaReaderApp extends Application implements Application.ActivityLifecycleCallbacks{

    static Application application;
    public static boolean inForeground;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        inForeground = true;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        inForeground = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        inForeground = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        inForeground = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        inForeground = false;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        inForeground = false;
    }
}
