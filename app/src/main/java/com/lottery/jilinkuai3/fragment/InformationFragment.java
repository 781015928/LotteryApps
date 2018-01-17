package com.lottery.jilinkuai3.fragment;

import android.support.v4.view.ViewPager;

import com.lottery.jilinkuai3.Constants;
import com.lottery.shishicaikaijiang.R;
import com.lottery.jilinkuai3.adapter.MainTabAdapter;
import com.lottery.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

public class InformationFragment extends BaseFragment {


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
        for (Constants.HomeApi homeApi : Constants.getHomeApis()) {
            HomeTabFragment homeTabFragment = new HomeTabFragment();
            homeTabFragment.setType(homeApi.type);
            mFragments.add(homeTabFragment);
        }
        mTabChannel.setupWithViewPager(mVpContent);
        mVpContent.setAdapter(new MainTabAdapter(mFragments, getChildFragmentManager()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_information;
    }

}
