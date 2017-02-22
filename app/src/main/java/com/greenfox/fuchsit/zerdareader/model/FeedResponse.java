package com.greenfox.fuchsit.zerdareader.model;

import java.util.ArrayList;

/**
 * Created by regnisalram on 2/22/17.
 */

public class FeedResponse {

    public ArrayList<NewsItem> feed;

    public FeedResponse() {
    }

    public FeedResponse(ArrayList<NewsItem> feed) {
        this.feed = feed;
    }
}
