package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dialog.ServerErrorDialog;
import com.greenfox.fuchsit.zerdareader.ZerdaReaderApp;
import com.greenfox.fuchsit.zerdareader.event.LeavingApplicationEvent;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ServerErrorDialog serverErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void onPause() {
        super.onPause();

        if(!ZerdaReaderApp.startingActivity) {
            EventBus.getDefault().post(new LeavingApplicationEvent());
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("timestamp", System.currentTimeMillis());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZerdaReaderApp.startingActivity = false;
    }

    public void showServerErrorDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        serverErrorDialog.show(fm, "new_subs_dialog");

    }

    @Override
    public void startActivity(Intent intent) {
        ZerdaReaderApp.startingActivity = true;
        super.startActivity(intent);
    }
}
