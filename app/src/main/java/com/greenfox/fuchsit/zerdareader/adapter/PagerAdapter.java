package com.greenfox.fuchsit.zerdareader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.greenfox.fuchsit.zerdareader.activity.FeedFragment;

/**
 * Created by Zsuzska on 2017. 01. 30..
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FeedFragment tab1 = FeedFragment.newInstance(0);
                return tab1;
            case 1:
                FeedFragment tab2 = FeedFragment.newInstance(1);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}


