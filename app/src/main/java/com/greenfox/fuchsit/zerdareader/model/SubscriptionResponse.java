package com.greenfox.fuchsit.zerdareader.model;

import java.util.ArrayList;

/**
 * Created by Zsuzska on 2017. 02. 23..
 */

public class SubscriptionResponse {
    public ArrayList<SubscriptionModel> subscriptions;

    public SubscriptionResponse() {
    }

    public SubscriptionResponse(ArrayList<SubscriptionModel> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
