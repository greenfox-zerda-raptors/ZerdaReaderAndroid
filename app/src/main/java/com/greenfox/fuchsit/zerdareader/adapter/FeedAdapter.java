package com.greenfox.fuchsit.zerdareader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by regnisalram on 1/24/17.
 */
public class FeedAdapter extends ArrayAdapter<NewsItem> {

    public FeedAdapter(Context context) {
        super(context, 0, new ArrayList<NewsItem>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NewsItem newsItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.newsitem, parent, false);
        }
        // Lookup view for data population
        TextView titleTW = (TextView) convertView.findViewById(R.id.title);

        // Populate the data into the template view using the data object
        titleTW.setText(newsItem.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }
}
