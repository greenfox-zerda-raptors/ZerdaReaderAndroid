package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

public class MainActivity extends AppCompatActivity {

    TextView textViewWelcome;
    Button buttonToolbar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        buttonToolbar = (Button) findViewById(R.id.buttonToolbar);
        textViewWelcome = (TextView) findViewById(R.id.textViewWelcome);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        checkIfLoggedIn();
    }

    private void checkIfLoggedIn() {
        boolean islogin = sharedPreferences.getBoolean("isLogin", false);
        if(!islogin) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
