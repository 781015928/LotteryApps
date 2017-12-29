package com.lottery.app.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lottery.app.R;
import com.lottery.app.Utils;
import com.lottery.app.WebContentIntentBuilder;
import com.lottery.app.activity.OpenAwardDetailActivity;
import com.lottery.app.viewholder.LotteryHallViewHolder;
import com.lottery.library.api.caipiao163.lotterhall.LotterHallModel;
import com.lottery.library.api.caipiao163.lotterhall.LotterHallRequest;
import com.lottery.library.base.BaseAdapter;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.base.OnItemClickListener;
import com.lottery.library.http.CallBack;

import java.util.List;

import butterknife.Bind;

/**
 * @author czg
 * @date 2017/12/29
 */

public class LotteryHallFragment extends BaseFragment {


    @Bind(R.id.recyclerview)
    XRecyclerView mRecyclerview;
    private BaseAdapter<LotterHallModel> mBaseAdapter;

    @Override
    protected void initData() {

        sendHtml(new LotterHallRequest(), new CallBack<List<LotterHallModel>>() {
            @Override
            public void onSuccess(List<LotterHallModel> response) {
                mBaseAdapter.setData(response);
                mRecyclerview.refreshComplete();
            }

            @Override
            public void onFail(Throwable throwable) {
                mRecyclerview.refreshComplete();
            }
        });
    }

    @Override
    protected void initView() {
        mBaseAdapter = new BaseAdapter(getActivity(), LotteryHallViewHolder.class, R.layout.item_lottery_hall);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mBaseAdapter);
        mBaseAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LotterHallModel item = mBaseAdapter.getItem(position);

                WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
                localWebContentIntentBuilder.context(getContext()).targetActivity(OpenAwardDetailActivity.class).title("").url(item.getHref()).titleSelector("header h1").toRemovedTags(new String[]{"#header", "div.topSwipeWrap", "section.bottomBox", "a.indexIcon", "div.awardBottom"});
                Utils.navigateTo(localWebContentIntentBuilder);
            }
        });
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                initData();
            }

            @Override
            public void onLoadMore() {
                mRecyclerview.loadMoreComplete();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lotteryhall;
    }

}
