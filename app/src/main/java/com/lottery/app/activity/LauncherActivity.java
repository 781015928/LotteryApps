package com.lottery.app.activity;

import android.content.Intent;
import android.os.Handler;

import com.lottery.app.R;
import com.lottery.library.base.BaseActivity;

public class LauncherActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

}