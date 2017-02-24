package com.greenfox.fuchsit.zerdareader.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.activity.ManageSubscriptionsActivity;
import com.greenfox.fuchsit.zerdareader.event.OkDeleteSubscriptionEvent;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Zsuzska on 2017. 02. 13..
 */

public class DeleteDialogFragment extends DialogFragment {
    SubscriptionModel subscriptionModel;

    public DeleteDialogFragment() {
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
        builder.setTitle("Unsubscribe");
        builder.setMessage("Are you sure, you want to unsubscribe?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                subscriptionModel = (SubscriptionModel) getArguments().getSerializable("subscriptionModel");
                EventBus.getDefault().post(new OkDeleteSubscriptionEvent(subscriptionModel));

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
