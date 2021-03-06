package com.lottery.jilinkuai3.viewholder.zhongcaihome;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lottery.library.api.zx500.news.NewsModel;
import com.lottery.library.base.BaseViewHolder;
import com.lottery.library.utils.ImageLoader;
import com.lottery.library.utils.TimeUtils;
import com.lottery.library.widget.BorderTextView;
import com.lottery.shishicaikaijiang.R;

import butterknife.Bind;

/**
 * Created by czg on 2017/12/28.
 */

public class HomeZcBigViewHolder extends BaseViewHolder<ZCHomeModel> {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_img)
    ImageView mIvImg;
    @Bind(R.id.iv_play)
    ImageView mIvPlay;
    @Bind(R.id.tv_tag)
    BorderTextView mTvTag;
    @Bind(R.id.tv_author)
    TextView mTvAuthor;
    @Bind(R.id.tv_comment_num)
    TextView mTvCommentNum;
    @Bind(R.id.tv_time)
    TextView mTvTime;

    public HomeZcBigViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);


    }


    @Override
    public void setData(ZCHomeModel model) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        layoutParams.setMargins(0, 20, 0, 0);


        NewsModel data = model.data;
        ImageLoader.setImage(mIvImg, data.getCover());
        mTvTime.setText(TimeUtils.timeFormat(data.getExpiretime(), "MM-dd HH:mm"));
        mTvAuthor.setText(data.getNickname());
        mTvTitle.setText(data.getTitle());
        mTvTag.setText(data.getLs());
        mTvTag.setVisibility(TextUtils.isEmpty(data.getLs()) ? View.GONE : View.VISIBLE);
        if (data.getTargets() != null) {
            mTvCommentNum.setText(data.getTargets().getMaxrednum());
        } else {
            mTvCommentNum.setText("");
        }

    }
}
