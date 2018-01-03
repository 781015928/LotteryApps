package com.lottery.jilinkuai3.activity;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lottery.jilinkuai3.R;
import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.web.WebRequest;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.http.CallBack;
import com.lottery.library.utils.ADFilterTool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;

public class WebContentActivity extends BaseActivity {
    public static final String EXTRA_REMOVED_ELEMENTS = "extra_removed_elements";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_TITLE_SELECTOR = "extra_title_selector";
    public static final String EXTRA_URL = "extra_url";
    protected String mTitleSelector;
    private List<String> mToRemoved = new ArrayList();
    protected String mUrl;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.web)
    WebView web;
    @Bind(R.id.title)
    TextView title;

    public WebContentActivity() {
    }

    protected List<String> getInterceptResourceRequestUrl() {
        return Collections.EMPTY_LIST;
    }

    protected boolean handleUrlLoading(WebView webView, String url) {
        sendHttp(url);
        return true;
    }

    public void sendHttp(String url) {
        sendHttp(new WebRequest(url), new CallBack<WebModel>() {
            @Override
            public void onSuccess(WebModel response) {
                WebContentActivity.this.onSuccess(response.getContent());
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

    @Override
    protected void initData() {
        sendHttp(mUrl);
        web.loadDataWithBaseURL(null, "加载中。。", "text/html", "utf-8", null);
        // HttpClient.fetchWebPage(100, this.mUrl, this);
    }

    @Override
    protected void initView() {

        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebContentActivity.MyWebViewClient());
        //  web.setDownloadListener(new MyWebViewDownloadListener(this));
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        this.title.setText(title);

        this.mUrl = getIntent().getStringExtra(EXTRA_URL);
        this.mTitleSelector = getIntent().getStringExtra(EXTRA_TITLE_SELECTOR);
        String[] var4 = getIntent().getStringArrayExtra(EXTRA_REMOVED_ELEMENTS);
        if (var4 != null && var4.length > 0) {
            this.mToRemoved.addAll(Arrays.asList(var4));
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_content;
    }


    public void onSuccess(@NonNull String html) {
        if (!TextUtils.isEmpty(this.mTitleSelector)) {
            Element var4 = Jsoup.parse(html).selectFirst(this.mTitleSelector);
            if (var4 != null && !TextUtils.isEmpty(var4.text())) {
                title.setText(var4.text());
            }
        }
        Document document = getDocument(html);
        Element var3 = parseWebContent(document);

        web.getSettings().setDefaultTextEncodingName(encoding);
        web.loadDataWithBaseURL(this.mUrl, var3.toString(), "text/html", encoding, null);
    }


    private String encoding = "utf-8";

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

    private Document getDocument(String html) {
        Document doc = Jsoup.parse(html);
        Elements meta = doc.select("meta");
        if (meta.size() > 0) {
            String content = meta.get(0).attr("content");
            if (!TextUtils.isEmpty(content)) {
                if (content.contains("charset=")) {
                     encoding = content.substring(content.indexOf("charset=") + "charset=".length(), content.length());
                }

            }


        }
        return doc;
    }


    protected Element parseWebContent(Document document) {
        if (this.mToRemoved.isEmpty()) {
        } else {
            for (int var4 = 0; var4 < this.mToRemoved.size(); ++var4) {
                Elements var5 = document.select(this.mToRemoved.get(var4));
                if (var5 != null) {
                    var5.remove();
                }
            }
        }

        return document;
    }

    class MyWebViewClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView var1, String var2) {
            //   LoadingWidget.get().dismiss();
        }

        @Override
        public void onPageStarted(WebView var1, String var2, Bitmap var3) {
        }

        @Override
        public void onReceivedError(WebView var1, int var2, String var3, String var4) {
            //  LoadingWidget.get().dismiss();
        }

        @TargetApi(21)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView var1, WebResourceRequest var2) {
            return this.shouldInterceptRequest(var1, var2.getUrl().toString());
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView var1, String var2) {
            String var3 = var2.toLowerCase();
            if (!ADFilterTool.isAdRequest(WebContentActivity.this, var3)) {
                List var4 = WebContentActivity.this.getInterceptResourceRequestUrl();
                if (var4 != null && !var4.isEmpty()) {
                    Iterator var5 = var4.iterator();

                    do {
                        if (!var5.hasNext()) {
                            return super.shouldInterceptRequest(var1, var3);
                        }
                    } while (!var3.startsWith((String) var5.next()));

                    return new WebResourceResponse("text/html", encoding, new ByteArrayInputStream("".getBytes()));
                } else {
                    return super.shouldInterceptRequest(var1, var3);
                }
            } else {
                return new WebResourceResponse("text/html", encoding, new ByteArrayInputStream("".getBytes()));
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView var1, WebResourceRequest var2) {
            return VERSION.SDK_INT >= 21 ? WebContentActivity.this.handleUrlLoading(var1, var2.getUrl().toString()) : false;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView var1, String var2) {
            return WebContentActivity.this.handleUrlLoading(var1, var2);
        }


    }
}
