package com.lottery.app;

import android.app.Application;

import com.lottery.library.http.HttpClient;
import com.lottery.library.utils.UIUtils;

/**
 * Created by czg on 2017/12/28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpClient.getInstances().init(this);
        UIUtils.init(this);
    }
}
