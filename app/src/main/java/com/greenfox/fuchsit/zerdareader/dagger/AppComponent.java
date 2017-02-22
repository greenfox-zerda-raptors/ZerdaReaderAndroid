package com.greenfox.fuchsit.zerdareader.dagger;

import com.greenfox.fuchsit.zerdareader.server.MockServer;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by regnisalram on 2/16/17.
 */

@Singleton
@Component(modules = {ApplicationProvider.class})
public interface AppComponent {
    void inject (MockServer mockServer);
    void inject (ReaderApiProvider readerApiProvider);
}
