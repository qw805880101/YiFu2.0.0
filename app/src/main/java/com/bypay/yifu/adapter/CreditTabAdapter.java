package com.bypay.yifu.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.HomeTabInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 信用管家顶部按钮
 * Created by tianchao on 2018/4/4.
 */

public class CreditTabAdapter extends BaseQuickAdapter<HomeTabInfo, BaseViewHolder> {

    private List<HomeTabInfo> list;

    public CreditTabAdapter(List<HomeTabInfo> list) {
        super(R.layout.item_credit_tab, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeTabInfo homeTabInfo) {
        TextView txtHomeTab = baseViewHolder.getView(R.id.txt_mall_name);
        ImageView imgHomeTab = baseViewHolder.getView(R.id.image_mall_tab);
        txtHomeTab.setText(homeTabInfo.getTabName());
        imgHomeTab.setImageResource(homeTabInfo.getTabImage());
    }

}
