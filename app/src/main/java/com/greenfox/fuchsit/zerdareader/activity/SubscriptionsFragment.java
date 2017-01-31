package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.fuchsit.zerdareader.R;

/**
 * Created by false on 2017. 01. 31..
 */

public class SubscriptionsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subscriptions_fragment, container, false);
        return view;
    }


}

