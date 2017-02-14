package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zsuzska on 2017. 01. 24..
 */

public class NewsItem implements Serializable{
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("created")
    private long created;
    @SerializedName("feed_name")
    private String feedName;
    @SerializedName("feed_id")
    private long feedId;
    @SerializedName("favorite")
    private boolean favorite;
    @SerializedName("opened")
    private boolean opened;
    @SerializedName("url")
    private String url;
    

    public NewsItem() {
    }

    public NewsItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public NewsItem(String title, String description, long created, String feedName, long feedId, boolean favorite, boolean opened, String url) {
        this.title = title;
        this.description = description;
        this.created = created;
        this.feedName = feedName;
        this.feedId = feedId;
        this.favorite = favorite;
        this.opened = opened;
        this.url = url;
    }

    public NewsItem(String title, String description, long created, String feedName, boolean favorite, boolean opened) {
        this.title = title;
        this.description = description;
        this.created = created;
        this.feedName = feedName;
        this.favorite = favorite;
        this.opened = opened;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public long getFeedId() {
        return feedId;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


