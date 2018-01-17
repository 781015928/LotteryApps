package com.lottery.jilinkuai3.viewholder.zhongcaihome;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lottery.jilinkuai3.Constants;
import com.lottery.jilinkuai3.Utils;
import com.lottery.jilinkuai3.WebContentIntentBuilder;
import com.lottery.jilinkuai3.activity.TabActivity;
import com.lottery.jilinkuai3.activity.WebContentActivity;
import com.lottery.library.base.BaseAdapter;
import com.lottery.library.base.BaseViewHolder;
import com.lottery.library.base.OnItemClickListener;
import com.lottery.library.widget.banner.HomeBanner;
import com.lottery.library.widget.banner.listener.OnBannerClickListener;
import com.lottery.library.widget.banner.loader.ImageLoader;
import com.lottery.shishicaikaijiang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author czg
 * @date 2018/1/17.
 */

public class BannerViewHolder extends BaseViewHolder<ZCHomeModel> implements OnItemClickListener {
    @Bind(R.id.home_banner)
    HomeBanner mHomeBanner;
    @Bind(R.id.rec_view)
    RecyclerView mRecView;
    private String[] colors = new String[]{
            "#f15124", "#2cb044", "#278bf4", "#00a0e9", "#f1ce6b", "#7de2dd", "#ffd300", "#ff7f29"
    };
    private final ArrayList<ZCHomeModel> mZcHomeModels;
    private final BaseAdapter<ZCHomeModel> mZcHomeModelBaseAdapter;

    public BannerViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
        mZcHomeModels = new ArrayList<>();
        List<Constants.HomeApi> homeApis = Constants.getHomeApis();
        for (int i = 0; i < homeApis.size(); i++) {
            Constants.HomeApi homeApi = homeApis.get(i);
            ZCHomeModel zcHomeModel = new ZCHomeModel();
            zcHomeModel.title = homeApi.title;
            zcHomeModel.color = Color.parseColor(colors[i]);
            zcHomeModel.wight = 1;
            zcHomeModel.type = ZCHomeModel.ZCHOME_BORARD_TYPE;
            mZcHomeModels.add(zcHomeModel);
        }
        mRecView.setLayoutManager(new GridLayoutManager(viewGroup.getContext(),4));
        mZcHomeModelBaseAdapter = new BaseAdapter<>(viewGroup.getContext(), BoardViewHolder.class, R.layout.item_zhongcai_home_board);
        mRecView.setAdapter(mZcHomeModelBaseAdapter);
        mZcHomeModelBaseAdapter.setOnItemClickListener(this);
    }

    @Override
    public void setData(final ZCHomeModel data) {
        mZcHomeModelBaseAdapter.setData(mZcHomeModels);
        ArrayList<Integer> integers = new ArrayList<>();
        for (int res : data.bannersImage) {
            integers.add(res);
        }
        mHomeBanner.setImages(integers);
        mHomeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });
        mHomeBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {

                if (position <= 0) {
                    return;
                }
                List<String> strings = new ArrayList<>();
                strings.add("header");
                strings.add("footer");
                strings.add("ddhang");
                strings.add("sq1");
                strings.add("tshi.div");
                strings.add("ltan");
                WebContentIntentBuilder localWebContentIntentBuilder = new WebContentIntentBuilder();
                localWebContentIntentBuilder.context(itemView.getContext()).targetActivity(WebContentActivity.class).url(data.bannersUrl[position-1]).toRemovedTags(strings);
                Utils.navigateTo(localWebContentIntentBuilder);


            }
        });
        mHomeBanner. start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_zhongcai_home_banner;
    }

    @Override
    public void onItemClick(View view, int position) {
        Constants.HomeApi homeApi = Constants.getHomeApis().get(position);
        TabActivity.startTabActivity(itemView.getContext(),homeApi.type,homeApi.title);

    }
}
