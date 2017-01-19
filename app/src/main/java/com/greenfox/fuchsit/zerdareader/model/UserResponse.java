package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public class UserResponse {

    @SerializedName("id")
    private long id;
    @SerializedName("result")
    private boolean success;
    @SerializedName("token")
    private String token;

}
