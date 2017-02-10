package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import com.greenfox.fuchsit.zerdareader.R;

public class SettingsActivity extends PreferenceActivity {

    private CheckBoxPreference checkbox_sync, checkbox_push;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        checkbox_sync = (CheckBoxPreference) findPreference("isSyncEnabled");
        checkbox_push = (CheckBoxPreference) findPreference("isPushEnabled");

        final Preference preference = findPreference("Preference");
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newVal) {
                final boolean value = (Boolean) newVal;
                checkbox_sync.setChecked(value);
                checkbox_push.setChecked(value);
                return true;
            }

        });
    }

}