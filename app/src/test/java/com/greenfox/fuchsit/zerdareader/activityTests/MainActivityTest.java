package com.greenfox.fuchsit.zerdareader.activityTests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.FeedFragment;
import com.greenfox.fuchsit.zerdareader.activity.LoginActivity;
import com.greenfox.fuchsit.zerdareader.activity.MainActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Anna on 17/02/01.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {

    private SharedPreferences sharedPreferences;

    @Before
    public void setupSharedPreference() {
        Context context = RuntimeEnvironment.application.getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Test
    public void testMenu() throws Exception {
        MainActivity mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.my_toolbar);
        ShadowActivity shadowActivity = shadowOf(mActivity);

        shadowActivity.onCreateOptionsMenu(toolbar.getMenu());

        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.refresh).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.favorite).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.manage_subscriptions).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.Settings).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.logout).isVisible());
    }

    @Test
    public void testIfSharedPrefContainsUser() throws Exception {
        sharedPreferences.edit().putString("testId", "12345").apply();
        sharedPreferences.edit().putBoolean("isLogin", true).apply();

        MainActivity mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        ViewPager viewPager = (ViewPager) mActivity.findViewById(R.id.pager);

        assertNotNull(viewPager);
    }

    @Test
    public void testNavigateToLogin() throws Exception {
        MainActivity mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        assertEquals(LoginActivity.class.getName(), shadowOf(mActivity).getNextStartedActivity().getComponent().getClassName());
    }

    @Test
    public void testSharedPrefEmptiedAfterLogout() throws Exception {
        sharedPreferences.edit().putString("username", "12345").apply();
        sharedPreferences.edit().putString("password", "12345").apply();

        MainActivity mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.my_toolbar);
        ShadowActivity shadowActivity = shadowOf(mActivity);
        shadowActivity.onCreateOptionsMenu(toolbar.getMenu());
        shadowActivity.clickMenuItem(R.id.logout);
//        MenuItem item = shadowActivity.getOptionsMenu().findItem(R.id.logout);
//        mActivity.onOptionsItemSelected(item);

        assertEquals("", sharedPreferences.getString("username", "default"));
        assertEquals("", sharedPreferences.getString("password", "default"));
    }

}
