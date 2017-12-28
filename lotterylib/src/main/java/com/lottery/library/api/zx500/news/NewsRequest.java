package com.lottery.library.api.zx500.news;

import com.lottery.library.http.ApiRequest;
import com.lottery.library.http.ApiResponse;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author czg
 * @date 2017/12/28
 */

public class NewsRequest extends ApiRequest<List<NewsModel>> {

    private final String url;
    private final String sortid;
    private final int pageCount;

    public String getSortid() {
        return sortid;
    }

    public int getPageCount() {
        return pageCount;
    }

    public NewsRequest(int pageCount, String sortid) {
        this.sortid=sortid;
        this.pageCount=pageCount;
        url = " http://zx.500.com/ajax.php?type=news&pageCount=" + pageCount + "&sortid=" + sortid;
    }

    @Override
    public Request getRequest() {
        return new Request.Builder().get().url(url).build();
    }

    @Override
    public ApiResponse<List<NewsModel>> getResponse(Response response) {
        return new NewsResponse(response);
    }
}
