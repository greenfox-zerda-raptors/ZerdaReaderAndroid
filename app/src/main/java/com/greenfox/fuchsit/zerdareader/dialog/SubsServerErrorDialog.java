package com.greenfox.fuchsit.zerdareader.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.ManageSubscriptionsActivity;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;

/**
 * Created by Zsuzska on 2017. 02. 16..
 */

public class SubsServerErrorDialog extends DialogFragment {
    ManageSubscriptionsActivity manageSubscriptionsActivity;
    SubscriptionModel subscriptionModel;

    public SubsServerErrorDialog() {
    }

    public static DeleteDialogFragment newInstance(String title) {
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog_favorite_error);
        builder.setTitle("Error");
        builder.setMessage("Something went wrong while connecting to server.");
        subscriptionModel = (SubscriptionModel) getArguments().getSerializable("subscriptionModel");
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ManageSubscriptionsActivity)getActivity()).subscribeToFeed(null);
                dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
