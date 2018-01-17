package com.lottery.jilinkuai3.viewholder.zhongcaihome;

import android.view.ViewGroup;

import com.lottery.library.base.BaseViewHolder;
import com.lottery.shishicaikaijiang.R;

/**
 * @author czg
 * @date 2018/1/17.
 */

public class ItemViewHolder extends BaseViewHolder<ZCHomeModel> {
    public ItemViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    @Override
    public void setData(ZCHomeModel data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_zhongcai_home_item;
    }
}
