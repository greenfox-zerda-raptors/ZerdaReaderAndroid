package com.greenfox.fuchsit.zerdareader.event;

import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;

/**
 * Created by Zsuzska on 2017. 02. 16..
 */

public class OkDeleteSubscriptionEvent {

    SubscriptionModel subscriptionModel;

    public OkDeleteSubscriptionEvent(SubscriptionModel subscriptionModel) {

        this.subscriptionModel = subscriptionModel;
    }
    public SubscriptionModel getSubscriptionModel() {
        return subscriptionModel;
    }
}
