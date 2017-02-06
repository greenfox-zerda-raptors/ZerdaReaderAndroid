package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by regnisalram on 2/6/17.
 */

public class UpdateRequest {

    @SerializedName("item_id")
    private long item_id;
    @SerializedName("opened")
    private int opened;

    public UpdateRequest() {
    }

    public UpdateRequest(long item_id, int opened) {
        this.item_id = item_id;
        this.opened = opened;
    }

    public long getItem_id() {
        return item_id;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }
}
