package com.lottery.jilinkuai3.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.lottery.jilinkuai3.fragment.ChatFragment;
import com.lottery.jilinkuai3.fragment.LotteryHallFragment;
import com.lottery.jilinkuai3.fragment.MeFragment;
import com.lottery.jilinkuai3.fragment.ZcHomeFragment;
import com.lottery.library.api.kuai3.Kuai3Model;
import com.lottery.library.api.kuai3.Kuai3Request;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.http.CallBack;
import com.lottery.shishicaikaijiang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.rg_main_group)
    RadioGroup mRadioGroup;
    private List<BaseFragment> mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            int statusColor = Color.parseColor("#008000");
//针对版本5.x以上的即LOLLIPOP以上的
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                //设置透明状态栏,这样才能让 ContentView 向上
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏颜色
                window.setStatusBarColor(statusColor);
                ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View .
                    // 使其不为系统 View 预留空间.不预留空间的话 状态栏就会覆盖布局顶部
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                }
            }
        }
    }

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
        mFragments.add(new ZcHomeFragment());
        mFragments.add(new ChatFragment());
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
        } else if (checkedId == R.id.rb_main_information) {

            switchFragment(mFragments.get(1));
        } else if (checkedId == R.id.rb_main_lottert_hall) {
            switchFragment(mFragments.get(2));
        } else if (checkedId == R.id.rb_main_me) {
            switchFragment(mFragments.get(3));

        }
    }
}
