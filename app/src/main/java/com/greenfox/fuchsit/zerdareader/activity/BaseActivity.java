package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void onPause() {
        super.onPause();

        Long timestamp = System.currentTimeMillis()/1000;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("timestamp", timestamp);
    }

    public void showNewsItems(Response<ArrayList<NewsItem>> response) {
        adapter.clear();
        adapter.addAll(response.body());
    }


}





