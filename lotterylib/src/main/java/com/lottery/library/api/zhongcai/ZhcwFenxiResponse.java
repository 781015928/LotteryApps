package com.lottery.library.api.zhongcai;

import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.web.WebResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class ZhcwFenxiResponse extends WebResponse {

    public ZhcwFenxiResponse(Response response) {
        super(response);
    }

    @Override
    public WebModel getBody() throws IOException {
        String xml = mResponseBody.string();

        Document doc = Jsoup.parse(xml);
        Element header = doc.selectFirst("header");
        if(header!=null) {
            header.remove();
        }
        Element footer = doc.getElementById("footer");
        if (footer != null) {
            footer.remove();
        }

        Elements ddhang = doc.getElementsByClass("ddhang");
        if (ddhang != null) {
            ddhang.remove();
        }
        Elements sq1 = doc.getElementsByClass("sq1");
        if (sq1 != null) {
            sq1.remove();
        }
        Elements ltan = doc.getElementsByClass("ltan");
        if (ltan != null) {
            ltan.remove();
        }

        WebModel zhcwFenxiModel = new WebModel();
        zhcwFenxiModel.setContent(doc.outerHtml());


        return zhcwFenxiModel;
    }


}
