package com.lottery.library.base;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lottery.library.R;
import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.web.WebRequest;
import com.lottery.library.http.CallBack;

/**
 * @author czg
 * @date 2018/1/17.
 */

public abstract class WebBaseFragment extends BaseFragment {
    WebView mWebView;
    TextView title;

    @Override
    protected void initData() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        //  webHome.setWebViewClient(new WebContentActivity.MyWebViewClient());

        sendHttp(new WebRequest(getUrl()), new CallBack<WebModel>() {
            @Override
            public void onSuccess(WebModel response) {
                mWebView.loadDataWithBaseURL(getUrl(), response.getContent(), "text/html", "utf-8", null);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
                //localWebContentIntentBuilder.context(getContext()).targetActivity(ZhcwActivity.class).title("").url(url);
                //Utils.navigateTo(localWebContentIntentBuilder);
                return true;
            }
        });
    }

    @Override
    protected void initView() {
        title = findViewById(R.id.title);
        mWebView = findViewById(R.id.web_page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_web;
    }

    protected abstract String getTitle();

    protected abstract String getUrl();

}
