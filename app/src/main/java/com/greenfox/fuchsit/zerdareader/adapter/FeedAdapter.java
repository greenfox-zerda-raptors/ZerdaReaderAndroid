package com.greenfox.fuchsit.zerdareader.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by regnisalram on 1/24/17.
 */
public class FeedAdapter extends ArrayAdapter<NewsItem> {

    public FeedAdapter(Context context, ArrayList<NewsItem> newsItems) {
        super(context, 0, newsItems);
    }
}
