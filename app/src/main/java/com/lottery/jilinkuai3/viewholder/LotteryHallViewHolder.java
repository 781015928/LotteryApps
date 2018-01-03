package com.lottery.jilinkuai3.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottery.jilinkuai3.R;
import com.lottery.library.api.caipiao163.lotterhall.LotterHallModel;
import com.lottery.library.base.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by czg on 2017/12/29.
 */

public class LotteryHallViewHolder extends BaseViewHolder<LotterHallModel> {
    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.ll_ball_content)
    LinearLayout mLlBallContent;
    @Bind(R.id.tv_date)
    TextView mTvDate;

    public LotteryHallViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_lottery_hall;
    }

    @Override
    public void setData(LotterHallModel data) {
        mTvContent.setText(data.getTittle());
        mTvDate.setText(data.getDate());
        List<LotterHallModel.NumBall> numBalls = data.getNumBalls();
        mLlBallContent.removeAllViews();
        if (numBalls.isEmpty()) {
            if (data.getBtn() != null) {
                TextView ball = (TextView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_ball, (ViewGroup) itemView, false);
                ball.setText(data.getBtn());
                mLlBallContent.addView(ball);
            }

        } else {
            for (LotterHallModel.NumBall numBall : numBalls) {
                if ("smallRedball".equals(numBall.getColor())) {
                    TextView ball = (TextView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_redball, (ViewGroup) itemView, false);
                    ball.setText(numBall.getNum());
                    mLlBallContent.addView(ball);
                } else if ("smallBlueball".equals(numBall.getColor())) {
                    TextView ball = (TextView) LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_blueball, (ViewGroup) itemView, false);
                    ball.setText(numBall.getNum());
                    mLlBallContent.addView(ball);
                }
            }

        }


    }
}
