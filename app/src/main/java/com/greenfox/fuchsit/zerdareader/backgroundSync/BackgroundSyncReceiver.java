package com.greenfox.fuchsit.zerdareader.backgroundSync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anna on 17/02/16.
 */

public class BackgroundSyncReceiver extends BroadcastReceiver {

    @Inject
    ReaderApiInterface apiService;
    SharedPreferences sharedPreferences;
    ArrayList<NewsItem> news;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("BackgroundSyncReceiver", "Service triggered");
        updateNewsItems(context);

    }

    private void updateNewsItems(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        DaggerMockServerComponent.builder().build().inject(this);

        Call<ArrayList<NewsItem>> call = apiService.getNewsItems(sharedPreferences.getString("token", ""));
        call.enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                news = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {
            }
        });
    }
}
