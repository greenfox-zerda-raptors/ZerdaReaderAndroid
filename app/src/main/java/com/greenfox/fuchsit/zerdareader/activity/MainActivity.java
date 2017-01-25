package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListAdapter;

import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.widget.ListView;


import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends FragmentActivity {

    SharedPreferences sharedPreferences;
    ReaderApiInterface readerApiInterface;
    Retrofit retrofit;
    FeedAdapter adapter;
    ListView feed;
    ArrayList<NewsItem> newsItems;
    FeedFragment feedFragment;

    public void showNewsItems() {
        readerApiInterface = retrofit.create(ReaderApiInterface.class);
        readerApiInterface.getNewsItems().enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                adapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        checkIfLoggedIn();
        setContentView(R.layout.activity_main);

        feed = (ListView) findViewById(android.R.id.list);

        newsItems = new ArrayList<>();
        newsItems.add(new NewsItem("Title 1", "blablabla"));
        newsItems.add(new NewsItem("Title 2", "blablabla"));
        newsItems.add(new NewsItem("Title 3", "blablabla"));
        newsItems.add(new NewsItem("Title 4", "blablabla"));
        newsItems.add(new NewsItem("Title 5", "blablabla"));
        newsItems.add(new NewsItem("Title 6", "blablabla"));

        adapter = new FeedAdapter(this, newsItems);
        feed.setAdapter(adapter);
    }

    private void checkIfLoggedIn() {
        boolean islogin = sharedPreferences.getBoolean("isLogin", false);
        if(!islogin) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
