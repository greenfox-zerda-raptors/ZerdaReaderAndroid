package com.greenfox.fuchsit.zerdareader;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.backgroundSync.BackgroundSyncReceiver;
import com.greenfox.fuchsit.zerdareader.event.BackSyncSettingEvent;
import com.greenfox.fuchsit.zerdareader.event.BackgroundSyncEvent;
import com.greenfox.fuchsit.zerdareader.event.LeavingApplicationEvent;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by regnisalram on 2/16/17.
 */

public class ZerdaReaderApp extends Application{

    static Application application;
    public static boolean startingActivity;
    private AlarmManager alarm;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onTerminate() {
        EventBus.getDefault().unregister(this);
        super.onTerminate();
    }

    @Subscribe
    public void onLeavingApplicationEvent(LeavingApplicationEvent event) {
        PendingIntent pIntent = setupPendingIntent();
        scheduleAlarm();
    }

    @Subscribe
    public void onBackSyncSettingEvent(BackSyncSettingEvent event) {
        enableBackgroundSync(event.enabled);
    }

    public void enableBackgroundSync(boolean checkboxChecked) {
        if(checkboxChecked) {
            Log.e("App", "Background sync enabled");
            scheduleAlarm();
        } else {
            Log.e("App", "Background sync disabled");
            cancelAlarm();
        }
    }

    public void scheduleAlarm() {
        final PendingIntent pIntent = setupPendingIntent();
        Long interval = defineInterval();
        alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, interval, pIntent);
        Log.e("App", "Alarm scheduled");
    }

    public PendingIntent setupPendingIntent() {
        Intent intent = new Intent(getApplicationContext(), BackgroundSyncReceiver.class);
        return PendingIntent.getBroadcast(this, 0, intent,0);
    }

    private long defineInterval() {
        if(startingActivity) {
            Log.e("App", "in foreground");
            return 120000L;
        } else {
            Log.e("App", "not in foreground");
            return 600000L;
        }
    }

    public void cancelAlarm() {
        alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(setupPendingIntent());
        Log.e("App", "Alarm cancelled");
    }

    @Subscribe
    public void onBackgroundSyncEvent(BackgroundSyncEvent event) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Long exitTimeInMillis = sharedPref.getLong("timestamp", 0L);
        ArrayList<NewsItem> newNewsList = event.getNewsList();

        getNumOfNewNewsItems(exitTimeInMillis, newNewsList);
    }

    private int getNumOfNewNewsItems(Long exitTimeInMillis, ArrayList<NewsItem> newNewsList) {
        int count = 0;
        for (NewsItem temp : newNewsList) {
            if(temp.getCreated() > exitTimeInMillis) {
                count++;
            }
        }
        return count;
    }
}
