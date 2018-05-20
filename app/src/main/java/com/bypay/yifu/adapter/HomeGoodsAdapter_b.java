package com.bypay.yifu.adapter;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.HomeGoodsInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 首页功能模块
 * Created by tianchao on 2018/4/4.
 */

public class HomeGoodsAdapter_b extends BaseQuickAdapter<HomeGoodsInfo, BaseViewHolder> {

    private List<HomeGoodsInfo> list;

    public HomeGoodsAdapter_b(List<HomeGoodsInfo> list) {
        super(R.layout.item_home_goods_b, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeGoodsInfo homeGoodsInfo) {
        baseViewHolder.setText(R.id.txt_home_goods_title, homeGoodsInfo.getGoodsTitle())
                .setText(R.id.txt_home_goods_hint, homeGoodsInfo.getGoodsHint())
                .setImageResource(R.id.image_home_goods, homeGoodsInfo.getGoodsImage());
    }

}
