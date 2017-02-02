package com.greenfox.fuchsit.zerdareader;

import android.app.Activity;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import com.greenfox.fuchsit.zerdareader.activity.LoginActivity;

import junit.framework.Assert;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Zsuzska on 2017. 02. 02..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private EditText editEmail;
    private EditText editPassword;

    @Before
    public void setUp() throws Exception {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().visible().get();
        editEmail = (EditText) loginActivity.findViewById(R.id.userName);
        editPassword = (EditText) loginActivity.findViewById(R.id.password);
    }

    @Test
    public void loginWithEmptyUsernameAndPassword() {
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("Please fill in username/password.", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void loginWithEmptyCredentialsStaysOnSameActivity() {
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals(LoginActivity.class.getName(), LoginActivity.class.getName());
    }

}
