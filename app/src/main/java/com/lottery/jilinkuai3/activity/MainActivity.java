package com.lottery.jilinkuai3.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lottery.jilinkuai3.R;
import com.lottery.jilinkuai3.fragment.HomeFragment;
import com.lottery.jilinkuai3.fragment.LotteryHallFragment;
import com.lottery.jilinkuai3.fragment.MeFragment;
import com.lottery.library.api.kuai3.Kuai3Model;
import com.lottery.library.api.kuai3.Kuai3Request;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.http.CallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.rg_main_group)
    RadioGroup mRadioGroup;
    private List<BaseFragment> mFragments;


    @Override
    protected void initData() {
        sendHttp(new Kuai3Request(), new CallBack<Kuai3Model>() {
            @Override
            public void onSuccess(final Kuai3Model response) {
                if ("200".equals(response.getCode())) {
                    Kuai3Model.DataBean data = response.getData();
                    if ("1".equals(data.getIswap())) {
                        WebMainActivity.startWebMain(MainActivity.this, response.getData().getWapurl());
                        finish();
                    }
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                initData();
            }
        });
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
