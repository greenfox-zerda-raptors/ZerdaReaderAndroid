package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class SignupActivity extends AppCompatActivity {

    Button registerButton;
    EditText emailToReg, passwordToReg;
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
        registerButton = (Button) findViewById(R.id.registerButton);
        textInputLayout = (TextInputLayout) findViewById(R.id.register_error_text);

        DaggerMockServerComponent.builder().build().inject(this);
    }

    public void register(View view) {
        if (areTextfieldsEmpty()) {
            textInputLayout.setError("Please fill in username and password");
        } else {
            loginRequest = new LoginRequest(emailToReg.getText().toString(), passwordToReg.getText().toString());
            Call<UserResponse> call = apiService.signUpUser(loginRequest);

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
                    
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }
    }

    private boolean areTextfieldsEmpty() {
        return emailToReg.getText().toString().equals("") || passwordToReg.getText().toString().equals("");
    }
}
