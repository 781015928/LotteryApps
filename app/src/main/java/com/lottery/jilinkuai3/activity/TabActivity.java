package com.lottery.jilinkuai3.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lottery.jilinkuai3.fragment.HomeTabFragment;
import com.lottery.library.base.BaseActivity;
import com.lottery.shishicaikaijiang.R;

import butterknife.Bind;

/**
 * @author czg
 * @date 2018/1/17.
 */

public class TabActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.fl_content)
    FrameLayout mFlContent;
    public static final String TYPE = "type";
    public static final String TITLE = "title";

    public static void startTabActivity(Context context, String type, String title) {
        Intent intent = new Intent(context, TabActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }


    @Override
    protected void initData() {
        mTitle.setText(getIntent().getStringExtra(TITLE));
        HomeTabFragment homeTabFragment = (HomeTabFragment) getSupportFragmentManager().findFragmentByTag(TYPE);
        if (homeTabFragment == null) {
            homeTabFragment = new HomeTabFragment();
            homeTabFragment.setType(getIntent().getStringExtra(TYPE));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fl_content, homeTabFragment, TYPE).commit();
        } else {
            homeTabFragment.setType(getIntent().getStringExtra(TYPE));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(homeTabFragment).commit();
        }

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_home;
    }


}
