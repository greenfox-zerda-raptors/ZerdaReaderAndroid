package com.greenfox.fuchsit.zerdareader.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.FavoriteHandler;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by regnisalram on 1/24/17.
 */
public class FeedAdapter extends ArrayAdapter<NewsItem> {

    FavoriteHandler favoriteHandler;
    Context context;

    public FeedAdapter(Context context) {
        super(context, 0, new ArrayList<NewsItem>());
        this.context = context;
        favoriteHandler = new FavoriteHandler(context);
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
                    favoriteHandler.deleteFavoriteCall(newsItem.getId());
                } else {
                    favoriteHandler.createFavoriteCall(newsItem.getId());
                }
            }
        });

        return convertView;
    }

    public void toggleFavoriteById(long itemId) {
        NewsItem newsItem;
        for (int i = 0; i < this.getCount(); i++) {
            newsItem = this.getItem(i);
            if (newsItem.getId() == itemId) {
                newsItem.setFavorite(!newsItem.isFavorite());
                if (newsItem.isFavorite()) {
                    Toast.makeText(context,"Marked as Favorite",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Removed from Favorites",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        notifyDataSetChanged();
    }
}


