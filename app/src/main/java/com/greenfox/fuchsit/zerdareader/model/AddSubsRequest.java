package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 02. 09..
 */

public class AddSubsRequest {
    @SerializedName("feed")
    private String url;

    public AddSubsRequest() {
    }

    public AddSubsRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
