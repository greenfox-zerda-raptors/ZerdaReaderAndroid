package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;

/**
 * Created by false on 2017. 01. 31..
 */

public class SubscriptionsFragment extends Fragment {

    ListView feed;
    FeedAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subscriptions_fragment, container, false);

        feed = (ListView) view.findViewById(android.R.id.list);

        adapter = new FeedAdapter(getActivity());
        feed.setAdapter(adapter);

        return view;
    }


}

