package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

public class ManageSubscriptionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscriptions);

        TextView subscriptions = (TextView) findViewById(R.id.subscriptionsTextView);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Feed");
        myToolbar.setSubtitle("Back to your feed");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.short_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}

