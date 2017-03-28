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
        alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
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
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), defineInterval(), pIntent);
    }

    @Subscribe
    public void onBackSyncSettingEvent(BackSyncSettingEvent event) {
        enableBackgroundSync(event.enabled);
    }

    public void enableBackgroundSync(boolean checkboxChecked) {
        if(checkboxChecked) {
            Log.e("SettingsActivity", "background sync enabled");
            scheduleAlarm();
        } else {
            Log.e("SettingsActivity", "background sync disabled");
            cancelAlarm();
        }
    }

    public void scheduleAlarm() {
        final PendingIntent pIntent = setupPendingIntent();
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), defineInterval(), pIntent);
    }

    public PendingIntent setupPendingIntent() {
        Intent intent = new Intent(this, BackgroundSyncReceiver.class);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private long defineInterval() {
        if(startingActivity) {
            Log.e("App", "in Foreground");
            return 120000L;
        } else {
            Log.e("App", "not in Foreground");
            return 600000L;
        }
    }

    public void cancelAlarm() {
        alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(setupPendingIntent());
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
