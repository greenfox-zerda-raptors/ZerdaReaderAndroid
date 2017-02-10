package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;

public class SettingsActivity extends PreferenceActivity {

    SharedPreferences loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       addPreferencesFromResource(R.xml.preferences);

}

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        loginData = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        final SharedPreferences.Editor editor = loginData.edit();

        switch(view.getId()) {
            case R.id.checkbox_sync:
                if (checked)
                editor.putBoolean("isSyncEnabled", true);
                editor.apply();
                Toast.makeText(SettingsActivity.this, "Sync enabled", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkbox_push:
                if (checked)
                editor.putBoolean("isSyncEnabled", true);
                editor.apply();
                Toast.makeText(SettingsActivity.this, "Push enabled", Toast.LENGTH_LONG).show();
                break;
        }
    }

}


