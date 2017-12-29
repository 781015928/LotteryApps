package com.lottery.app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lottery.app.R;
import com.lottery.app.fragment.HomeFragment;
import com.lottery.app.fragment.LotteryHallFragment;
import com.lottery.app.fragment.MeFragment;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.rg_main_group)
    RadioGroup mRadioGroup;
    private List<BaseFragment> mFragments;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new LotteryHallFragment());
        mFragments.add(new MeFragment());
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(R.id.rb_main_home);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private Fragment mTempFragment;

    private void switchFragment(Fragment fragment) {
        if (fragment != mTempFragment) {
            if (!fragment.isAdded()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (mTempFragment != null) {
                    transaction.hide(mTempFragment);
                }
                transaction.add(R.id.fl_main_content, fragment).commitAllowingStateLoss();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mTempFragment)
                        .show(fragment).commitAllowingStateLoss();
            }
            mTempFragment = fragment;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.rb_main_home) {

            switchFragment(mFragments.get(0));
        } else if (checkedId == R.id.rb_main_lottert_hall) {

            switchFragment(mFragments.get(1));
        } else if (checkedId == R.id.rb_main_me) {

            switchFragment(mFragments.get(2));
        }
    }
}
