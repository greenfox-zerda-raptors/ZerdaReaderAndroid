package com.greenfox.fuchsit.zerdareader.syncService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Anna on 17/02/16.
 */

public class BackgroundSyncReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("BackgroundSyncService", "Service received");
        updateFragment(intent);
    }
}
