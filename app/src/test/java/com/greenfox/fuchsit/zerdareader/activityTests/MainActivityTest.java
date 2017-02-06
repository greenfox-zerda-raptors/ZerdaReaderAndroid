package com.greenfox.fuchsit.zerdareader.activityTests;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.FeedFragment;
import com.greenfox.fuchsit.zerdareader.activity.LoginActivity;
import com.greenfox.fuchsit.zerdareader.activity.MainActivity;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.util.FragmentTestUtil.startFragment;

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

        assertNotNull(viewPager.getCurrentItem());
    }

    @Test
    public void testFragmentIsNotNullAndViewIsSameAsMainActivity() throws Exception {
        FeedFragment fragment = FeedFragment.newInstance(1);
        assertNotNull(fragment);
        MainActivity mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        View feed = mActivity.findViewById(R.id.feed);
        assertEquals(fragment.getView(), feed);
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

        assertEquals("", sharedPreferences.getString("username", "default"));
        assertEquals("", sharedPreferences.getString("password", "default"));
    }
}
