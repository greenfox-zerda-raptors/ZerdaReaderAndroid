package com.greenfox.fuchsit.zerdareader.activityTests;

import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.greenfox.fuchsit.zerdareader.BuildConfig;
import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.MainActivity;
import com.greenfox.fuchsit.zerdareader.activity.SignupActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Anna on 17/02/09.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SignupActivityTest {

    SignupActivity signupActivity;
    TextInputLayout errorMessage;
    EditText password, passwordAgain, userName;

    @Before
    public void setUp() throws Exception {
        signupActivity = Robolectric.buildActivity(SignupActivity.class).create().get();
        errorMessage = (TextInputLayout) signupActivity.findViewById(R.id.register_error_text);
        password = (EditText) signupActivity.findViewById(R.id.password);
        passwordAgain = (EditText) signupActivity.findViewById(R.id.passwordAgain);
        userName = (EditText) signupActivity.findViewById(R.id.userName);
    }

    @Test
    public void testSignupWithEmptyUsernameAndPassword() throws Exception{
        signupActivity.findViewById(R.id.registerButton).performClick();
        assertEquals("Please fill in username and password", errorMessage.getError().toString());
    }

    @Test
    public void testIfPasswordsDoNotMatch() throws Exception{
        userName.setText("username");
        password.setText("password");
        passwordAgain.setText("Password");
        signupActivity.findViewById(R.id.registerButton).performClick();
        assertEquals("Passwords do not match", errorMessage.getError().toString());
    }

    @Test
    public void testIfRegistrationSuccessfulRedirectToMainActivity() throws Exception {
        userName.setText("username");
        password.setText("password");
        passwordAgain.setText("password");
        signupActivity.findViewById(R.id.registerButton).performClick();
        assertEquals(MainActivity.class.getName(), shadowOf(signupActivity).getNextStartedActivity().getComponent().getClassName());
    }
}
