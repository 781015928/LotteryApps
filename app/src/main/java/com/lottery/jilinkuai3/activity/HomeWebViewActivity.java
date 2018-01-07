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

import com.lottery.jilinkuai3.R;
import com.lottery.library.api.zx500.news.NewsModel;
import com.lottery.library.api.zx500.newsdetail.NewsDetailModel;
import com.lottery.library.api.zx500.newsdetail.NewsDetailRequest;
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

public class HomeWebViewActivity extends BaseActivity {
    public static final String NEWS_MODEL = "news_model";

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
        newsModel = (NewsModel) getIntent().getSerializableExtra(NEWS_MODEL);

        sendHtml(new NewsDetailRequest(newsModel.getUrl()), new CallBack<NewsDetailModel>() {
            @Override
            public void onSuccess(NewsDetailModel response) {
                String html = response.getContent();
                mWvContent.loadDataWithBaseURL(null,html, "text/html",  "utf-8", null);
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
