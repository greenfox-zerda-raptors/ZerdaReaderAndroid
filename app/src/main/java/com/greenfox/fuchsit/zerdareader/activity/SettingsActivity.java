package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.greenfox.fuchsit.zerdareader.R;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private CheckBoxPreference checkbox_sync, checkbox_push;
    private SharedPreferences loginData;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        loginData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        loginData.registerOnSharedPreferenceChangeListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onSharedPreferenceChanged(SharedPreferences loginData, String s) {
        setPreferenceScreen(null);
        addPreferencesFromResource(R.xml.preferences);
    }
}