package com.greenfox.fuchsit.zerdareader;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.backgroundSync.BackgroundSyncReceiver;
import com.greenfox.fuchsit.zerdareader.event.BackSyncSettingEvent;
import com.greenfox.fuchsit.zerdareader.event.LeavingApplicationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by regnisalram on 2/16/17.
 */

public class ZerdaReaderApp extends Application{

    static Application application;
    public static boolean visible;
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
        alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), defineInterval(), pIntent);
    }

    public PendingIntent setupPendingIntent() {
        Intent intent = new Intent(this, BackgroundSyncReceiver.class);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private long defineInterval() {
        if(visible) {
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

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, BackgroundSyncReceiver.class);
        notificationIntent.putExtra(BackgroundSyncReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(BackgroundSyncReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_new);
        return builder.build();
    }
}
