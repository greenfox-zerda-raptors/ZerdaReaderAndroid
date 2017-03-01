package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.event.BackgroundSyncEvent;
import com.greenfox.fuchsit.zerdareader.event.FavoriteSavedEvent;
import com.greenfox.fuchsit.zerdareader.event.RefreshEvent;
import com.greenfox.fuchsit.zerdareader.model.FeedResponse;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;
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
    BaseActivity baseActivity;

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

        downloadNewsItems();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    public void downloadNewsItems() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FeedFragment.super.getContext());
        Call<FeedResponse> call;

        if(tabNumber == 1) {
            call = apiService.getNewsItems(sharedPreferences.getString("token", "default"));
        } else {
            call = apiService.getFavouriteNewsItems(sharedPreferences.getString("token", "default"));
        }

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.code() == 200) {
                    showNewsItems(response.body().feed);
                }
                if (response.code() == 401) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
                Log.d("onResponse", "code:" + response.code());
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Log.d("onfailure-dwnldfeed", t.toString());
                ((BaseActivity) getActivity()).showServerErrorDialog(null);
            }
        });
    }

    public void showNewsItems(ArrayList<NewsItem> newsItems) {
        adapter.clear();
        adapter.addAll(newsItems);
        feed.smoothScrollToPositionFromTop(0, 0, 0);
    }

    @Override
    public void onListItemClick(ListView feed, View view, int position, long id) {
        super.onListItemClick(feed, view, position, id);

        NewsItem item = (NewsItem) feed.getItemAtPosition(position);
        updateRequest = new UpdateRequest(1);
        Call <ResponseBody>  call = apiService.updateOpened(item.getId(), sharedPreferences.getString("token", "default"), updateRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ((BaseActivity) getActivity()).showServerErrorDialog(null);
            }
        });

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
        feed.smoothScrollToPositionFromTop(0, 0, 0);
    }

    @Subscribe
    public void onBackgroundSyncEvent (BackgroundSyncEvent backgroundSyncEvent) {
        showNewsItems(backgroundSyncEvent.getNewsList());
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent refreshEvent) {
        downloadNewsItems();
        Toast.makeText(getActivity(),"Refreshed",Toast.LENGTH_LONG).show();

    }
}
