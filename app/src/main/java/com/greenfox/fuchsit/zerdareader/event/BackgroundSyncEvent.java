package com.greenfox.fuchsit.zerdareader.event;

import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by Anna on 17/02/20.
 */

public class BackgroundSyncEvent {

    ArrayList<NewsItem> newsList;

    public BackgroundSyncEvent(ArrayList<NewsItem> newsList) {
        this.newsList = newsList;
    }

    public Object getNewsList() {
        return newsList;
    }
}
