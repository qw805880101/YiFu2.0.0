package com.bypay.yifu.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.HomeTabInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 地址管理列表
 * Created by tianchao on 2018/4/4.
 */

public class AddressManageAdapter extends BaseQuickAdapter<HomeTabInfo, BaseViewHolder> {

    private List<HomeTabInfo> list;

    public AddressManageAdapter(List<HomeTabInfo> list) {
        super(R.layout.item_address, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeTabInfo homeTabInfo) {
    }

}
