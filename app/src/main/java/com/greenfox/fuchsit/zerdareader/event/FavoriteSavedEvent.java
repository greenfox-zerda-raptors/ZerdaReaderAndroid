package com.greenfox.fuchsit.zerdareader.event;

/**
 * Created by regnisalram on 2/13/17.
 */

public class FavoriteSavedEvent {

    long item_id;

    public FavoriteSavedEvent(long item_id) {
        this.item_id = item_id;
    }

    public long getItem_id() {
        return item_id;
    }
}
