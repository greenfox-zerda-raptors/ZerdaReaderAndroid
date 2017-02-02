package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        
        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra("newsItem");

        article = (TextView) findViewById(R.id.description);
        article.setText(newsItem.getDescription());
    }
}
