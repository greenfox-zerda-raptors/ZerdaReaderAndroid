package com.greenfox.fuchsit.zerdareader.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.ZerdaReaderApp;
import com.greenfox.fuchsit.zerdareader.backgroundSync.BackgroundSyncReceiver;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    
    private SharedPreferences preferences;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onSharedPreferenceChanged(SharedPreferences loginData, String key) {
        setPreferenceScreen(null);
        addPreferencesFromResource(R.xml.preferences);
        enableBackgroundSync(preferences.getBoolean("checkbox_sync", true));
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
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), defineInterval(), pIntent);
    }

    private long defineInterval() {
        if(ZerdaReaderApp.inForeground) {
            return 120000L;
        } else {
            return 600000L;
        }
    }

    public void cancelAlarm() {
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(setupPendingIntent());
    }

    public PendingIntent setupPendingIntent() {
        Intent intent = new Intent(this, BackgroundSyncReceiver.class);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
