package com.bypay.yifu.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.HomeTabInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 商城 为你推荐
 * Created by tianchao on 2018/4/4.
 */

public class ShoppingMallGoodsListAdapter extends BaseQuickAdapter<HomeTabInfo, BaseViewHolder> {

    private List<HomeTabInfo> list;

    public ShoppingMallGoodsListAdapter(List<HomeTabInfo> list) {
        super(R.layout.item_mall_goods_list, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeTabInfo homeTabInfo) {

    }

}
