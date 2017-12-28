package com.lottery.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lottery.library.http.CallBack;
import com.lottery.library.http.HttpClient;
import com.lottery.library.http.ApiRequest;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {

            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
            initView();
            initData();
        }
        return mRootView;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected final <T> void sendHttp(final ApiRequest<T> request, final CallBack<T> callBack) {
        HttpClient.getInstances().send(request, callBack);
    }

}
