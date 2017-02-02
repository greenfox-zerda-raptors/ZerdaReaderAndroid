package com.greenfox.fuchsit.zerdareader.server;

import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */

public class MockServer implements ReaderApiInterface {
    @Override
    public Call<ArrayList<NewsItem>> getFavouriteNewsItems() {
        return new MockCall<ArrayList<NewsItem>>() {
            @Override
            public void enqueue(Callback<ArrayList<NewsItem>> callback) {
                ArrayList<NewsItem> newsItems = new ArrayList<>();
                newsItems.add(new NewsItem("Favourite 1", "you are my favourite"));
                newsItems.add(new NewsItem("Favourite 2", "you are my favourite"));
                newsItems.add(new NewsItem("Favourite 3", "you are my favourite"));
                newsItems.add(new NewsItem("Favourite 4", "you are my favourite"));
                newsItems.add(new NewsItem("Favourite 5", "you are my favourite"));
                newsItems.add(new NewsItem("Favourite 6", "you are my favourite"));
                Response<ArrayList<NewsItem>> r = Response.success(newsItems);
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public MockCall<ArrayList<NewsItem>> getNewsItems() {
        return new MockCall<ArrayList<NewsItem>>() {
            @Override
            public void enqueue(Callback<ArrayList<NewsItem>> callback) {
                ArrayList<NewsItem> newsItems = new ArrayList<>();
                newsItems.add(new NewsItem("Title 1", "blablabla"));
                newsItems.add(new NewsItem("Title 2", "blablabla"));
                newsItems.add(new NewsItem("Title 3", "blablabla"));
                newsItems.add(new NewsItem("Title 4", "blablabla"));
                newsItems.add(new NewsItem("Title 5", "blablabla"));
                newsItems.add(new NewsItem("Title 6", "blablabla"));
                Response<ArrayList<NewsItem>> r = Response.success(newsItems);
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public MockCall<UserResponse> loginUser(String username, String password) {
        return new MockCall<UserResponse>() {
            @Override
            public void enqueue(Callback<UserResponse> callback) {
                Response<UserResponse> r = Response.success(new UserResponse());
                callback.onResponse(this, r);
            }
        };
    }
}


