package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by regnisalram on 2/8/17.
 */

public class FavoriteResponse {

    @SerializedName("response")
    private String response;

    public FavoriteResponse() {
    }

    public FavoriteResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
