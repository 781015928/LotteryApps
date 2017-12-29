package com.lottery.library.api.zx500.newsdetail;

import com.lottery.library.http.ApiResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class NewsDetailResponse extends ApiResponse<NewsDetailModel> {

    public NewsDetailResponse(Response response) {
        super(response);
    }

    @Override
    public NewsDetailModel getBody() throws IOException {
        String url = getApiRequest().getRequest().url().toString();
        Document doc = Jsoup.parse(new URL(url).openStream(), "GBK", "http://zx.500.com");
        //  String xml = mResponseBody.string();
        // xml = new String(xml.getBytes("GBK"));
        // Document doc = Jsoup.parse(xml);
        NewsDetailModel newsDetailModel = new NewsDetailModel();
        Elements elementsByClass = doc.getElementsByClass("detail detail_indent");
        if (elementsByClass.size() > 0) {
            String content = elementsByClass.get(0).html();
            content = content.replace("<img", "<img style=\"display:;max-width:100%;\"");
            newsDetailModel.setContent(content);
        }
        return newsDetailModel;
    }
}
