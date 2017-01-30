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
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;

public class MainActivity extends AppCompatActivity {

    Button logoutButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        logoutButton =(Button) findViewById(R.id.logoutButton);
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

    public void logOut(View view){
       SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(this);
       SharedPreferences.Editor editor = loginData.edit();
        editor.putString("userName", "");
        editor.putString("password", "");
        editor.apply();
        Toast.makeText(this,"Successful logout", Toast.LENGTH_SHORT).show();
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
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
