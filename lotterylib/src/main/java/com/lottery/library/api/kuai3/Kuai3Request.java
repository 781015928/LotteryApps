package com.lottery.library.api.kuai3;

import com.lottery.library.api.Api;
import com.lottery.library.http.ApiRequest;
import com.lottery.library.http.ApiResponse;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class Kuai3Request extends ApiRequest<Kuai3Model> {


    private String url;

    public Kuai3Request() {
        url = "http://pjhnbo.net/api/app?appkey=WKRZaxVhcrdvEfm9uJndWedHvAAx32c2&appid=" + Api.getAppid();
    }
    @Override
    public Request getRequest() {
        return new Request.Builder().get().url(url).build();
    }

    @Override
    public ApiResponse<Kuai3Model> getResponse(Response responseBody) {
        return new Kuai3Response(responseBody);
    }
}
