package com.greenfox.fuchsit.zerdareader.activity;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by regnisalram on 1/24/17.
 */

public class FeedFragment extends ListFragment {

    ListView feed;
    FeedAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);

        feed = (ListView) view.findViewById(android.R.id.list);

        adapter = new FeedAdapter(getActivity());
        feed.setAdapter(adapter);

        showNewsItems();

        return view;
    }

    public void showNewsItems() {

        //this is commented out because we will switch to this line when we'll use the real server
//        final ReaderApiInterface apiService = api.getClient().create(ReaderApiInterface.class);
        MockServer apiService = new MockServer();
        Call<ArrayList<NewsItem>> call = apiService.getNewsItems();

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

    @Override
    public void onListItemClick(ListView feed, View view, int position, long id) {
        super.onListItemClick(feed, view, position, id);

        NewsItem item = (NewsItem) feed.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("newsItem", item);

        DetailedPageFragment detailedPageFragment = new DetailedPageFragment();
        detailedPageFragment.setArguments(bundle);
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailedPageFragment)
                .addToBackStack(null)
                .commit();
    }
}



