package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

public class ManageSubscriptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscriptions);

        TextView subscriptions = (TextView) findViewById(R.id.subscriptionsTextView);



        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button backButton = (Button) findViewById(R.id.subscriptionBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ManageSubscriptionsActivity.this, MainActivity.class);
                ManageSubscriptionsActivity.this.startActivity(activityChangeIntent);
            }
        });
    }

}

