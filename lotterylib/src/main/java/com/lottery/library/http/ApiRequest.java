package com.lottery.library.http;


import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：czg
 * @class ：ApiRequest.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public abstract class ApiRequest<T> {
    public abstract Request getRequest();


    public abstract ApiResponse<T> getResponse(Response responseBody);
}
