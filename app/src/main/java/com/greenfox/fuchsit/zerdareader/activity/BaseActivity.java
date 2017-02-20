package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.fuchsit.zerdareader.R;

public abstract class BaseActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void onPause() {
        super.onPause();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("timestamp", System.currentTimeMillis());
    }

}





