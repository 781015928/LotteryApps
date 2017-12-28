package com.lottery.app.activity;

import android.support.v4.view.ViewPager;

import com.lottery.app.Api;
import com.lottery.app.adapter.MainTabAdapter;
import com.lottery.app.R;
import com.lottery.app.fragment.HomeFragment;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

public class MainActivity extends BaseActivity {


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
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setType(homeApi.type);
            mFragments.add(homeFragment);
        }
        mTabChannel.setupWithViewPager(mVpContent);
        mVpContent.setAdapter(new MainTabAdapter(mFragments, getSupportFragmentManager()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
