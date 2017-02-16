package com.greenfox.fuchsit.zerdareader.syncService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Anna on 17/02/16.
 */

public class BackgroundSyncReceiver extends BroadcastReceiver {

    public static final String TRANSACTION_DONE = "com.greenfox.fuchsit.zerdareader.TRANSACTION_DONE";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("BackgroundSyncReceiver", "Service triggered");
        Intent i = new Intent(context, BackgroundSyncService.class);
        context.startService(i);
    }
}
