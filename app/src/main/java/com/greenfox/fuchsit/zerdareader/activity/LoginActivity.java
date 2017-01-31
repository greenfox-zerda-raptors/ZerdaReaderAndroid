package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApi;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editUserName, editPassword;

    TextView textView;

    String username, password;
    ReaderApi api;

    SharedPreferences loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textView = (TextView) findViewById(R.id.loginTitle);
        editUserName = (EditText) findViewById(R.id.userName);
        editPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);
    }

    public void login(View view){

//        final ReaderApiInterface apiService = api.getClient().create(ReaderApiInterface.class);

        MockServer apiService = new MockServer();

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

                Toast.makeText(LoginActivity.this,"Saved",Toast.LENGTH_LONG).show();

                loginWithCorrectData();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }

    private void loginWithCorrectData() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    private boolean isTextfieldsEmpty() {
        return editUserName.getText().toString().equals("") || editPassword.getText().toString().equals("");
    }


    // toolbar methods:

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"You must be my lucky star",Toast.LENGTH_LONG).show();
        return true;
    }
}
