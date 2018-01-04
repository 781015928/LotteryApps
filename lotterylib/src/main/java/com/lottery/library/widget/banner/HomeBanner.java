package com.lottery.library.widget.banner;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.lottery.library.R;


/**
 * 类 名 称  ： HomeBanner.class
 * 作    者 ：  czg
 * 日    期 ：  2016/12/19.
 * 作    用 ： 在这里写一句话描述作用
 */
public class HomeBanner extends Banner {

    private float mRatio;

    public HomeBanner(Context context) {
        super(context);
    }

    public HomeBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HomeBanner);
        mRatio = typedArray.getFloat(R.styleable.HomeBanner_ratio, 1.5f);
        typedArray.recycle();
    }

    public HomeBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HomeBanner);
        mRatio = typedArray.getFloat(R.styleable.HomeBanner_ratio, 1.5f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hight = (int) (width / mRatio);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(hight, MeasureSpec.EXACTLY);//明确高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
