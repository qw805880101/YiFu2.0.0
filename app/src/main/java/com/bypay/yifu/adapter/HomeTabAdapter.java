package com.bypay.yifu.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.HomeTabInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 首页功能模块
 * Created by tianchao on 2018/4/4.
 */

public class HomeTabAdapter extends BaseQuickAdapter<HomeTabInfo, BaseViewHolder> {

    private List<HomeTabInfo> list;

    public HomeTabAdapter(List<HomeTabInfo> list) {
        super(R.layout.item_home_tab, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeTabInfo homeTabInfo) {
        TextView txtHomeTab = baseViewHolder.getView(R.id.txt_home_tab);
        ImageView imgHomeTab = baseViewHolder.getView(R.id.image_home_tab);
        txtHomeTab.setText(homeTabInfo.getTabName());
        imgHomeTab.setImageResource(homeTabInfo.getTabImage());
    }

}
