package com.lottery.library.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author ：czg
 * @class ：HttpClient.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class HttpClient {
    private static HttpClient instances = new HttpClient();
    private static Handler uiHandler = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient;
    ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    public static HttpClient getInstances() {
        return instances;
    }

    private HttpClient() {
    }

    public void init(Context context) {
        mOkHttpClient = new OkHttpClient
                .Builder()
                .cache(new Cache(new File(context.getCacheDir(), "okhttpcache"), 10 * 1024 * 1024)).build();
    }

    public <T> void send(final ApiRequest<T> request, final CallBack<T> callBack) {
        mOkHttpClient.newCall(request.getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                uiHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        callBack.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResponse<T> apiResponse = request.getResponse(response);
                    apiResponse.setApiRequest(request);
                    final T body = apiResponse.getBody();
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(body);
                        }
                    });
                } else {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFail(new RuntimeException("http error " + response.code()));
                        }
                    });
                }
            }
        });
    }

    public <T> void sendHtml(final ApiRequest<T> request, final CallBack<T> callBack) {
        String string = request.getRequest().url().toString();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ApiResponse<T> apiResponse = request.getResponse(null);
                apiResponse.setApiRequest(request);
                try {
                    final T body = apiResponse.getBody();
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(body);
                        }
                    });
                } catch (Exception e) {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFail(new RuntimeException("http error "));
                        }
                    });
                }
            }
        });

    }
}
