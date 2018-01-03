package com.lottery.jilinkuai3.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lottery.jilinkuai3.R;
import com.lottery.library.base.BaseActivity;

import butterknife.Bind;

public class WebMainActivity extends BaseActivity {

    private static final String URL = "web_url";

    public static void startWebMain(Context context, String url) {
        context.startActivity(new Intent(context, WebMainActivity.class).putExtra(URL, url));
    }

    @Bind(R.id.web_view)
    WebView webView;

    @Override
    protected void initData() {
        currentUrl = getIntent().getStringExtra(URL);
        webView.loadUrl(getIntent().getStringExtra(URL));
    }

    private String currentUrl;

    @Override
    protected void initView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webView.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        settings.setDomStorageEnabled(true);


        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // mPbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //   mPbLoading.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                currentUrl = url;
                if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                }
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // mPbLoading.setProgress(newProgress);
            }


        });

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {  //表示按返回键

                        if (webView.canGoBack()) {
                            webView.goBack();  //后退
                            return true;
                        } else {
                            if (canBack) {
                                return false;
                            } else {
                                Toast.makeText(WebMainActivity.this, "2S内连续点击退出", Toast.LENGTH_SHORT).show();
                                canBack = true;

                                webView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        canBack = false;
                                    }
                                }, 1000);
                                return true;
                            }

                        }
                        //已处理
                    }
                }
                return false;
            }
        });
    }

    private boolean canBack = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_main;
    }


}
