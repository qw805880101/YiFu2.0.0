package com.bypay.yifu.adapter;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.HomeTabInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 信用管家
 * Created by tianchao on 2018/4/4.
 */

public class CreditListAdapter extends BaseQuickAdapter<HomeTabInfo, BaseViewHolder> {

    private List<HomeTabInfo> list;

    public CreditListAdapter(List<HomeTabInfo> list) {
        super(R.layout.item_credit_list, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeTabInfo homeTabInfo) {

    }

}
