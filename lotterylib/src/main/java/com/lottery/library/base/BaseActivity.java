package com.lottery.library.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;
import android.view.WindowManager;

import com.lottery.library.http.CallBack;
import com.lottery.library.http.HttpClient;
import com.lottery.library.http.ApiRequest;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    protected static final String THEME_CHANGE = "theme_change";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //  setTranslucentStatus(this, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        //  tintManager.setStatusBarTintEnabled(true);
        // 使用颜色资源
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected final <T> void sendHttp(final ApiRequest<T> request, final CallBack<T> callBack) {
        HttpClient.getInstances().send(request, callBack);
    }
    protected final <T> void sendHtml(final ApiRequest<T> request, final CallBack<T> callBack) {
        HttpClient.getInstances().sendHtml(request, callBack);
    }
    @TargetApi(19)
    private void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
