package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.ZerdaReaderApp;
import com.greenfox.fuchsit.zerdareader.event.BackSyncSettingEvent;

import org.greenrobot.eventbus.EventBus;

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
        EventBus.getDefault().post(new BackSyncSettingEvent(preferences.getBoolean("checkbox_sync", true)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZerdaReaderApp.visible = true;
    }
}
