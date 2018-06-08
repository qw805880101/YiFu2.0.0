package com.bypay.yifu.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.bean.GoodsInfo;
import com.bypay.yifu.bean.HomeBannerInfo;
import com.bypay.yifu.bean.HomeGoodsInfo;
import com.bypay.yifu.view.InterestSpaceItemDecoration;
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

public class HomeRecommendAdapter extends BaseQuickAdapter<HomeBannerInfo, BaseViewHolder> {

    private List<HomeBannerInfo> list;

    private HomeGoodsAdapter_a mHomeGoodsAdapterA;
    private HomeGoodsAdapter_b mHomeGoodsAdapterB;
    private HomeGoodsAdapter_c mHomeGoodsAdapterC;

    public HomeRecommendAdapter(List<HomeBannerInfo> list) {
        super(R.layout.item_home_recommend, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeBannerInfo homeBannerInfo) {
        TextView textView = baseViewHolder.getView(R.id.txt_bannerName);
        textView.setText(homeBannerInfo.getBannerName());
        ImageView imageView = baseViewHolder.getView(R.id.image_home_banner);
        imageView.setImageResource(homeBannerInfo.getBannerImage());
        RecyclerView recyclerView = baseViewHolder.getView(R.id.recycler_goods);
        if (homeBannerInfo.getType() == 0) {
            String[] goodsNames = {"缓解湿疹牛皮癣", "补水保湿", "美白亮肤抗皱"};
            String[] goodsAmts = {"99.00", "319.00", "349.00"};
            String[] goodsOldAmts = {"139.00", "799.00", "560.00"};
            int[] goodsImages = {R.mipmap.home_commodity_iamge1, R.mipmap.home_commodity_iamge2, R.mipmap.home_commodity_iamge3};
            List<HomeGoodsInfo> homeGoodsInfos = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                homeGoodsInfos.add(new HomeGoodsInfo(goodsNames[i], goodsAmts[i], goodsOldAmts[i], goodsImages[i]));
            }
            mHomeGoodsAdapterA = new HomeGoodsAdapter_a(homeGoodsInfos);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            recyclerView.setAdapter(mHomeGoodsAdapterA);
        }

        if (homeBannerInfo.getType() == 1) {
            String[] goodsTitle = {"交通银行Visa金卡", "交行Y-POWER信用卡"};
            String[] goodsHint = {"免息期长达56天", "取现手续费极低"};
            int[] goodsImages = {R.mipmap.banner2_pic2, R.mipmap.banner2_pic1};
            List<HomeGoodsInfo> homeGoodsInfos = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                homeGoodsInfos.add(new HomeGoodsInfo(goodsTitle[i], goodsHint[i], goodsImages[i]));
            }
            mHomeGoodsAdapterB = new HomeGoodsAdapter_b(homeGoodsInfos);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setAdapter(mHomeGoodsAdapterB);
        }

        if (homeBannerInfo.getType() == 2) {
            String[] goodsTitle = {"代还信用卡", "信用卡分期指南", "轻轻松松还款", "借款记录快捷查", "代还信用卡", "信用卡分期指南", "轻轻松松还款", "借款记录快捷查"};
            String[] goodsHint = {"快速帮您还清账单", "轻松不逾期", "还款攻略详解", "操作一目了然", "快速帮您还清账单", "轻松不逾期", "还款攻略详解", "操作一目了然"};
            int[] goodsImages = {R.mipmap.home_icon1, R.mipmap.home_icon2, R.mipmap.home_icon3,
                    R.mipmap.home_icon4, R.mipmap.home_icon1, R.mipmap.home_icon2, R.mipmap.home_icon3,
                    R.mipmap.home_icon4};
            List<HomeGoodsInfo> homeGoodsInfos = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                homeGoodsInfos.add(new HomeGoodsInfo(goodsTitle[i], goodsHint[i], goodsImages[i]));
            }
            mHomeGoodsAdapterC = new HomeGoodsAdapter_c(homeGoodsInfos);
            recyclerView.addItemDecoration(new InterestSpaceItemDecoration(mContext.getResources().getDimensionPixelSize(R.dimen.bottom_1), list.size()));
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setAdapter(mHomeGoodsAdapterC);
        }

    }

}
