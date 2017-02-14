package com.greenfox.fuchsit.zerdareader.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import android.support.v4.app.ListFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;
import com.greenfox.fuchsit.zerdareader.syncService.BackgroundSyncService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by regnisalram on 1/24/17.
 */

public class FeedFragment extends ListFragment {

    ListView feed;
    FeedAdapter adapter;
    int tabNumber;
    SharedPreferences sharedPreferences;
    UpdateRequest updateRequest;
    private BroadcastReceiver broadcastReceiver;
    ArrayList<NewsItem> news;

    @Inject
    ReaderApiInterface apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);

        feed = (ListView) view.findViewById(android.R.id.list);
        adapter = new FeedAdapter(getActivity());
        feed.setAdapter(adapter);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        tabNumber = getArguments().getInt("tabNumber", 1);
        DaggerMockServerComponent.builder().build().inject(this);

        showNewsItems();

        return view;
    }

    public static FeedFragment newInstance(int tabNumber) {
        FeedFragment myFragment = new FeedFragment();

        Bundle args = new Bundle();
        args.putInt("tabNumber", tabNumber);
        myFragment.setArguments(args);

        return myFragment;
    }

    public void showNewsItems() {
        Call<ArrayList<NewsItem>> call;

        if(tabNumber == 1) {
            call = apiService.getNewsItems(sharedPreferences.getString("token", "default"));
        } else {
            call = apiService.getFavouriteNewsItems(sharedPreferences.getString("token", "default"));
        }

        call.enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                if(tabNumber == 1) {
                    news = response.body();
                }
                adapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onListItemClick(ListView feed, View view, int position, long id) {
        super.onListItemClick(feed, view, position, id);

        NewsItem item = (NewsItem) feed.getItemAtPosition(position);
        updateRequest = new UpdateRequest(item.getId(), 1);
        apiService.updateOpened(item.getId(), updateRequest, sharedPreferences.getString("token", "default"));

        Intent i = new Intent(getActivity(), DetailedPageActivity.class);
        i.putExtra("newsItem", item);
        startActivity(i);
    }

    // Setup a recurring alarm every 15 mins, will be wired to settings' view
    public void scheduleAlarm(View view) {
        createBroadcastReceiver();
        createIntentFilter();

        Intent intent = new Intent(getContext(), BackgroundSyncService.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(getContext(), BackgroundSyncService.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long interval = defineInterval();
        AlarmManager alarm = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pIntent);
    }

    private long defineInterval() {
        if(isVisible()) {
            return 60000L;
        } else {
            return 600000L;
        }
    }

    private void createBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("BackgroundSyncService", "Service received");
                updateFragment(intent);
            }
        };
    }

    public void createIntentFilter() {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(BackgroundSyncService.TRANSACTION_DONE);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, intentfilter);
    }

    private void updateFragment(Intent intent) {
        adapter.clear();
        ArrayList<NewsItem> list = (ArrayList<NewsItem>) intent.getExtras().getSerializable("bundle");
        calculateNumOfNewItems(list);
        adapter.addAll(list);
    }

    private int calculateNumOfNewItems(ArrayList<NewsItem> list) {
        return news.size() - list.size();
    }

    //will be wired to settings' view
    public void cancelAlarm() {
        Intent intent = new Intent(getContext(), BackgroundSyncService.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(getContext(), BackgroundSyncService.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}
