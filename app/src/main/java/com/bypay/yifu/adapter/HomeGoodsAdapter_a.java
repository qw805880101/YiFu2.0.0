package com.bypay.yifu.adapter;

import android.graphics.Paint;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.GoodsInfo;
import com.bypay.yifu.bean.HomeGoodsInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页功能模块
 * Created by tianchao on 2018/4/4.
 */

public class HomeGoodsAdapter_a extends BaseQuickAdapter<HomeGoodsInfo, BaseViewHolder> {

    private List<HomeGoodsInfo> list;

    public HomeGoodsAdapter_a(List<HomeGoodsInfo> list) {
        super(R.layout.item_home_goods_a, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeGoodsInfo homeGoodsInfo) {
        baseViewHolder.setText(R.id.txt_home_goods_name, homeGoodsInfo.getGoodsName())
        .setText(R.id.txt_home_goods_amt, "¥  "+homeGoodsInfo.getGoodsAmt())
        .setImageResource(R.id.image_home_goods, homeGoodsInfo.getGoodsImage());

        TextView oldAmtTxt = baseViewHolder.getView(R.id.txt_home_goods_old_amt);
        oldAmtTxt.setText("¥  "+homeGoodsInfo.getGoodsOldAmt());
        oldAmtTxt.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        oldAmtTxt.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
    }

}
