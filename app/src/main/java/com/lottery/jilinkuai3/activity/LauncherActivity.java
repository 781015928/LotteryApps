package com.lottery.jilinkuai3.activity;

import android.content.Intent;
import android.os.Handler;

import com.lottery.shishicaikaijiang.R;
import com.lottery.library.api.kuai3.Kuai3Model;
import com.lottery.library.api.kuai3.Kuai3Request;
import com.lottery.library.base.BaseActivity;
import com.lottery.library.http.CallBack;

public class LauncherActivity extends BaseActivity {


    private long timeMillis;

    @Override
    protected void initData() {
        sendHttp(new Kuai3Request(), new CallBack<Kuai3Model>() {
            @Override
            public void onSuccess(final Kuai3Model response) {
                if ("200".equals(response.getCode())) {
                    Kuai3Model.DataBean data = response.getData();
                    if ("1".equals(data.getIswap())) {
                        long time = System.currentTimeMillis() - timeMillis;
                        if (time > 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                 WebMainActivity.startWebMain(LauncherActivity.this,response.getData().getWapurl());
                                    finish();
                                }
                            }, time);
                        } else {
                            WebMainActivity.startWebMain(LauncherActivity.this,response.getData().getWapurl());
                            finish();
                        }
                    } else {
                        long time = System.currentTimeMillis() - timeMillis;
                        if (time > 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                                    finish();
                                }
                            }, time);
                        } else {
                            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                            finish();
                        }

                    }

                } else {
                    initData();
                }

            }

            @Override
            public void onFail(Throwable throwable) {
                startActivity(new Intent(LauncherActivity.this, MainActivity.class).putExtra("err_net",true));
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        timeMillis = System.currentTimeMillis();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

}