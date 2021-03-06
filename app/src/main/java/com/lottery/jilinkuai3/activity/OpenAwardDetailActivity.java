package com.lottery.jilinkuai3.activity;

import android.webkit.WebView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

/**
 * Created by czg on 2017/12/29.
 */

public class OpenAwardDetailActivity extends WebContentActivity {

    @Override
    protected List<String> getInterceptResourceRequestUrl() {
        return Arrays.asList(new String[]{"http://caipiao.163.com/seosem/getseoinfobygsp.html"});
    }

    @Override
    protected boolean handleUrlLoading(WebView paramWebView, String paramString) {
        int i = this.mUrl.lastIndexOf("/");
        String str = this.mUrl.substring(0, i + 1);
        if (paramString.equals(str)) {

            sendHttp(str);
            return true;
        }
        if(paramString!=null) {
            sendHttp(paramString);
        }

        return true;
    }

    @Override
    protected Element parseWebContent(  Document localDocument) {
        try {
            localDocument.selectFirst("#header").remove();
            localDocument.selectFirst("div.awardBottom").remove();
            return localDocument;
        } catch (Exception localException) {
            localException.printStackTrace();
            return localDocument;
        }
    }


}