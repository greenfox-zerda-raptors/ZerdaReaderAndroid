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
        setSupportActionBar(myToolbar);
        
        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra("newsItem");

        article = (TextView) findViewById(R.id.description);
        article.setText(newsItem.getDescription());
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.favorite:
                Toast.makeText(this, "You must be my lucky star", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}


