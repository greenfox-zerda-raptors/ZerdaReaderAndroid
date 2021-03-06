package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.regex.Pattern;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    Button button;

    EditText editEmail, editPassword;
    TextView textView;

    TextInputLayout til;

    @Inject
    ReaderApiInterface apiService;

    SharedPreferences loginData;

    LoginRequest loginRequest;
    UserResponse userResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        textView = (TextView) findViewById(R.id.loginTitle);
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);
        til = (TextInputLayout) findViewById(R.id.error_text);

        DaggerMockServerComponent.builder().build().inject(this);
    }

    public void login(View view){

        if (isTextfieldsEmpty()) {
            til.setError("Please fill in your email and password");
        } else if (!isEmailAddressValid()){
            til.setError("Please fill in a valid email address");
        } else {
            loginRequest = new LoginRequest(editEmail.getText().toString(), editPassword.getText().toString());
            Call<UserResponse> call = apiService.loginUser(loginRequest);

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    userResponse = response.body();

                    checkCredentialsAndLogIn(userResponse);
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    
                }
            });
        }
    }


    private void saveDataToSharedPreferences() {
        loginData = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        final SharedPreferences.Editor editor = loginData.edit();

        editor.putString("token", userResponse.getToken());
        editor.putString("userName", editEmail.getText().toString());
        editor.putString("password", editPassword.getText().toString());
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

    private void loginWithCorrectData() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    private boolean isTextfieldsEmpty() {
        return editEmail.getText().toString().equals("") || editPassword.getText().toString().equals("");
    }

    private boolean isEmailAddressValid() {
        String email = editEmail.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean isLoginDataCorrect(String result) {
        return result.equals("success");
    }

    private void checkCredentialsAndLogIn(UserResponse userResponse) {
        if (!isLoginDataCorrect(userResponse.getResult())) {
            til.setError("Username or password is incorrect");
            
        } else {
            saveDataToSharedPreferences();
            Toast.makeText(LoginActivity.this, "Saved", Toast.LENGTH_LONG).show();
            loginWithCorrectData();
        }
    }

    public void redirectToSignup(View view) {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

}


