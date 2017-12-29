package com.lottery.app.fragment;

import android.support.v4.view.ViewPager;

import com.lottery.app.Api;
import com.lottery.app.R;
import com.lottery.app.adapter.MainTabAdapter;
import com.lottery.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

public class HomeFragment extends BaseFragment {


    private List<BaseFragment> mFragments;

    @Bind(R.id.tab_channel)
    ColorTrackTabLayout mTabChannel;
    @Bind(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        for (Api.HomeApi homeApi : Api.getHomeApis()) {
            HomeTabFragment homeTabFragment = new HomeTabFragment();
            homeTabFragment.setType(homeApi.type);
            mFragments.add(homeTabFragment);
        }
        mTabChannel.setupWithViewPager(mVpContent);
        mVpContent.setAdapter(new MainTabAdapter(mFragments, getChildFragmentManager()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
