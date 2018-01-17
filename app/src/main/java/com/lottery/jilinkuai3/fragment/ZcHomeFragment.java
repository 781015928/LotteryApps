package com.lottery.jilinkuai3.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lottery.jilinkuai3.activity.HomeWebViewActivity;
import com.lottery.jilinkuai3.viewholder.zhongcaihome.BannerViewHolder;
import com.lottery.jilinkuai3.viewholder.zhongcaihome.HomeZCTextViewHolder;
import com.lottery.jilinkuai3.viewholder.zhongcaihome.HomeZcBigViewHolder;
import com.lottery.jilinkuai3.viewholder.zhongcaihome.ZCHomeModel;
import com.lottery.library.api.zx500.news.NewsModel;
import com.lottery.library.api.zx500.news.NewsRequest;
import com.lottery.library.base.BaseAdapter;
import com.lottery.library.base.BaseFragment;
import com.lottery.library.base.OnItemClickListener;
import com.lottery.library.http.CallBack;
import com.lottery.shishicaikaijiang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author czg
 * @date 2018/1/17.
 */

public class ZcHomeFragment extends BaseFragment implements OnItemClickListener {
    @Bind(R.id.home_xrecyclerview)
    XRecyclerView mHomeXrecyclerview;
    private BaseAdapter<ZCHomeModel> mBaseAdapter;

    private String[] bannerUrl = new String[]{
            "http://m.zhcw.com/khd/zy/banner/14970150.shtml",
            "http://m.zhcw.com/khd/zy/banner/14879698.shtml",
            "http://m.zhcw.com/khd/zx/sp/fcybs/14919037.shtml"
    };
    private int page = 1;

    @Override
    protected void initData() {
        loadData();

    }

    @Override
    protected void initView() {
        mHomeXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBaseAdapter = new BaseAdapter(getActivity()) {
            @Override
            public int getItemViewType(int position) {
                return mBaseAdapter.getItem(position).type;
            }
        };
        mBaseAdapter.registerViewHolders(ZCHomeModel.ZCHOME_BANNER_TYPE, BannerViewHolder.class, R.layout.item_zhongcai_home_banner);
        mBaseAdapter.registerViewHolders(NewsModel.BIG_TYPE, HomeZcBigViewHolder.class, R.layout.item_center_pic_news);
        mBaseAdapter.registerViewHolders(NewsModel.NO_IMAGE, HomeZCTextViewHolder.class, R.layout.item_text_news);
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
        return R.layout.fragment_zc_home;
    }

    private void loadData() {
        page = 0;
        sendHttp(new NewsRequest(page, "1"), new CallBack<List<NewsModel>>() {
            @Override
            public void onSuccess(List<NewsModel> response) {
                if (response.isEmpty()) {
                    mHomeXrecyclerview.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_thumb, null));
                } else {
                    loadData(response);
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
        sendHttp(new NewsRequest(page, "1"), new CallBack<List<NewsModel>>() {
            @Override
            public void onSuccess(List<NewsModel> response) {
                if (!response.isEmpty()) {
                    loadMoreData(response);
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

    public void loadData(List<NewsModel> response) {
        ArrayList<ZCHomeModel> zcHomeModels = new ArrayList<>();
        ZCHomeModel zcHomeModel_Banner = new ZCHomeModel();
        zcHomeModel_Banner.bannersImage = new int[]{R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3};
        zcHomeModel_Banner.bannersUrl = bannerUrl;
        zcHomeModel_Banner.type = ZCHomeModel.ZCHOME_BANNER_TYPE;
        zcHomeModel_Banner.wight = 4;
        zcHomeModels.add(zcHomeModel_Banner);
        for (NewsModel newsModel : response) {
            ZCHomeModel zcHomeMode = new ZCHomeModel();
            zcHomeMode.data = newsModel;
            zcHomeMode.type = NewsModel.BIG_TYPE;
            zcHomeModels.add(zcHomeMode);

        }
        mBaseAdapter.setData(zcHomeModels);
    }

    public void loadMoreData(List<NewsModel> response) {
        ArrayList<ZCHomeModel> zcHomeModels = new ArrayList<>();
        for (NewsModel newsModel : response) {
            ZCHomeModel zcHomeMode = new ZCHomeModel();
            zcHomeMode.data = newsModel;
            zcHomeMode.type = NewsModel.BIG_TYPE;
            zcHomeModels.add(zcHomeMode);

        }
        mBaseAdapter.addData(zcHomeModels);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position > 0) {
            ZCHomeModel item = mBaseAdapter.getItem(position - 1);
            switch (item.type) {

                case ZCHomeModel.ZCHOME_BORARD_TYPE:

                    break;
                case NewsModel.BIG_TYPE:
                    NewsModel data = item.data;
                    Intent intent = new Intent(getActivity(), HomeWebViewActivity.class);
                    intent.putExtra(HomeWebViewActivity.NEWS_MODEL, data);
                    startActivity(intent);
                    break;
            }

        }
    }
}
