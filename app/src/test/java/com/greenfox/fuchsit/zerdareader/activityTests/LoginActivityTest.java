package com.greenfox.fuchsit.zerdareader.activityTests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.R;
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
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
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
    private TextInputLayout til;

    @Before
    public void setUp() throws Exception {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
        editEmail = (EditText) loginActivity.findViewById(R.id.email);
        editPassword = (EditText) loginActivity.findViewById(R.id.password);
        til = (TextInputLayout) loginActivity.findViewById(R.id.error_text);
    }

    @Test
    public void loginWithEmptyUsernameAndPassword() {
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals("Please fill in username and password", til.getError().toString());
    }

    @Test
    public void loginWithEmptyCredentialsStaysOnSameActivity() {
        loginActivity.findViewById(R.id.loginButton).performClick();
        Assert.assertEquals(LoginActivity.class.getName(), LoginActivity.class.getName());
    }

    @Test
    public void loginWithCorrectDataOpensNextActivity() {
        editEmail.setText("admin");
        editPassword.setText("fuchsit");

        loginActivity.findViewById(R.id.loginButton).performClick();

        Assert.assertEquals(MainActivity.class.getName(), shadowOf(loginActivity).getNextStartedActivity().getComponent().getClassName());
    }

    @Test
    public void testUserDataIsSavedToSharedPref() throws Exception {
        editEmail.setText("admin");
        editPassword.setText("fuchsit");
        loginActivity.findViewById(R.id.loginButton).performClick();

        Context context = RuntimeEnvironment.application.getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        assertEquals("admin", sharedPreferences.getString("username", "admin"));
        assertEquals("fuchsit", sharedPreferences.getString("password", "fuchsit"));
    }

}
