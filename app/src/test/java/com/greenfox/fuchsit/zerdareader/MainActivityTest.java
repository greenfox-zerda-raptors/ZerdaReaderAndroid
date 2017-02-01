package com.greenfox.fuchsit.zerdareader;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;

import com.greenfox.fuchsit.zerdareader.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Anna on 17/02/01.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {

    private static MainActivity mActivity;

    @Before
    public void setMainActivity() {
        if (mActivity == null) {
            mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        }
    }
    @Test
    public void testMenu() throws Exception {
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.my_toolbar);
        ShadowActivity shadowActivity = shadowOf(mActivity);

        shadowActivity.onCreateOptionsMenu(toolbar.getMenu());

        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.refresh).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.favorite).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.manage_subscriptions).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.Settings).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.logout).isVisible());
    }
}
