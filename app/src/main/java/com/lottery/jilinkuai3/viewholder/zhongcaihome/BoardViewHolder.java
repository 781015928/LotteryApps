package com.lottery.jilinkuai3.viewholder.zhongcaihome;

import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lottery.library.base.BaseViewHolder;
import com.lottery.library.widget.RatioImageView;
import com.lottery.shishicaikaijiang.R;

import butterknife.Bind;

/**
 * @author czg
 * @date 2018/1/17.
 */

public class BoardViewHolder extends BaseViewHolder<ZCHomeModel> {


    @Bind(R.id.image)
    RatioImageView mImage;
    @Bind(R.id.tv_title)
    TextView mTvTitle;

    public BoardViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
        mImage.setRatio(1);
    }

    @Override
    public void setData(ZCHomeModel data) {
        GradientDrawable myGrad = (GradientDrawable) mImage.getDrawable();
        mTvTitle.setText(data.title);
        myGrad.setColor(data.color);
        if (data.title.length() > 3) {
            mTvTitle.setEms(2);

        }else {
            mTvTitle.setEms(4);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_zhongcai_home_board;
    }
}
