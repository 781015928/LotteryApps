package com.lottery.jilinkuai3.fragment;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lottery.jilinkuai3.Constants;
import com.lottery.shishicaikaijiang.R;
import com.lottery.jilinkuai3.Utils;
import com.lottery.jilinkuai3.WebContentIntentBuilder;
import com.lottery.jilinkuai3.activity.WebContentActivity;
import com.lottery.jilinkuai3.activity.ZhcwActivity;
import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.zhongcai.ZhcwFenxiRequest;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.http.CallBack;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * @author czg
 * @date 2018/1/15.
 */

public class ChatFragment extends BaseFragment {


    @Bind(R.id.web_home)
    WebView webHome;

    @Override
    protected void initData() {
        webHome.getSettings().setJavaScriptEnabled(true);
        //  webHome.setWebViewClient(new WebContentActivity.MyWebViewClient());

        sendHttp(new ZhcwFenxiRequest(Constants.CHAT), new CallBack<WebModel>() {
            @Override
            public void onSuccess(WebModel response) {
                webHome.loadDataWithBaseURL(Constants.CHAT, response.getContent(), "text/html", "utf-8", null);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });

        webHome.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
                localWebContentIntentBuilder.context(getContext()).targetActivity(ZhcwActivity.class).title("").url(url);
                Utils.navigateTo(localWebContentIntentBuilder);


                return true;
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }


    public void onClick(View view) {
        String url = null;
        ArrayList<String> remove = new ArrayList<>();
        String title = null;
        remove.add("#header");
        remove.add("div.#topSwipeWrap");
        remove.add("div.topSwipeWrap fixed_right_bottom topHiPos4");
        remove.add("div.topSwipeItem");
        remove.add("section.bottomBox");
        remove.add("a.indexIcon");
        remove.add("div.awardBottom");
        remove.add("section.awardBottom");
        remove.add("div.newsListTit");
        remove.add("ul.newsListCon");
        remove.add("section.newsPost");
        remove.add("p.bottomLink");
        remove.add("p.copye");
        remove.add("a.bottomAdWrap");
        remove.add("dl.info");


        switch (view.getId()) {
            case R.id.ll_gaopingxuayuan:
                title = "彩票学院";
                url = "http://zxwap.caipiao.163.com/yuanchuang/gpxy";
                break;
            case R.id.ll_jincaijiangtang:
                title = "彩票大讲堂";
                url = "http://zxwap.caipiao.163.com/yuanchuang/jcdjt";
                break;
            case R.id.ll_shuangseqiujiangtang:
                title = "双色球学院";
                url = "http://zxwap.caipiao.163.com/yuanchuang/ssqxt";
                break;

        }
        WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
        localWebContentIntentBuilder.context(getContext()).targetActivity(WebContentActivity.class).title(title).url(url).titleSelector("header h1").toRemovedTags(remove).toIgnoreText("网易");
        Utils.navigateTo(localWebContentIntentBuilder);
    }


}
