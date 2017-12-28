package com.lottery.library.http;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author ：czg
 * @class ：Response.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public abstract class ApiResponse<T> {
    protected ResponseBody mResponseBody;
    protected Response mResponse;
    protected ApiRequest<T> apiRequest;

    public ApiRequest<T> getApiRequest() {
        return apiRequest;
    }

    public void setApiRequest(ApiRequest<T> apiRequest) {
        this.apiRequest = apiRequest;
    }

    public ApiResponse(Response response) {
        if (response != null) {
            this.mResponseBody = response.body();
            this.mResponse = response;
        }
    }

    public abstract T getBody() throws IOException;

    public Request getRequest() {
        if(mResponse==null) {
            return null;
        }
        return mResponse.request();
    }
}
