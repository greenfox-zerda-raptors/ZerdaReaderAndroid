package com.greenfox.fuchsit.zerdareader.dialog;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
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
    public TextInputLayout notValidUrlError;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.new_subs_dialog, container);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        enterUrlTExView = (TextView) view.findViewById(R.id.enterUrlTextView);
        urlEditText = (EditText) view.findViewById(R.id.urlEditText);
        okButton = (Button) view.findViewById(R.id.okButton);
        notValidUrlError = (TextInputLayout) view.findViewById(R.id.not_valid_url);


        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Subscribe");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        urlEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


}



