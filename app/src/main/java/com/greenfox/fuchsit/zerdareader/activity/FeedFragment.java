package com.greenfox.fuchsit.zerdareader.activity;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.fuchsit.zerdareader.R;

/**
 * Created by regnisalram on 1/24/17.
 */

public class FeedFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        return view;
    }
}
