package com.lottery.jilinkuai3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;
import android.support.v7.app.AppCompatActivity;

import com.lottery.jilinkuai3.activity.WebContentActivity;

import java.util.ArrayList;
import java.util.List;

public class WebContentIntentBuilder {
    private Context mContext;
    private Class mTarget;
    private String mTitle;
    private String mTitleSelector;
    private String[] mToRemovedTags;
    private ArrayList<String> ignoreText=new ArrayList<>();
    private String mUrl;

    @SuppressLint("RestrictedApi")
    public Intent build() {
        Preconditions.checkNotNull(this.mUrl);
        Preconditions.checkNotNull(this.mContext);
        if (mTarget.getSuperclass() != WebContentActivity.class) {
            mTarget = WebContentActivity.class;

        }
        Intent localIntent = new Intent(this.mContext, this.mTarget);
        localIntent.putExtra("extra_url", this.mUrl);
        if (this.mTitle != null) {
            localIntent.putExtra("extra_title", this.mTitle);
        }
        if (this.mToRemovedTags != null) {
            localIntent.putExtra("extra_removed_elements", this.mToRemovedTags);
        }
        if (this.mTitleSelector != null) {
            localIntent.putExtra("extra_title_selector", this.mTitleSelector);
        }
        if (this.ignoreText != null) {
            localIntent.putExtra(WebContentActivity.EXTRA_IGNORETEXT, this.ignoreText);
        }
        return localIntent;

    }

    @SuppressLint("RestrictedApi")
    public WebContentIntentBuilder context(@NonNull Context paramContext) {
        Preconditions.checkNotNull(paramContext);
        this.mContext = paramContext;
        return this;
    }

    public Context getContext() {
        return this.mContext;
    }

    @SuppressLint("RestrictedApi")
    public WebContentIntentBuilder targetActivity(@NonNull Class<? extends AppCompatActivity> paramClass) {
        Preconditions.checkNotNull(paramClass);
        this.mTarget = paramClass;
        return this;
    }

    public WebContentIntentBuilder title(@Nullable String paramString) {
        this.mTitle = paramString;
        return this;
    }

    @SuppressLint("RestrictedApi")
    public WebContentIntentBuilder titleSelector(@NonNull String paramString) {
        Preconditions.checkNotNull(paramString);
        this.mTitleSelector = paramString;
        return this;
    }

    @SuppressLint("RestrictedApi")
    public WebContentIntentBuilder toRemovedTags(@Nullable List<String> paramList) {
        if (paramList != null) {
            mToRemovedTags=new String[paramList.size()];
            for(int i=0;i<paramList.size();i++){
                mToRemovedTags[i]=paramList.get(i);
            }

        }
        ;
        return this;
    }
    public WebContentIntentBuilder toIgnoreText(@Nullable List<String> toIgnoreText) {
        if(toIgnoreText!=null) {
            ignoreText.addAll(toIgnoreText);
        }
        return this;
    }
    public WebContentIntentBuilder toIgnoreText(@Nullable String toIgnoreText) {
        if(toIgnoreText!=null) {
            ignoreText.add(toIgnoreText);
        }
        return this;
    }
    public WebContentIntentBuilder toRemovedTags(@Nullable String[] paramArrayOfString) {
        this.mToRemovedTags = paramArrayOfString;
        return this;
    }

    @SuppressLint("RestrictedApi")
    public WebContentIntentBuilder url(@NonNull String paramString) {
        Preconditions.checkNotNull(paramString);
        this.mUrl = paramString;
        return this;
    }
}