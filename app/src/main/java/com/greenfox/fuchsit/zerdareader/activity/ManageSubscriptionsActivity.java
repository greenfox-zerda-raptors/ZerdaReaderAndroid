package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
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
import com.greenfox.fuchsit.zerdareader.dialog.ServerErrorDialog;
import com.greenfox.fuchsit.zerdareader.event.OkDeleteSubscriptionEvent;
import com.greenfox.fuchsit.zerdareader.model.AddSubsRequest;
import com.greenfox.fuchsit.zerdareader.model.AddSubsResponse;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteRequest;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteResponse;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Pattern;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageSubscriptionsActivity extends BaseActivity {
    ListView subscriptionsList;
    SubscriptionsAdapter subscriptionsAdapter;
    @Inject
    ReaderApiInterface apiService;
    NewSubsDialogFragment newSubsDialogFragment;
    DeleteDialogFragment deleteDialogFragment;


    private EditText urlEditText;
    TextInputLayout notValidUrlError;

    AddSubsResponse addSubsResponse;
    AddSubsRequest addSubsRequest;

    SubsDeleteRequest subsDeleteRequest;
    SubsDeleteResponse subsDeleteResponse;
    SharedPreferences sharedPreferences;

    SubscriptionsAdapter.OnTrashcanClickListenerInterface onTrashcanClickListenerInterface;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscriptions);

        subscriptionsList = (ListView) findViewById(R.id.subscriptions_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        subscriptionsAdapter = new SubscriptionsAdapter(this);
        subscriptionsList.setAdapter(subscriptionsAdapter);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

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
        serverErrorDialog = ServerErrorDialog.newInstance("Server Error");


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

        apiService.getSubscriptions().enqueue(new Callback<SubscriptionResponse>() {
            @Override
            public void onResponse(Call<SubscriptionResponse> call, Response<SubscriptionResponse> response) {
                subscriptionsAdapter.clear();
                subscriptionsAdapter.addAll(response.body().subscriptions);
            }

            @Override
            public void onFailure(Call<SubscriptionResponse> call, Throwable t) {
                showServerErrorDialog(null);
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
        notValidUrlError = (TextInputLayout) newSubsDialogFragment.getView().findViewById(R.id.not_valid_url);

        url = urlEditText.getText().toString();
        if (isUrlValid(url)) {
            addSubsRequest = new AddSubsRequest(url);
            apiService.addNewSubscription(addSubsRequest).enqueue(new Callback<AddSubsResponse>() {
                @Override
                public void onResponse(Call<AddSubsResponse> call, Response<AddSubsResponse> response) {
                    addSubsResponse = response.body();
                    checkResultAndSubscribe(addSubsResponse);
                }

                @Override
                public void onFailure(Call<AddSubsResponse> call, Throwable t) {
                    showServerErrorDialog(null);
                }
            });
        } else if (isUrlTextfieldEmpty()) {
            notValidUrlError.setError("Please enter URL.");
        }
        else {
            notValidUrlError.setError("Please enter a valid URL.");
        }
    }

    public boolean isUrlValid(String url) {
        Pattern pattern = Patterns.WEB_URL;
        return pattern.matcher(url).matches();
    }

    private boolean isUrlTextfieldEmpty() {
        return url.equals("");
    }


    public void unsubscribe(final SubscriptionModel subscriptionModel) {
        subsDeleteRequest = new SubsDeleteRequest(subscriptionModel.getUrl());
        Call<SubsDeleteResponse> call = apiService.deleteSubscription(subscriptionModel.getId(), subsDeleteRequest, sharedPreferences.getString("token", "default"));

        call.enqueue(new Callback<SubsDeleteResponse>() {
            @Override
            public void onResponse(Call<SubsDeleteResponse> call, Response<SubsDeleteResponse> response) {
                subsDeleteResponse = response.body();
                checkDeleteResult(subscriptionModel);
            }

            @Override
            public void onFailure(Call<SubsDeleteResponse> call, Throwable t) {
                showServerErrorDialog(null);
            }
        });

    }

    private void checkDeleteResult(SubscriptionModel subscriptionModel) {
        if (subsDeleteResponse.getResult().equals("success")) {
            subscriptionsAdapter.remove(subscriptionModel);
            Toast.makeText(ManageSubscriptionsActivity.this, "You have successfully unsubscribed.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ManageSubscriptionsActivity.this, subsDeleteResponse.getResult(), Toast.LENGTH_LONG).show();
        }
    }


    private void checkResultAndSubscribe(AddSubsResponse addSubsResponse) {
        if (addSubsResponse.getResult().equals("success")) {
            subscriptionsAdapter.add(new SubscriptionModel(urlEditText.getText().toString(), addSubsResponse.getId()));
            Toast.makeText(ManageSubscriptionsActivity.this, "You have successfully subscribed to " + urlEditText.getText(), Toast.LENGTH_LONG).show();
            newSubsDialogFragment.dismiss();
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
        Log.d("kjnfjvlj", "event caught");
    }


}
