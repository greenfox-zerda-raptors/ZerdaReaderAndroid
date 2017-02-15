package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.fuchsit.zerdareader.R;

public abstract class BaseActivity extends AppCompatActivity {

    SharedPreferences timePreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void onPause() {
        super.onPause();

        Long timestamp = System.currentTimeMillis()/1000;
        timePreferences = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        final SharedPreferences.Editor editor = timePreferences.edit();
        editor.putLong("timestamp", timestamp);
    }

}





