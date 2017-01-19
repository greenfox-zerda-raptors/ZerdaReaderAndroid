package com.greenfox.fuchsit.zerdareader;

import android.app.Activity;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.activity.HelloWorldActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class HelloWorldActivityTest {

    @Test
    public void testClickButton() throws Exception {
        Activity activity = Robolectric.buildActivity(HelloWorldActivity.class).create().get();
        Button button = (Button) activity.findViewById(R.id.button);
        TextView results = (TextView) activity.findViewById(R.id.text_view);
        button.performClick();
        String resultsText = results.getText().toString();

        assertThat(resultsText, equalTo("Hello Fuchsit!"));
    }
}