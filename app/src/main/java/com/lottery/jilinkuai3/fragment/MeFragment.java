package com.lottery.jilinkuai3.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lottery.jilinkuai3.R;
import com.lottery.jilinkuai3.Utils;
import com.lottery.jilinkuai3.activity.AboutActivity;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.utils.ClearCacheUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author czg
 * @date 2017/12/29
 */

public class MeFragment extends BaseFragment {


    @Bind(R.id.tv_app_version)
    TextView tvAppVersion;
    @Bind(R.id.rl_update)
    RelativeLayout rlUpdate;
    @Bind(R.id.rl_clear_cache)
    RelativeLayout rlClearCache;
    @Bind(R.id.rl_clear_about)
    RelativeLayout rlClearAbout;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvAppVersion.setText(Utils.getAppInfo(getActivity()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    private boolean iscache;
    Toast toast;

    @OnClick({R.id.rl_update, R.id.rl_clear_cache, R.id.rl_clear_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_update:
                tvAppVersion.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(toast!=null) {
                            toast.cancel();
                        }

                        toast = Toast.makeText(getActivity().getApplicationContext(), "已经是最新版本了", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, 1000);
                break;
            case R.id.rl_clear_cache:
                if (!iscache) {
                    tvAppVersion.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(toast!=null) {
                                toast.cancel();
                            }
                            toast = Toast.makeText(getActivity().getApplicationContext(), "清除了" + ClearCacheUtil.getTotalCacheSize(getActivity()) + "缓存", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }, 1000);

                    iscache = true;
                } else {
                    tvAppVersion.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(toast!=null) {
                                toast.cancel();
                            }
                            toast = Toast.makeText(getActivity().getApplicationContext(), "很干净，没有垃圾", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }, 1000);

                }


                break;
            case R.id.rl_clear_about:


                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);


                break;
        }
    }
}
