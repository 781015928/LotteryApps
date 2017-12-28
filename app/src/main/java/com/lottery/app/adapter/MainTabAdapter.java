package com.lottery.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lottery.app.Api;
import com.lottery.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class MainTabAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mFragments = new ArrayList();

    public MainTabAdapter(List<BaseFragment> fragmentList, FragmentManager fm) {
        super(fm);
        if (fragmentList != null) {
            mFragments = fragmentList;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Api.getHomeApis().get(position).title;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
