package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;

import com.greenfox.fuchsit.zerdareader.R;

public class SettingsActivity extends PreferenceActivity implements AppCompatCallback {

    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        delegate = AppCompatDelegate.create(this, this);
        // delegate.setContentView(R.layout.activity_settings);
        Toolbar myToolbar= (Toolbar) findViewById(R.id.my_toolbar);
        delegate.setSupportActionBar(myToolbar);

    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}


