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

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editUserName, editPassword;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textView = (TextView) findViewById(R.id.loginTitle);

        editUserName = (EditText) findViewById(R.id.userName);
        editPassword = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.loginButton);
    }

    public void login(View view){
        SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("userName", editUserName.getText().toString());
        editor.putString("password", editPassword.getText().toString());
        editor.putBoolean("isLogin", true);
        editor.commit();

        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
