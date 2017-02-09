package com.greenfox.fuchsit.zerdareader.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

/**
 * Created by Zsuzska on 2017. 02. 09..
 */

public class NewSubsDialogFragment extends DialogFragment {

    private TextView enterUrlTExView;
    private EditText urlEditText;
    private Button okButton;

    public NewSubsDialogFragment() {
    }

    public static NewSubsDialogFragment newInstance(String title) {
        NewSubsDialogFragment fragment = new NewSubsDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        String title = getArguments().getString("title");
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//        alertDialogBuilder.setTitle(title);
//        alertDialogBuilder.setMessage("Are you sure?");
//        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // on success
//            }
//        });
//        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        return alertDialogBuilder.create();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.new_subs_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        enterUrlTExView = (TextView) view.findViewById(R.id.enterUrlTextView);
        urlEditText = (EditText) view.findViewById(R.id.urlEditText);
        okButton = (Button) view.findViewById(R.id.okButton);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Subscribe");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        urlEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
