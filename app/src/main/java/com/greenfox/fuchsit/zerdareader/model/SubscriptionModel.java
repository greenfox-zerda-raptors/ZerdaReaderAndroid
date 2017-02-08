package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zsuzska on 2017. 02. 08..
 */

public class SubscriptionModel implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("feed")
    private String url;
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;

    public SubscriptionModel() {
    }

    public SubscriptionModel(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public SubscriptionModel(String url) {
        this.url = url;
    }

    public SubscriptionModel(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public SubscriptionModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
