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
import com.greenfox.fuchsit.zerdareader.model.User;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button button;

    EditText editEmail, editPassword;

    TextView textView;

    ReaderApi api;

    
    TextView textView;
    
    @Inject
    ReaderApiInterface apiService;

    SharedPreferences loginData;

    UserResponse userResponse;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        textView = (TextView) findViewById(R.id.loginTitle);
        editEmail = (EditText) findViewById(R.id.userName);
        editPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);

       DaggerMockServerComponent.builder().build().inject(this);
    }

    public void login(View view) {

        if (isTextfieldsEmpty()) {
            Toast.makeText(this, "Please fill in username/password.", Toast.LENGTH_LONG).show();
        }

        Call<UserResponse> call = apiService.loginUser(editEmail.getText().toString(), editPassword.getText().toString());

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user = response.body();


                checkCredentialsAndLogIn();                           
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }


    private void saveDataToSharedPreferences() {
        loginData = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        final SharedPreferences.Editor editor = loginData.edit();

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

    private boolean isLoginDataCorrect() {
        return editEmail.getText().toString().equals(user.getEmail()) && editPassword.getText().toString().equals(user.getPassword());
    }

    private void checkCredentialsAndLogIn() {
        if (!isLoginDataCorrect()) {
            Toast.makeText(this, "Username/password is incorrect.", Toast.LENGTH_LONG).show();
        } else {
            saveDataToSharedPreferences();
            Toast.makeText(LoginActivity.this, "Saved", Toast.LENGTH_LONG).show();
            loginWithCorrectData();
        }
    }


    // toolbar methods:

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "You must be my lucky star", Toast.LENGTH_LONG).show();
        return true;
    }

}


