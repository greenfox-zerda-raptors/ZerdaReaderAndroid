package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.event.FavoriteSavedEvent;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    @Inject
    ReaderApiInterface apiService;

    UpdateRequest updateRequest;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feed_fragment, container, false);

        feed = (ListView) view.findViewById(android.R.id.list);

        adapter = new FeedAdapter(getActivity());
        feed.setAdapter(adapter);

        DaggerMockServerComponent.builder().build().inject(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        tabNumber = getArguments().getInt("tabNumber", 1);

        downloadNewsItems();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void downloadNewsItems() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FeedFragment.super.getContext());

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

    public void showNewsItems(Response<ArrayList<NewsItem>> response) {
        adapter.clear();
        adapter.addAll(response.body());
    }

    @Override
    public void onListItemClick(ListView feed, View view, int position, long id) {
        super.onListItemClick(feed, view, position, id);

        NewsItem item = (NewsItem) feed.getItemAtPosition(position);
        updateRequest = new UpdateRequest(1);
        apiService.updateOpened(item.getId(), sharedPreferences.getString("token", "default"), updateRequest);

        Intent i = new Intent(getActivity(), DetailedPageActivity.class);
        i.putExtra("newsItem", item);
        startActivity(i);
    }

    public static FeedFragment newInstance(int tabNumber) {
        FeedFragment myFragment = new FeedFragment();

        Bundle args = new Bundle();
        args.putInt("tabNumber", tabNumber);
        myFragment.setArguments(args);

        return myFragment;
    }
    @Subscribe
    public void onFavoriteSavedEvent(FavoriteSavedEvent favoriteSavedEvent) {
        adapter.toggleFavoriteById(favoriteSavedEvent.getItem_id());
    }
}



