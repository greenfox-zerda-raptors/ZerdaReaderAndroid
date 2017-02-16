package com.greenfox.fuchsit.zerdareader.dagger;

import com.greenfox.fuchsit.zerdareader.activity.FeedFragment;
import com.greenfox.fuchsit.zerdareader.activity.LoginActivity;
import com.greenfox.fuchsit.zerdareader.activity.SignupActivity;
import com.greenfox.fuchsit.zerdareader.backgroundSync.BackgroundSyncReceiver;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Anna on 17/01/25.
 */
@Singleton
@Component(modules = {ReaderApiProvider.class})
public interface MockServerComponent {
    void inject(LoginActivity loginActivity);
    void inject(FeedFragment feedFragment);
    void inject(SignupActivity signupActivity);
    void inject(BackgroundSyncReceiver backgroundSyncReceiver);
}
