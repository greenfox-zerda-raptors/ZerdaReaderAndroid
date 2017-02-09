package com.greenfox.fuchsit.zerdareader.syncService;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Anna on 17/02/09.
 */

public class BackgroundSyncService extends IntentService {
    public static final String TRANSACTION_DONE = "com.geenfox.fuchsit.zerdareader.TRASACTION_DONE";

    public BackgroundSyncService() {
        super(BackgroundSyncService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("BackgroundSyncService", "Service started");
        String passedURL =  intent.getStringExtra("url");
        updateNewsItems(passedURL);
        Log.e("BackgroundSyncService", "Service stopped");
    }
}
