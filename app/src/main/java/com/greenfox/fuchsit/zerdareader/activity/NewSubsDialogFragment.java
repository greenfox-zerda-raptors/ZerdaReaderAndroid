package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.fuchsit.zerdareader.R;

/**
 * Created by Zsuzska on 2017. 02. 09..
 */

public class NewSubsDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_subs_dialog, container, false);
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
}
