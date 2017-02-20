package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by regnisalram on 2/6/17.
 */

public class UpdateRequest {

    @SerializedName("opened")
    private int opened;

    public UpdateRequest() {
    }

    public UpdateRequest(int opened) {
        this.opened = opened;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }
}
