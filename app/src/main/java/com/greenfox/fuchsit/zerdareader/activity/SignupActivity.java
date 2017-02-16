package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anna on 17/02/06.
 */

public class SignupActivity extends BaseActivity {

    Button registerButton;
    EditText emailToReg, passwordToReg, passwordAgain;
    TextInputLayout textInputLayout;

    @Inject
    ReaderApiInterface apiService;

    SharedPreferences loginData;

    LoginRequest loginRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        emailToReg = (EditText) findViewById(R.id.userName);
        passwordToReg = (EditText) findViewById(R.id.password);
        passwordAgain = (EditText) findViewById(R.id.passwordAgain);
        registerButton = (Button) findViewById(R.id.registerButton);
        textInputLayout = (TextInputLayout) findViewById(R.id.register_error_text);

        DaggerMockServerComponent.builder().build().inject(this);
    }

    public void register(View view) {
        if (areTextfieldsEmpty()) {
            textInputLayout.setError("Please fill in your email and password");
        } else if (!isEmailValid()){
            textInputLayout.setError("Please fill in a valid email address");
        } else if (!arePasswordsMatching()){
            textInputLayout.setError("Passwords do not match");
        } else {
            loginRequest = new LoginRequest(emailToReg.getText().toString(), passwordToReg.getText().toString());
            Call<UserResponse> call = apiService.signUpUser(loginRequest);

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();

                    if (userResponse.getResult().equals("success")) {
                        saveDataToSharedPreferences();
                        Toast.makeText(SignupActivity.this, "Saved", Toast.LENGTH_LONG).show();
                        goToMainActivity();
                    } else {
                        textInputLayout.setError("Email address already exists");
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }
    }

    private boolean arePasswordsMatching() {
        return passwordToReg.getText().toString().equals(passwordAgain.getText().toString());
    }

    private boolean areTextfieldsEmpty() {
        return emailToReg.getText().toString().equals("") || passwordToReg.getText().toString().equals("")
                || passwordAgain.getText().toString().equals("");
    }

    private boolean isEmailValid() {
        return emailToReg.getText().toString().contains("@") && emailToReg.getText().toString().contains(".");
    }

    private void saveDataToSharedPreferences() {
        loginData = PreferenceManager.getDefaultSharedPreferences(SignupActivity.this);
        final SharedPreferences.Editor editor = loginData.edit();

        editor.putString("username", emailToReg.getText().toString());
        editor.putString("password", passwordToReg.getText().toString());
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

    private void goToMainActivity() {
        Intent i = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(i);
    }
}
