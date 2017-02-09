package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zsuzska on 2017. 02. 09..
 */

public class SubsDeleteResponse {
    @SerializedName("result")
    private String result;


    public SubsDeleteResponse() {
    }

    public SubsDeleteResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
