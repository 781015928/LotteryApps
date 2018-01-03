package com.lottery.jilinkuai3.fragment;

import com.lottery.jilinkuai3.R;
import com.lottery.jilinkuai3.Utils;
import com.lottery.jilinkuai3.WebContentIntentBuilder;
import com.lottery.jilinkuai3.activity.OpenAwardDetailActivity;
import com.lottery.library.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    String urls[] = new String[]{"http://zxwap.caipiao.163.com/yuanchuang/gpxy",
            "http://m.zhcw.com/zixun/active.jsp?type=zt"};

    @Override
    protected void initData() {
        WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
        localWebContentIntentBuilder.context(getContext()).targetActivity(OpenAwardDetailActivity.class).title("").url(urls[0]).titleSelector("header h1").toRemovedTags(new String[]{"#header", "div.topSwipeWrap", "section.bottomBox", "a.indexIcon", "div.awardBottom"});
        Utils.navigateTo(localWebContentIntentBuilder);
    }

    @Override
    protected void initView() {
        WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
        localWebContentIntentBuilder.context(getContext()).targetActivity(OpenAwardDetailActivity.class).title("").url(urls[1]).titleSelector("header h1").toRemovedTags(new String[]{"#header", "div.topSwipeWrap", "section.bottomBox", "a.indexIcon", "div.awardBottom"});
        Utils.navigateTo(localWebContentIntentBuilder);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }




}
