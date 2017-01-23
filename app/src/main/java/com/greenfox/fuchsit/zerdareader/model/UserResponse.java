package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */
public class UserResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("result")
    private boolean success;
    @SerializedName("token")
    private String token;

    public UserResponse() {
    }

    public UserResponse(Long id, boolean success, String token) {
        this.id = id;
        this.success = success;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}