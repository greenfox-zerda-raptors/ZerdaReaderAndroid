package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Feed");
        myToolbar.setSubtitle("Back to your feed");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra("newsItem");

        article = (TextView) findViewById(R.id.description);
        article.setText(newsItem.getDescription());
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_toolbar_menu, menu);
        MenuItem msItem = menu.findItem(R.id.manage_subscriptions);
        msItem.setVisible(false);
        MenuItem sItem = menu.findItem(R.id.settings);
        sItem.setVisible(false);
        MenuItem logoutItem = menu.findItem(R.id.logout);
        logoutItem.setVisible(false);
        MenuItem refreshItem = menu.findItem(R.id.refresh);
        refreshItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.favorite:
                Toast.makeText(this, "You must be my lucky star", Toast.LENGTH_LONG).show();
                break;
            case R.id.manage_subscriptions:
                startActivity(new Intent(this, ManageSubscriptionsActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }
}


