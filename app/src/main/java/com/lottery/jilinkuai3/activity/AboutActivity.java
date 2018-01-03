package com.lottery.jilinkuai3.activity;

import android.widget.TextView;

import com.lottery.jilinkuai3.R;
import com.lottery.library.base.BaseActivity;

import butterknife.Bind;

public class AboutActivity extends BaseActivity {
    @Bind(R.id.tv_about)
    TextView tv_about;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tv_about.setText(String.format(getString(R.string.app_about), getString(R.string.app_name)));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }
}
