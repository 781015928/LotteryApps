package com.czg.xunlei.activity;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.czg.aoputils.permission.Permission;
import com.czg.xunlei.MainActivity;
import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;

import butterknife.Bind;

public class LauncherActivity extends BaseActivity {
    @Bind(R.id.animation_view)
    LottieAnimationView animation_view;

    @Override
    protected void initData() {
        setTitle("");
        animation_view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPermission();
                    }
                }, 500);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    @Permission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    private void getPermission() {
        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected boolean isHaveBackIcon() {
        return false;
    }

    @Override
    protected boolean isHaveSettingIcon() {
        return false;
    }
}
