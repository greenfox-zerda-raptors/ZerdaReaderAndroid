package com.greenfox.fuchsit.zerdareader.activityTests;

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
import com.greenfox.fuchsit.zerdareader.adapter.FeedAdapter;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

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
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static android.R.id.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.manage_subscriptions).isVisible());
        assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.settings).isVisible());
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

    @Test
    public void testFavoritedStarLoadedRight() throws Exception {
        MainActivity mActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        FeedFragment fragment = FeedFragment.newInstance(1);
        assertNotNull(fragment);
        fragment.onAttach(mActivity);

        SupportFragmentTestUtil.startVisibleFragment(fragment);
        View feed = fragment.getView().findViewById(R.id.feed);
        ListView newsItemList = (ListView) fragment.getView().findViewById(android.R.id.list);
        ShadowListView shadowListView = Shadows.shadowOf(newsItemList);
        FeedAdapter adapter = new FeedAdapter(mActivity);
        newsItemList.setAdapter(adapter);

        NewsItem newsItem = new NewsItem(1, "Pofont nem, maximum orrpöckölést kap az európai elit a francia elnökválasztáson",
                "Candy canes danish marzipan cookie caramels jelly beans. Sweet roll lemon drops marzipan cake jelly soufflé tart halvah jujubes. Jelly jelly gummies. Sweet roll pie topping croissant topping gingerbread chocolate cake. Sweet roll macaroon candy canes tart caramels. Tart gummies carrot cake muffin cupcake caramels chocolate bar. Jelly sugar plum chocolate macaroon candy croissant. Soufflé icing apple pie. Dragée fruitcake tart lollipop dessert cupcake lemon drops jelly beans macaroon. Caramels jelly-o soufflé sweet roll halvah cheesecake bear claw bear claw. Candy canes cotton candy cheesecake. Donut cupcake marshmallow. Caramels bonbon sweet.",
                1487079219, "Fox Crunch", false, false);

        adapter.add(newsItem);
        shadowListView.populateItems();


    }

    @Test
    public void testNotFavoritedStarLoadedRight() throws Exception {

    }
}