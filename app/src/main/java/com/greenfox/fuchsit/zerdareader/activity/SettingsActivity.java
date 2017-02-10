package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.greenfox.fuchsit.zerdareader.R;

public class SettingsActivity extends PreferenceActivity {

    SharedPreferences settingsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       addPreferencesFromResource(R.xml.preferences);

    }

    private void saveDataToSharedPreferences() {
        settingsData = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        final SharedPreferences.Editor editor = settingsData.edit();

        editor.putBoolean("isPushEnabled", true);
        editor.putBoolean("isSyncEnabled", true);
        editor.apply();
    }
}


