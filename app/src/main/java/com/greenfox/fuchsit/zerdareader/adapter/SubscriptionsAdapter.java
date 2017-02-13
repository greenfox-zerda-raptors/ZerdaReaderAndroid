package com.greenfox.fuchsit.zerdareader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.ManageSubscriptionsActivity;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;

import java.util.ArrayList;

/**
 * Created by Zsuzska on 2017. 02. 08..
 */

public class SubscriptionsAdapter extends ArrayAdapter<SubscriptionModel> {

    ManageSubscriptionsActivity manageSubscriptionsActivity;

    public SubscriptionsAdapter(Context context) {
        super(context, 0, new ArrayList<SubscriptionModel>());
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final SubscriptionModel subscriptionModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subscription, parent, false);
        }
        // Lookup view for data population
        TextView subsName = (TextView) convertView.findViewById(R.id.subscription_name);
        ImageView trashCan = (ImageView) convertView.findViewById(R.id.trashcan);
        trashCan.setTag(new Integer(position));
        trashCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: FIX THIS!!!
//                manageSubscriptionsActivity.showDeleteDialog(v, Integer.valueOf(v.getTag().toString()));
                Toast.makeText(getContext(), "Trashcan clicked: " + v.getTag().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        // Populate the data into the template view using the data object
        subsName.setText(subscriptionModel.getName());
        trashCan.setImageResource(R.drawable.delete);


        // Return the completed view to render on screen
        return convertView;
    }
}
