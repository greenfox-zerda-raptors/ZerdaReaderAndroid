package com.greenfox.fuchsit.zerdareader.activityTests;

import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.DetailedPageActivity;
import com.greenfox.fuchsit.zerdareader.activity.MainActivity;
import com.greenfox.fuchsit.zerdareader.activity.SignupActivity;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by regnisalram on 2/14/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class DetailedPageActivityTest {

    NewsItem newsItem;

    @Before
    public void setUp() throws Exception {
        newsItem = new NewsItem(1, "Pofont nem, maximum orrpöckölést kap az európai elit a francia elnökválasztáson",
                "Candy canes danish marzipan cookie caramels jelly beans. Sweet roll lemon drops marzipan cake jelly soufflé tart halvah jujubes. Jelly jelly gummies. Sweet roll pie topping croissant topping gingerbread chocolate cake. Sweet roll macaroon candy canes tart caramels. Tart gummies carrot cake muffin cupcake caramels chocolate bar. Jelly sugar plum chocolate macaroon candy croissant. Soufflé icing apple pie. Dragée fruitcake tart lollipop dessert cupcake lemon drops jelly beans macaroon. Caramels jelly-o soufflé sweet roll halvah cheesecake bear claw bear claw. Candy canes cotton candy cheesecake. Donut cupcake marshmallow. Caramels bonbon sweet.",
                1487079219, "Fox Crunch", false, false);
    }

    @Test
    public void testMenu() throws Exception {
        DetailedPageActivity detailedPageActivity = Robolectric.buildActivity(DetailedPageActivity.class).create().visible().get();
        Toolbar toolbar = (Toolbar) detailedPageActivity.findViewById(R.id.my_toolbar);
        ShadowActivity shadowActivity = shadowOf(detailedPageActivity);

        shadowActivity.onCreateOptionsMenu(toolbar.getMenu());

        if (newsItem.isFavorite()) {
            assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.remove_favorite).isVisible());
        } else {
            assertTrue(shadowActivity.getOptionsMenu().findItem(R.id.add_favorite).isVisible());
        }
    }


}


