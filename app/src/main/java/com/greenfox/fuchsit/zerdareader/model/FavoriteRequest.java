package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by regnisalram on 2/8/17.
 */

public class FavoriteRequest {

    @SerializedName("item_id")
    private long itemId;

    public FavoriteRequest() {
    }

    public FavoriteRequest(long itemId) {
        this.itemId = itemId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
