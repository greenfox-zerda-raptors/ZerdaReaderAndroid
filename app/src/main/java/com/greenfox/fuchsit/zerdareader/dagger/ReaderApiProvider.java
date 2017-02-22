package com.greenfox.fuchsit.zerdareader.dagger;

import android.app.Application;
import android.util.Log;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApi;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anna on 17/01/30.
 */
@Module
public class ReaderApiProvider {

    @Inject
    Application application;

    @Provides
    public ReaderApiInterface provideApiInterface() {
        if(BuildConfig.DEBUG) {
            return new MockServer();
        } else {
            return ReaderApi.getClient().create(ReaderApiInterface.class);
        }
    }
}
