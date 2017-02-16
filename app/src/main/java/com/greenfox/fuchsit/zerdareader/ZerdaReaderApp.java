package com.greenfox.fuchsit.zerdareader;

import android.app.Application;

import com.greenfox.fuchsit.zerdareader.dagger.AppComponent;
import com.greenfox.fuchsit.zerdareader.dagger.ApplicationProvider;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerAppComponent;

/**
 * Created by regnisalram on 2/16/17.
 */

public class ZerdaReaderApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .applicationProvider(new ApplicationProvider(this)).build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mNetComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

}
