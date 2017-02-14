package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by regnisalram on 1/30/17.
 */

public class DetailedPageActivity extends AppCompatActivity {

    TextView article;
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

        article = (TextView) findViewById(R.id.description);
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
            break;
            case R.id.add_favorite:
                favoriteHandler.createFavoriteCall(newsItem.getId());
                break;
            case R.id.remove_favorite:
                favoriteHandler.deleteFavoriteCall(newsItem.getId());
                break;
        }
        return true;
    }

    @Subscribe
    public void onFavoriteSavedEvent(FavoriteSavedEvent favoriteSavedEvent) {
        newsItem.setFavorite(!newsItem.isFavorite());
        if (newsItem.isFavorite()) {
            Toast.makeText(DetailedPageActivity.this.getBaseContext(),"Marked as Favorite",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DetailedPageActivity.this.getBaseContext(),"Removed from Favorites",Toast.LENGTH_LONG).show();
        }
        invalidateOptionsMenu();
    }
}


