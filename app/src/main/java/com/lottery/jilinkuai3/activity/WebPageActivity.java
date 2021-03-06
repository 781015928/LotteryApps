package com.lottery.jilinkuai3.activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lottery.shishicaikaijiang.R;
import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.web.WebRequest;
import com.lottery.library.api.zx500.news.NewsModel;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.http.CallBack;
import com.lottery.library.utils.UIUtils;
import com.lottery.library.widget.flyn.Eyes;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author ChayChan
 * @description: 加载网页的activity
 * @date 2017/7/4  22:01
 */

public class WebPageActivity extends BaseActivity {
    public static final String URL = "url";

    @Bind(R.id.iv_back)
    ImageView mIvBack;

    @Bind(R.id.tv_author)
    TextView mTvTitle;

    @Bind(R.id.pb_loading)
    ProgressBar mPbLoading;

    @Bind(R.id.wv_content)
    WebView mWvContent;
    private NewsModel newsModel;


    @Override
    public void initView() {
        Eyes.setStatusBarColor(this, UIUtils.getColor(R.color.status_color_grey));
        initListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra(URL);
        sendHttp(new WebRequest(url), new CallBack<WebModel>() {
            @Override
            public void onSuccess(WebModel response) {
                String html = response.getContent();
                mWvContent.loadData(html, "text/html", "UTF-8");
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });

    }

    public void initListener() {
        WebSettings settings = mWvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mPbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPbLoading.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                sendHttp(new WebRequest(url), new CallBack<WebModel>() {
                    @Override
                    public void onSuccess(WebModel response) {
                        String html = response.getContent();
                        mWvContent.loadData(html, "text/html", "UTF-8");
                    }

                    @Override
                    public void onFail(Throwable throwable) {

                    }
                });
                return true;
            }

        });

        mWvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mPbLoading.setProgress(newProgress);
            }
        });

        mWvContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWvContent.canGoBack()) {  //表示按返回键
                        mWvContent.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
