package com.lottery.jilinkuai3;

import android.app.Application;

import com.king.thread.nevercrash.NeverCrash;
import com.lottery.library.api.Api;
import com.lottery.library.http.HttpClient;
import com.lottery.library.utils.LogUtils;
import com.lottery.library.utils.UIUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by czg on 2017/12/28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpClient.getInstances().init(this);
        UIUtils.init(this);
        JPushInterface.init(this);

        JPushInterface.init(getApplicationContext()); // 初始化 JPush
        JPushInterface.setDebugMode(true);
        String registrationID = JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.e(registrationID);
        Api.init("131506");
        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
            }
        });
    }
    private void showToast(final String text){


    }

}
