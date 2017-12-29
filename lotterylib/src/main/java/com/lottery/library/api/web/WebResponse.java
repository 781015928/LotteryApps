package com.lottery.library.api.web;

import com.lottery.library.http.ApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class WebResponse extends ApiResponse<WebModel> {

    public WebResponse(Response response) {
        super(response);
    }

    @Override
    public WebModel getBody() throws IOException {
        String xml = mResponseBody.string();
//        Document doc = Jsoup.parse(xml);
//        doc.select("#header").remove();
//        doc.select("div.awardBottom").remove();
//        //   detailNav.remove();
//
//
//        //    Elements bottomBox = doc.getElementsByClass("bottomBox");
//        //  bottomBox.remove();
        WebModel webModel = new WebModel();
//        //   Elements body = doc.select("body");
        // script.addAll(body);
        webModel.setContent(xml);
        return webModel;
    }

    private List<String> mToRemoved = new ArrayList();

}
