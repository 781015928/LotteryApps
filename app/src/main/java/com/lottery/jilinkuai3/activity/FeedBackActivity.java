package com.lottery.jilinkuai3.activity;

import android.widget.Toast;

import com.lottery.shishicaikaijiang.R;
import com.lottery.library.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author czg
 * @date 2018/1/15.
 */

public class FeedBackActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_feedback;
    }



    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        Toast.makeText(FeedBackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
