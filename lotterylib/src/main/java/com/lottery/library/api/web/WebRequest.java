package com.lottery.library.api.web;

import com.lottery.library.http.ApiRequest;
import com.lottery.library.http.ApiResponse;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class WebRequest extends ApiRequest<WebModel> {
    private String url;

    public WebRequest(String url) {
        this.url = url;
    }

    @Override
    public Request getRequest() {
         return new Request.Builder().get().url(url).build();
    }

    @Override
    public ApiResponse<WebModel> getResponse(Response responseBody) {
        return new WebResponse(responseBody);
    }
}
