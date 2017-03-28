package com.greenfox.fuchsit.zerdareader.event;

/**
 * Created by Anna on 17/02/23.
 */

public class BackSyncSettingEvent {

    public boolean enabled;

    public BackSyncSettingEvent(boolean b) {
        enabled = b;
    }
}
