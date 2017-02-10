package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.greenfox.fuchsit.zerdareader.R;

public class SettingsActivity extends PreferenceActivity {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       addPreferencesFromResource(R.xml.preferences);

    }

    public void enableBackgroundSync() {

    }

    public void enablePushNotifications() {

    }

}


