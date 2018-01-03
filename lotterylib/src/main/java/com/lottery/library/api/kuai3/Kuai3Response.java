package com.lottery.library.api.kuai3;

import com.alibaba.fastjson.JSON;
import com.lottery.library.http.ApiResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by 78101 on 2017/12/28.
 */

public class Kuai3Response extends ApiResponse<Kuai3Model> {

    public Kuai3Response(Response response) {
        super(response);
    }

    @Override
    public Kuai3Model getBody() throws IOException {
        String json = mResponseBody.string();
        Kuai3Model kuai3Model = JSON.parseObject(json, Kuai3Model.class);
      //  kuai3Model.getData().setIswap("1");
     //   kuai3Model.getData().setWapurl("http://m.8mcp.com");
        return kuai3Model;
    }


}
