package com.greenfox.fuchsit.zerdareader.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by regnisalram on 2/16/17.
 */

@Module
public class ApplicationProvider {

    Application mApplication;

    public ApplicationProvider(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }
}

