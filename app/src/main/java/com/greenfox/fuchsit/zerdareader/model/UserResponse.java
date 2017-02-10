package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */
public class UserResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("result")
    private String result;
    @SerializedName("token")
    private String token;
    @SerializedName("message")
    private String message;

    public UserResponse() {
    }

    public UserResponse(String token, String result, Long id) {
        this.id = id;
        this.result = result;
        this.token = token;
    }

    public UserResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public UserResponse(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



