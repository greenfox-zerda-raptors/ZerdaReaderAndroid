package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 02. 09..
 */

public class AddSubsResponse {
    @SerializedName("result")
    private String result;
    @SerializedName("id")
    private Long id;
    @SerializedName("message")
    private String message;

    public AddSubsResponse() {
    }

    public AddSubsResponse(String result, Long id) {
        this.result = result;
        this.id = id;
    }

    public AddSubsResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
