package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 02. 09..
 */

public class SubsDeleteRequest {
    @SerializedName("id")
    private Long id;
    @SerializedName("result")
    private String result;

    public SubsDeleteRequest() {
    }

    public SubsDeleteRequest(Long id) {
        this.id = id;
    }

    public SubsDeleteRequest(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
