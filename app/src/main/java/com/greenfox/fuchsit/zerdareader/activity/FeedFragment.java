package com.greenfox.fuchsit.zerdareader.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.greenfox.fuchsit.zerdareader.R;

import static android.app.DialogFragment.STYLE_NO_TITLE;

/**
 * Created by regnisalram on 1/24/17.
 */

public class FeedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        return view;
    }
}
