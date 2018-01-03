package com.lottery.library.api.zx500.news;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.library.http.ApiRequest;
import com.lottery.library.http.ApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * @author czg
 * @date 2017/12/28
 */

public class NewsResponse extends ApiResponse<List<NewsModel>> {

    private static Map<String, List<String>> imageUrls = new HashMap();


    public NewsResponse(Response response) {
        super(response);
    }

    @Override
    public List<NewsModel> getBody() throws IOException {
        String json = mResponseBody.string();
        JSONObject jsonObj = JSON.parseObject(json);
        if (jsonObj != null) {
            List<NewsModel> newsModels = JSON.parseArray(jsonObj.getString("data"), NewsModel.class);
            ApiRequest<List<NewsModel>> apiRequest = getApiRequest();
            NewsRequest newsRequest = null;
            if (apiRequest != null && apiRequest instanceof NewsRequest) {
                newsRequest = (NewsRequest) apiRequest;
            }

            for (NewsModel newsModel : newsModels) {
                if (Math.random() > 0.7) {
                    newsModel.setViewType(NewsModel.BIG_TYPE);
                } else {
                    newsModel.setViewType(NewsModel.DEFAULT_TYPE);
                }
                if (newsRequest != null) {

                    List<String> urls = imageUrls.get(newsRequest.getSortid());
                    if (urls == null) {
                        urls = new ArrayList<String>();
                        imageUrls.put(newsRequest.getSortid(), urls);
                    }
                    if (newsRequest.getPageCount() == 0) {
                        urls.clear();
                    }
                    if (urls.contains(newsModel.getCover())) {
                        newsModel.setViewType(NewsModel.NO_IMAGE);
                    } else {
                        urls.add(newsModel.getCover());
                    }
                }
            }
            return newsModels;
        }
        return new ArrayList<>();

    }

}
