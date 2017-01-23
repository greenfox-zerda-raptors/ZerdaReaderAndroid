package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApi;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;
import com.greenfox.fuchsit.zerdareader.server.MockCall;
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editUserName, editPassword;
    String username, password;
    ReaderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editUserName = (EditText) findViewById(R.id.userName);
        editPassword = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.loginButton);

        final ReaderApiInterface apiService =
                api.getClient().create(ReaderApiInterface.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUserName.getText().toString();
                password = editPassword.getText().toString();
                MockCall<UserResponse> call = (MockCall<UserResponse>) apiService.loginUser(username, password);

                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse user = response.body();
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
            });
            }
        });
    }
}

