package com.greenfox.fuchsit.zerdareader.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dialog.FavoriteErrorDialog;
import com.greenfox.fuchsit.zerdareader.model.FavoriteHandler;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by regnisalram on 1/24/17.
 */
public class FeedAdapter extends ArrayAdapter<NewsItem> implements FavoriteErrorDialog.FavoriteErrorListener{

    FavoriteHandler favoriteHandler;
    Context context;

    public FeedAdapter(Context context) {
        super(context, 0, new ArrayList<NewsItem>());
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NewsItem newsItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.newsitem, parent, false);
        }

        ImageView email = (ImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView feedName = (TextView) convertView.findViewById(R.id.feed_name);
        ImageView star = (ImageView) convertView.findViewById(R.id.favorite);

        if (newsItem.isOpened()) {
            email.setImageResource(R.drawable.email_open);
            convertView.setBackgroundResource(R.color.light_grey);
        } else {
            email.setImageResource(R.drawable.email);
            convertView.setBackgroundResource(R.color.alabaster);
        }

        if (newsItem.isFavorite()) {
            star.setImageResource(R.drawable.ic_star);
        } else {
            star.setImageResource(R.drawable.not_fav_star);
        }

        title.setText(newsItem.getTitle());
        feedName.setText(newsItem.getFeedName());

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsItem.isFavorite()) {
                    removeFavorite(newsItem);
                } else {
                    addFavorite(newsItem);
                }
            }
        });

        return convertView;
    }


    private void addFavorite(NewsItem newsItem) {
        favoriteHandler = new FavoriteHandler(context);
        if (favoriteHandler.createFavoriteCall()) {
            newsItem.setFavorite(true);
            Toast.makeText(context,"Marked as Favorite",Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        } else {
            final String message = "Couldn't save favorite at this time. Try again?";
            final boolean isFavoriting = true;
            FavoriteErrorDialog errorDialog = new FavoriteErrorDialog(message, isFavoriting);
//            errorDialog.show(, "createFavoriteError");
        }
    }

    private void removeFavorite(NewsItem newsItem) {
        favoriteHandler = new FavoriteHandler(context);
        if (favoriteHandler.deleteFavoriteCall()) {
            newsItem.setFavorite(false);
            Toast.makeText(context,"Removed from Favorites",Toast.LENGTH_LONG).show();
            notifyDataSetChanged();        }
        else {
            final String message = "Couldn't remove favorite at this time. Try again?";
            final boolean isFavoriting = false;
            FavoriteErrorDialog errorDialog = new FavoriteErrorDialog(message, isFavoriting);
//            errorDialog.show(, "deleteFavoriteError");
        }

    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog, boolean isFavoriting, @Nullable NewsItem newsItem) {
        if (isFavoriting) {
            addFavorite(newsItem);
        } else {
            removeFavorite(newsItem);
        }
    }
}


