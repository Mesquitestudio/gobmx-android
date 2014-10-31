package com.mesquitestudio.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mesquitestudio.fragments.AdditionalFragment;
import com.mesquitestudio.fragments.InformationFragment;
import com.mesquitestudio.fragments.ResolutionFragment;
import com.mesquitestudio.fragments.ServicesFragment;
import com.mesquitestudio.models.Services;

import java.util.List;

/**
 * Created by paulmoreno on 10/7/14.
 */
public class TabPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public TabPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {

        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
