package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.adapter.SubscriptionsAdapter;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.dialog.DeleteDialogFragment;
import com.greenfox.fuchsit.zerdareader.dialog.NewSubsDialogFragment;
import com.greenfox.fuchsit.zerdareader.event.OkDeleteSubscriptionEvent;
import com.greenfox.fuchsit.zerdareader.model.AddSubsRequest;
import com.greenfox.fuchsit.zerdareader.model.AddSubsResponse;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteRequest;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteResponse;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;
import com.greenfox.fuchsit.zerdareader.server.MockServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageSubscriptionsActivity extends AppCompatActivity {
    SubscriptionModel subscriptionModel;
    ListView subscriptionsList;
    SubscriptionsAdapter subscriptionsAdapter;
    @Inject
    ReaderApiInterface apiService;
    NewSubsDialogFragment newSubsDialogFragment;
    DeleteDialogFragment deleteDialogFragment;

    private EditText urlEditText;

    AddSubsResponse addSubsResponse;
    AddSubsRequest addSubsRequest;

    SubsDeleteRequest subsDeleteRequest;
    SubsDeleteResponse subsDeleteResponse;
    SharedPreferences sharedPreferences;

    MockServer mockServer;

    SubscriptionsAdapter.OnTrashcanClickListenerInterface onTrashcanClickListenerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscriptions);

        subscriptionsList = (ListView) findViewById(R.id.subscriptions_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        subscriptionsAdapter = new SubscriptionsAdapter(this);
        subscriptionsList.setAdapter(subscriptionsAdapter);

        subscriptionsAdapter.setOnTrashcanClickListenerInterface(new SubscriptionsAdapter.OnTrashcanClickListenerInterface() {
            @Override
            public void onTrashcanClicked(SubscriptionModel subscriptionModel) {
                showDeleteDialog(subscriptionModel);

            }
        });

        myToolbar.setTitle("Feed");
        myToolbar.setSubtitle("Back to your feed");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newSubsDialogFragment = NewSubsDialogFragment.newInstance("Subscribe");
        deleteDialogFragment = DeleteDialogFragment.newInstance("Unsubcribe");

        DaggerMockServerComponent.builder().build().inject(this);

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
                subscriptionsAdapter.clear();
                subscriptionsAdapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<SubscriptionModel>> call, Throwable t) {

            }
        });
    }

    public void showDeleteDialog(SubscriptionModel subscriptionModel) {
        deleteDialogFragment.getArguments().putSerializable("subscriptionModel", subscriptionModel);
        FragmentManager fragmentManager = getSupportFragmentManager();
        deleteDialogFragment.show(fragmentManager, "new_delete_dialog");
    }

    public void showNewSubsDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        newSubsDialogFragment.show(fm, "new_subs_dialog");
    }

    public void subscribeToFeed(View view) {

        urlEditText = (EditText) newSubsDialogFragment.getView().findViewById(R.id.urlEditText);

        addSubsRequest = new AddSubsRequest(urlEditText.getText().toString());
        Call<AddSubsResponse> call = apiService.addNewSubscription(addSubsRequest);

        call.enqueue(new Callback<AddSubsResponse>() {
            @Override
            public void onResponse(Call<AddSubsResponse> call, Response<AddSubsResponse> response) {
                addSubsResponse = response.body();

                checkResultAndSubscribe(addSubsResponse);
            }

            @Override
            public void onFailure(Call<AddSubsResponse> call, Throwable t) {

            }
        });

    }

    public void unsubscribe(SubscriptionModel subscriptionModel) {
        subsDeleteRequest = new SubsDeleteRequest(subscriptionModel.getUrl());
        Call<SubsDeleteResponse> call = apiService.deleteSubscription(subscriptionModel.getId(), subsDeleteRequest, sharedPreferences.getString("token", "default"));

        call.enqueue(new Callback<SubsDeleteResponse>() {
            @Override
            public void onResponse(Call<SubsDeleteResponse> call, Response<SubsDeleteResponse> response) {
                subsDeleteResponse = response.body();
                checkDeleteResult(subsDeleteResponse);
            }

            @Override
            public void onFailure(Call<SubsDeleteResponse> call, Throwable t) {

            }
        });
        showSubscriptions();
    }

    private void checkDeleteResult(SubsDeleteResponse subsDeleteResponse) {
        if (subsDeleteResponse.getResult().equals("success")) {
            Toast.makeText(ManageSubscriptionsActivity.this, "You have successfully unsubscribed.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ManageSubscriptionsActivity.this, subsDeleteResponse.getResult(), Toast.LENGTH_LONG).show();
        }
    }


    private void checkResultAndSubscribe(AddSubsResponse addSubsResponse) {
        if (addSubsResponse.getResult().equals("success")) {
            newSubsDialogFragment.dismiss();
            subscriptionsAdapter.notifyDataSetChanged();
            Toast.makeText(ManageSubscriptionsActivity.this, "You have successfully subscribed to " + urlEditText.getText(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ManageSubscriptionsActivity.this, addSubsResponse.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onOkDeleteSubscriptionEvent(OkDeleteSubscriptionEvent okDeleteSubscriptionEvent) {
        unsubscribe(okDeleteSubscriptionEvent.getSubscriptionModel());
    }


}

