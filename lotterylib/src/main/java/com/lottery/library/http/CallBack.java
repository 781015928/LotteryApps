package com.lottery.library.http;

/**
 * @author ：czg
 * @class ：CallBack.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public interface CallBack<T > {

    void onSuccess(T response);

    void onFail(Throwable throwable);

}
