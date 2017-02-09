package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.SubscriptionsAdapter;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteRequest;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteResponse;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageSubscriptionsActivity extends AppCompatActivity {

    ListView subscriptionsList;
    SubscriptionsAdapter subscriptionsAdapter;
    @Inject
    ReaderApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscriptions);

        subscriptionsList = (ListView) findViewById(R.id.subscriptions_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Feed");
        myToolbar.setSubtitle("Back to your feed");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DaggerMockServerComponent.builder().build().inject(this);
        subscriptionsAdapter = new SubscriptionsAdapter(this);
        subscriptionsList.setAdapter(subscriptionsAdapter);

        showSubscriptions();
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

    public void showSubscriptions() {

        Call<ArrayList<SubscriptionModel>> call;

        call = apiService.getSubscriptions();

        call.enqueue(new Callback<ArrayList<SubscriptionModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SubscriptionModel>> call, Response<ArrayList<SubscriptionModel>> response) {
                subscriptionsAdapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<SubscriptionModel>> call, Throwable t) {

            }
        });
    }



    public void showEditDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        NewSubsDialogFragment newSubsDialogFragment = NewSubsDialogFragment.newInstance("Subscribe");
        newSubsDialogFragment.show(fm, "new_subs_dialog");
    }


}

