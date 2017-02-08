package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by regnisalram on 2/8/17.
 */

public class FavoriteRequest {

    @SerializedName("item_id")
    private int itemId;

    public FavoriteRequest() {
    }

    public FavoriteRequest(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
