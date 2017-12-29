package com.lottery.library.api.caipiao163.lotterhall;

import com.lottery.library.http.ApiRequest;
import com.lottery.library.http.ApiResponse;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by czg on 2017/12/29.
 */

public class LotterHallRequest extends ApiRequest<List<LotterHallModel>> {
    @Override
    public Request getRequest() {
        return new Request.Builder().get().url("http://caipiao.163.com/t/award/").build();
    }

    @Override
    public ApiResponse<List<LotterHallModel>> getResponse(Response responseBody) {
        return new LotterHallResponse(responseBody);
    }
}
