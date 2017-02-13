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
import com.greenfox.fuchsit.zerdareader.dialog.FavoriteErrorDialog;
import com.greenfox.fuchsit.zerdareader.model.FavoriteHandler;
import com.greenfox.fuchsit.zerdareader.model.FavoriteRequest;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

/**
 * Created by regnisalram on 1/30/17.
 */

public class DetailedPageActivity extends AppCompatActivity implements FavoriteErrorDialog.FavoriteErrorListener {

    TextView article;
    NewsItem newsItem;
    MenuItem favoriteStar;
    MenuItem notFavoriteStar;
    boolean isItemFavorite;

    FavoriteRequest favoriteRequest;

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
        favoriteRequest = new FavoriteRequest(newsItem.getId());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            break;
            case R.id.add_favorite:
                addFavorite();
                break;
            case R.id.remove_favorite:
                removeFavorite();
                break;
        }
        return true;
    }

    private void removeFavorite() {
        if (favoriteHandler.deleteFavoriteCall()) {
            newsItem.setFavorite(false);
            Toast.makeText(DetailedPageActivity.this.getBaseContext(),"Removed from Favorites",Toast.LENGTH_LONG).show();
            invalidateOptionsMenu();
        } else {
            final String message = "Couldn't remove favorite at this time. Try again?";
            final boolean isFavoriting = false;
            FavoriteErrorDialog newFragment = new FavoriteErrorDialog(message, isFavoriting);
            newFragment.show(getSupportFragmentManager(), "deleteFavoriteError");

            invalidateOptionsMenu();
        }
    }

    private void addFavorite() {
        if (favoriteHandler.createFavoriteCall()) {
            newsItem.setFavorite(true);
            Toast.makeText(DetailedPageActivity.this.getBaseContext(),"Marked as Favorite",Toast.LENGTH_LONG).show();
            invalidateOptionsMenu();
        } else {
            final String message = "Couldn't save favorite at this time. Try again?";
            final boolean isFavoriting = true;
            FavoriteErrorDialog newFragment = new FavoriteErrorDialog(message, isFavoriting);
            newFragment.show(getSupportFragmentManager(), "createFavoriteError");
            invalidateOptionsMenu();
        }
    }

    @Override
    public void onDialogPositiveClick(android.support.v4.app.DialogFragment dialog, boolean isFavoriting, NewsItem newsItem) {
        if (isFavoriting) {
            addFavorite();
        } else {
            removeFavorite();
        }
    }
}


