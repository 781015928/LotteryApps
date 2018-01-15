package com.lottery.jilinkuai3;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

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
        Api.init("141386");
        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                showToast(e.getMessage());
            }
        });
    }
    private void showToast(final String text){

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
