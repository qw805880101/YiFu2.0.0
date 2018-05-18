package com.bypay.yifu.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.GoodsInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页功能模块
 * Created by tianchao on 2018/4/4.
 */

public class HomeRecommendAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    private List<GoodsInfo> list;

    private Map<Integer, Boolean> isChose = new HashMap<>();

    private HomeGoodsAdapter_a mHomeGoodsAdapterA;
    private HomeGoodsAdapter_b mHomeGoodsAdapterB;
    private HomeGoodsAdapter_c mHomeGoodsAdapterC;

    public HomeRecommendAdapter(List<GoodsInfo> list) {
        super(R.layout.item_home_recommend, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final GoodsInfo mGoodsInfo) {

        RecyclerView recyclerView = baseViewHolder.getView(R.id.recycler_goods);
        if (baseViewHolder.getLayoutPosition() == 0) {
            List<GoodsInfo> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add(new GoodsInfo());
            }
            mHomeGoodsAdapterA = new HomeGoodsAdapter_a(list);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            recyclerView.setAdapter(mHomeGoodsAdapterA);
        }

        if (baseViewHolder.getLayoutPosition() == 1) {
            List<GoodsInfo> list = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                list.add(new GoodsInfo());
            }
            mHomeGoodsAdapterB = new HomeGoodsAdapter_b(list);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setAdapter(mHomeGoodsAdapterB);
        }

        if (baseViewHolder.getLayoutPosition() == 2) {
            List<GoodsInfo> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(new GoodsInfo());
            }
            mHomeGoodsAdapterC = new HomeGoodsAdapter_c(list);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setAdapter(mHomeGoodsAdapterC);
        }

    }

}
