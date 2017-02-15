package com.greenfox.fuchsit.zerdareader.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.syncService.BackgroundSyncService;

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
    }

    public void enableBackgroundSync(View view) {
        CheckBox checkBoxPreference = (CheckBox) view.findViewById(R.id.checkbox_sync);
        if(checkBoxPreference.isChecked()) {
            scheduleAlarm();
        } else {
            cancelAlarm();
        }
    }

    public void scheduleAlarm() {
        Intent intent = new Intent(this, BackgroundSyncService.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, BackgroundSyncService.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(this, BackgroundSyncService.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, BackgroundSyncService.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }

}

