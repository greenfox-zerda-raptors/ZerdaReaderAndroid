package com.greenfox.fuchsit.zerdareader.server;

import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */

public class MockServer implements ReaderApiInterface {
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
