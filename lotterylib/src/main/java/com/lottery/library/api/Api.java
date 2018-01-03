package com.lottery.library.api;

/**
 * Created by 78101 on 2017/12/30.
 */

public class Api {
    private static String appid;

    public static void init(String appid) {
        Api.appid = appid;
    }

    public static String getAppid() {
        return appid;
    }
}
