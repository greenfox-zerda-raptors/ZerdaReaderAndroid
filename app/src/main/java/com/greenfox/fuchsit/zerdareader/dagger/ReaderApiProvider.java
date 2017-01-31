package com.greenfox.fuchsit.zerdareader.dagger;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApi;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anna on 17/01/30.
 */
@Module
public class ReaderApiProvider {

    @Provides
    public ReaderApiInterface provideApiInterface() {
        if(BuildConfig.DEBUG) {
            return new MockServer();
        } else {
            return ReaderApi.getClient().create(ReaderApiInterface.class);
        }
    }
}
