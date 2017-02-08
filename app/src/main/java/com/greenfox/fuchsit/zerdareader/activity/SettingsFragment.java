package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.greenfox.fuchsit.zerdareader.R;

/**
 * Created by false on 2017. 02. 08..
 */

public class SettingsFragment extends PreferenceFragment {


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.preferences_fragment, container, false);
//    }

}