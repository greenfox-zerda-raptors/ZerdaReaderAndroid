package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    FeedAdapter adapter;
    int tabNumber;

    @Inject
    ReaderApiInterface apiService;


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


    public void showNewsItems() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        Call<ArrayList<NewsItem>> call;

        if(tabNumber == 1) {
            call = apiService.getNewsItems(sharedPreferences.getString("token", "default"));
        } else {
            call = apiService.getFavouriteNewsItems(sharedPreferences.getString("token", "default"));
        }

        call.enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                adapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {

            }
        });
    }


}





