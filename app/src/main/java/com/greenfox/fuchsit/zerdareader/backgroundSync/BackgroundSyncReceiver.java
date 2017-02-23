package com.greenfox.fuchsit.zerdareader.backgroundSync;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.event.BackgroundSyncEvent;
import com.greenfox.fuchsit.zerdareader.model.FeedResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anna on 17/02/16.
 */

public class BackgroundSyncReceiver extends BroadcastReceiver {


    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @Inject
    ReaderApiInterface apiService;
    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("BackgroundSyncReceiver", "Service triggered");
        updateNewsItems(context);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);



    }

    private void updateNewsItems(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        DaggerMockServerComponent.builder().build().inject(this);

        Call<FeedResponse> call = apiService.getNewsItems(sharedPreferences.getString("token", ""));

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                EventBus.getDefault().post(new BackgroundSyncEvent(response.body().feed));
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {

            }
        });

    }
}
