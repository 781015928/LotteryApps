package com.lottery.jilinkuai3.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lottery.jilinkuai3.R;
import com.lottery.jilinkuai3.activity.HomeWebViewActivity;
import com.lottery.jilinkuai3.viewholder.HomeBigViewHolder;
import com.lottery.jilinkuai3.viewholder.HomeTextViewHolder;
import com.lottery.jilinkuai3.viewholder.HomeViewHolder;
import com.lottery.library.api.zx500.news.NewsModel;
import com.lottery.library.api.zx500.news.NewsRequest;
import com.lottery.library.base.BaseAdapter;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.base.OnItemClickListener;
import com.lottery.library.http.CallBack;

import java.util.List;

import butterknife.Bind;

/**
 * @author czg
 * @date 2017/12/28
 */

public class HomeTabFragment extends BaseFragment implements OnItemClickListener {
    @Bind(R.id.home_xrecyclerview)
    XRecyclerView mHomeXrecyclerview;
    private BaseAdapter<NewsModel> mBaseAdapter;
    private int page = 1;
    private String type;

    public HomeTabFragment() {

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void initData() {
        loadData();
    }

    private void loadData() {
        page = 0;
        sendHttp(new NewsRequest(page, type), new CallBack<List<NewsModel>>() {
            @Override
            public void onSuccess(List<NewsModel> response) {
                if (response.isEmpty()) {
                    mHomeXrecyclerview.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_thumb, null));
                } else {
                    mBaseAdapter.setData(response);
                    mHomeXrecyclerview.refreshComplete();
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                mHomeXrecyclerview.loadMoreComplete();
                throwable.printStackTrace();
            }
        });
    }

    private void loadMoreData() {
        page++;
        sendHttp(new NewsRequest(page, type), new CallBack<List<NewsModel>>() {
            @Override
            public void onSuccess(List<NewsModel> response) {
                if (!response.isEmpty()) {
                    mBaseAdapter.addData(response);
                    mHomeXrecyclerview.loadMoreComplete();
                } else {
                    mHomeXrecyclerview.setNoMore(true);
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                mHomeXrecyclerview.loadMoreComplete();
                throwable.printStackTrace();
            }
        });
    }

    @Override
    protected void initView() {
        mBaseAdapter = new BaseAdapter(getActivity(), HomeViewHolder.class, R.layout.item_pic_video_news) {
            @Override
            public int getItemViewType(int position) {
                return mBaseAdapter.getItem(position).getViewType();
            }
        };
        mBaseAdapter.registerViewHolders(NewsModel.BIG_TYPE, HomeBigViewHolder.class, R.layout.item_center_pic_news);
        mBaseAdapter.registerViewHolders(NewsModel.NO_IMAGE, HomeTextViewHolder.class, R.layout.item_text_news);
        mHomeXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeXrecyclerview.setAdapter(mBaseAdapter);
        mHomeXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
        mBaseAdapter.setOnItemClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position > 0) {
            NewsModel item = mBaseAdapter.getItem(position - 1);
            Intent intent = new Intent(getActivity(), HomeWebViewActivity.class);
            intent.putExtra(HomeWebViewActivity.NEWS_MODEL, item);
            startActivity(intent);
        }
    }
}
