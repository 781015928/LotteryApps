package com.lottery.library.api.zx500.newsdetail;

import com.lottery.library.http.ApiRequest;
import com.lottery.library.http.ApiResponse;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class NewsDetailRequest extends ApiRequest<NewsDetailModel> {
    private String url;

    public NewsDetailRequest(String url) {
        this.url = url;
    }

    @Override
    public Request getRequest() {
         return new Request.Builder().get().url(url).build();
    }

    @Override
    public ApiResponse<NewsDetailModel> getResponse(Response responseBody) {
        return new NewsDetailResponse(responseBody);
    }
}
