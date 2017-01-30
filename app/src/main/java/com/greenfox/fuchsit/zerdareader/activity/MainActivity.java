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
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApi;
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
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends FragmentActivity {

    SharedPreferences sharedPreferences;
    ReaderApiInterface readerApiInterface;
    ReaderApi api;
    FeedAdapter adapter;
    ListView feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        checkIfLoggedIn();
        setContentView(R.layout.activity_main);

        feed = (ListView) findViewById(android.R.id.list);

        adapter = new FeedAdapter(this);
        feed.setAdapter(adapter);

        showNewsItems();
    }

    public void showNewsItems() {

        //this is commented out because we will switch to this line when we'll use the real server
//        final ReaderApiInterface apiService = api.getClient().create(ReaderApiInterface.class);
        MockServer apiService = new MockServer();
        Call<ArrayList<NewsItem>> call = apiService.getNewsItems();

        call.enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                adapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {

            }
        });
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
