package com.lottery.jilinkuai3.fragment;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lottery.jilinkuai3.Constants;
import com.lottery.jilinkuai3.R;
import com.lottery.jilinkuai3.Utils;
import com.lottery.jilinkuai3.WebContentIntentBuilder;
import com.lottery.jilinkuai3.activity.WebContentActivity;
import com.lottery.jilinkuai3.activity.ZhcwActivity;
import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.zhongcai.ZhcwFenxiRequest;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.http.CallBack;
import com.lottery.library.widget.banner.HomeBanner;
import com.lottery.library.widget.banner.listener.OnBannerClickListener;
import com.lottery.library.widget.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    String urls[] = new String[]{"http://zxwap.caipiao.163.com/yuanchuang/gpxy",
            "http://m.zhcw.com/zixun/active.jsp?type=zt"};

    private String[] bannerUrl = new String[]{
            "http://m.zhcw.com/khd/zx/cx/banner/14668207.shtml",
            "http://m.zhcw.com/khd/zx/cx/banner/14670353.shtml"
    };


    @Bind(R.id.home_banner)
    HomeBanner homeBanner;
    @Bind(R.id.ll_gaopingxuayuan)
    LinearLayout llGaopingxuayuan;
    @Bind(R.id.ll_jincaijiangtang)
    LinearLayout llJincaijiangtang;
    @Bind(R.id.ll_shuangseqiujiangtang)
    LinearLayout llShuangseqiujiangtang;
    @Bind(R.id.web_home)
    WebView webHome;

    @Override
    protected void initData() {
        webHome.getSettings().setJavaScriptEnabled(true);
        //  webHome.setWebViewClient(new WebContentActivity.MyWebViewClient());

        sendHttp(new ZhcwFenxiRequest(Constants.ZHONG_CAI_WANG_FEN_XI), new CallBack<WebModel>() {
            @Override
            public void onSuccess(WebModel response) {
                webHome.loadDataWithBaseURL(Constants.ZHONG_CAI_WANG_FEN_XI, response.getContent(), "text/html", "utf-8", null);
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
        homeBanner.setImages(Arrays.asList(R.mipmap.banner1, R.mipmap.banner2));
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                int id = (int) path;
                imageView.setImageResource(id);

            }
        });
        homeBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position <= 0) {
                    return;
                }
                List<String> strings = new ArrayList<>();
                strings.add("header");
                strings.add("footer");
                strings.add("ddhang");
                strings.add("sq1");
                strings.add("ltan");
                WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
                localWebContentIntentBuilder.context(getContext()).targetActivity(WebContentActivity.class).url(bannerUrl[position - 1]).toRemovedTags(strings);
                Utils.navigateTo(localWebContentIntentBuilder);
            }
        });

        homeBanner.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @OnClick({R.id.ll_gaopingxuayuan, R.id.ll_jincaijiangtang, R.id.ll_shuangseqiujiangtang})
    public void onClick(View view) {
        String url = null;
        ArrayList<String> remove = new ArrayList<>();
        String title = null;
        remove.add("#header");
        remove.add("div.topSwipeWrap");
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
