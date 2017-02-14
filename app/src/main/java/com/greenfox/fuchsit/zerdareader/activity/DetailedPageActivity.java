package com.greenfox.fuchsit.zerdareader.activity;

<<<<<<< HEAD
import android.content.SharedPreferences;
=======
import android.net.Uri;
>>>>>>> 2cc9d57ab24b52dd4f1b6fbb260f794aa5b70aaa
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.event.FavoriteSavedEvent;
import com.greenfox.fuchsit.zerdareader.model.FavoriteHandler;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

<<<<<<< HEAD
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
=======
import org.joda.time.LocalDate;
>>>>>>> 2cc9d57ab24b52dd4f1b6fbb260f794aa5b70aaa

/**
 * Created by regnisalram on 1/30/17.
 */

public class DetailedPageActivity extends AppCompatActivity {

    TextView title, feedName, date, article;
    NewsItem newsItem;
    MenuItem favoriteStar;
    MenuItem notFavoriteStar;
    boolean isItemFavorite;

    SharedPreferences sharedPreferences;

    FavoriteHandler favoriteHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Feed");
        myToolbar.setSubtitle("Back to your feed");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newsItem = (NewsItem) getIntent().getSerializableExtra("newsItem");

        title = (TextView) findViewById(R.id.title);
        title.setText(newsItem.getTitle());

        feedName = (TextView) findViewById(R.id.feed_name);
        feedName.setText(newsItem.getFeedName());

        String dateString = getDate(newsItem.getCreated());
        date = (TextView) findViewById(R.id.date);
        date.setText(String.format("posted on %s", dateString));

        article = (TextView) findViewById(R.id.article);
        article.setText(newsItem.getDescription());

        DaggerMockServerComponent.builder().build().inject(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        favoriteHandler = new FavoriteHandler(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detailed_view_toolbar_menu, menu);

        favoriteStar = menu.findItem(R.id.remove_favorite);
        notFavoriteStar = menu.findItem(R.id.add_favorite);

        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        isItemFavorite = newsItem.isFavorite();
        if (isItemFavorite) {
            menu.findItem(R.id.add_favorite).setVisible(false);
            menu.findItem(R.id.remove_favorite).setVisible(true);
        } else {
            menu.findItem(R.id.add_favorite).setVisible(true);
            menu.findItem(R.id.remove_favorite).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
<<<<<<< HEAD
            break;
            case R.id.add_favorite:
                favoriteHandler.createFavoriteCall(newsItem.getId());
                break;
            case R.id.remove_favorite:
                favoriteHandler.deleteFavoriteCall(newsItem.getId());
                break;
=======
                break;
            case R.id.remove_favorite:
                isItemFavorite = false;
                newsItem.setFavorite(false);
                Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_LONG).show();
                invalidateOptionsMenu();
                break;

            case R.id.add_favorite:
                isItemFavorite = true;
                newsItem.setFavorite(true);
                Toast.makeText(this, "Marked as Favorite", Toast.LENGTH_LONG).show();
                invalidateOptionsMenu();
                break;

>>>>>>> 2cc9d57ab24b52dd4f1b6fbb260f794aa5b70aaa
        }
        return true;
    }

<<<<<<< HEAD
    @Subscribe
    public void onFavoriteSavedEvent(FavoriteSavedEvent favoriteSavedEvent) {
        newsItem.setFavorite(!newsItem.isFavorite());
        if (newsItem.isFavorite()) {
            Toast.makeText(DetailedPageActivity.this.getBaseContext(),"Marked as Favorite",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DetailedPageActivity.this.getBaseContext(),"Removed from Favorites",Toast.LENGTH_LONG).show();
        }
        invalidateOptionsMenu();
=======
    private String getDate(long unixTimeStamp) {
        LocalDate localDate = new LocalDate(unixTimeStamp * 1000);
        return localDate.toString("YYYY. MM. DD");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
>>>>>>> 2cc9d57ab24b52dd4f1b6fbb260f794aa5b70aaa
    }
}


