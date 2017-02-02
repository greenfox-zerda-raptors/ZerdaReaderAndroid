package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;

public class ManageSubscriptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscriptions);

        TextView subscriptions = (TextView) findViewById(R.id.subscriptionsTextView);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(this, "Refreshed", Toast.LENGTH_LONG).show();
                break;
            case R.id.favorite:
                Toast.makeText(this, "You must be my lucky star", Toast.LENGTH_LONG).show();
                break;
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.manage_subscriptions:
                startActivity(new Intent(this, ManageSubscriptionsActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }
}
