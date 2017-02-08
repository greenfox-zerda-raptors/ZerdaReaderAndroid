package com.greenfox.fuchsit.zerdareader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;

import java.util.ArrayList;

/**
 * Created by Zsuzska on 2017. 02. 08..
 */

public class SubscriptionsAdapter extends ArrayAdapter<SubscriptionModel> {

    public SubscriptionsAdapter(Context context) {
        super(context, 0, new ArrayList<SubscriptionModel>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SubscriptionModel subscriptionModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subscription, parent, false);
        }
        // Lookup view for data population
        TextView url = (TextView) convertView.findViewById(R.id.name);
        ImageView trashCan = (ImageView) convertView.findViewById(R.id.trashcan);

        // Populate the data into the template view using the data object
        url.setText(subscriptionModel.getUrl());


        // Return the completed view to render on screen
        return convertView;
    }
}
