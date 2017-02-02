package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editUserName, editPassword;
    TextView textView;

    String username, password;
    @Inject
    ReaderApiInterface apiService;

    SharedPreferences loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        textView = (TextView) findViewById(R.id.loginTitle);
        editUserName = (EditText) findViewById(R.id.userName);
        editPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);

       DaggerMockServerComponent.builder().build().inject(this);
    }

    public void login(View view) {

        username = editUserName.getText().toString();
        password = editPassword.getText().toString();
        Call<UserResponse> call = apiService.loginUser(username, password);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user = response.body();

                loginData = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                final SharedPreferences.Editor editor = loginData.edit();

                editor.putString("userName", username);
                editor.putString("password", password);
                editor.putBoolean("isLogin", true);
                editor.apply();

                Toast.makeText(LoginActivity.this, "Saved", Toast.LENGTH_LONG).show();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }

}


