package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

/**
 * Created by regnisalram on 1/30/17.
 */

public class DetailedPageActivity extends AppCompatActivity {

    TextView article;
    NewsItem newsItem;

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
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detailed_view_toolbar_menu, menu);
        return true;
    }

    public void markAsFavorite(NewsItem newsItem) {
        newsItem.setFavorite(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            break;
            case R.id.favorite:
                markAsFavorite(newsItem);
                Toast.makeText(this,"Marked as Favorite",Toast.LENGTH_LONG).show();
            break;
        }
        return true;
    }
}


