package com.greenfox.fuchsit.zerdareader.syncService;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.activity.FeedFragment;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anna on 17/02/09.
 */

public class BackgroundSyncService extends IntentService {

    public static final String TRANSACTION_DONE = "com.geenfox.fuchsit.zerdareader.TRANSACTION_DONE";

    @Inject
    ReaderApiInterface apiService;
    SharedPreferences sharedPreferences;

    public BackgroundSyncService() {
        super(BackgroundSyncService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("BackgroundSyncService", "Service started");
        updateNewsItems();
        Log.e("BackgroundSyncService", "Service stopped");
        Intent i = new Intent(TRANSACTION_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void updateNewsItems() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        DaggerMockServerComponent.builder().build().inject(this);
        Call<ArrayList<NewsItem>> call = apiService.getNewsItems(sharedPreferences.getString("token", ""));
        call.enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                ArrayList<NewsItem> news = response.body();
                Intent redirectToFeedFragment = new Intent(getApplicationContext(), FeedFragment.class);
                redirectToFeedFragment.putExtra("news", news);
                startService(redirectToFeedFragment);
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {
            }
        });
    }
}
