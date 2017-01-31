package com.greenfox.fuchsit.zerdareader.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

/**
 * Created by regnisalram on 1/30/17.
 */

public class DetailedPageFragment extends Fragment {

    TextView article;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailed_page_fragment, container, false);

        article = (TextView) view.findViewById(R.id.description);

        showDescription();

        return view;
    }

    private void showDescription() {
    }
}
