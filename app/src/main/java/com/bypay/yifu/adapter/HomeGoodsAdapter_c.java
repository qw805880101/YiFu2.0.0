package com.bypay.yifu.adapter;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.GoodsInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页功能模块
 * Created by tianchao on 2018/4/4.
 */

public class HomeGoodsAdapter_c extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    private List<GoodsInfo> list;

    private Map<Integer, Boolean> isChose = new HashMap<>();

    public HomeGoodsAdapter_c(List<GoodsInfo> list) {
        super(R.layout.item_home_goods_c, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final GoodsInfo mGoodsInfo) {

    }

}
