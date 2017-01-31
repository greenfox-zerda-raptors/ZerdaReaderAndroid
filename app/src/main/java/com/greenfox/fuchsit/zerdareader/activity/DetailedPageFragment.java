package com.greenfox.fuchsit.zerdareader.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

/**
 * Created by regnisalram on 1/30/17.
 */

public class DetailedPageFragment extends Fragment {

    TextView article;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailed_page_fragment, container, false);

        NewsItem newsItem = (NewsItem) getArguments().getSerializable("newsItem");

        article = (TextView) view.findViewById(R.id.description);
        article.setText(newsItem.getDescription());

        return view;
    }
}
