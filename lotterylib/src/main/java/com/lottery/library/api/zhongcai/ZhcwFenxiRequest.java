package com.lottery.library.api.zhongcai;

import com.lottery.library.api.web.WebModel;
import com.lottery.library.api.web.WebRequest;
import com.lottery.library.http.ApiResponse;

import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class ZhcwFenxiRequest extends WebRequest {
    private String url;

    public ZhcwFenxiRequest(String url) {
        super(url);
    }
    @Override
    public ApiResponse<WebModel>  getResponse(Response responseBody) {
        return new ZhcwFenxiResponse(responseBody);
    }
}
