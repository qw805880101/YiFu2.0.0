package com.bypay.yifu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.adapter.HomeTabAdapter;
import com.bypay.yifu.adapter.HomeRecommendAdapter;
import com.bypay.yifu.base.BaseFragment;
import com.bypay.yifu.bean.HomeBannerInfo;
import com.bypay.yifu.bean.HomeTabInfo;
import com.psylife.wrmvplibrary.utils.TitleBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 首页
 * Created by psylife00 on 2016/12/20.
 */

public class HomePageFragment extends BaseFragment {

    @BindView(R.id.txt_scan)
    TextView mTxtScan;
    @BindView(R.id.txt_payment_code)
    TextView mTxtPaymentCode;
    @BindView(R.id.txt_reserve_1)
    TextView mTxtReserve1;
    @BindView(R.id.txt_reserve_2)
    TextView mTxtReserve2;
    @BindView(R.id.recycler_function)
    RecyclerView mRecyclerFunction;
    @BindView(R.id.recycler_recommend)
    RecyclerView mRecyclerRecommend;

    HomeTabAdapter mHomeTabAdapter;
    HomeRecommendAdapter mHomeRecommendAdapter;

    @Override
    public View getTitleView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        List<HomeTabInfo> list = new ArrayList<>();
        String[] tabNames = {"代还信用卡", "手机数码", "电脑办公", "家用电器", "办信用卡"};
        int[] tabImages = {R.mipmap.home_tab_btn_icon1, R.mipmap.home_tab_btn_icon2,
                R.mipmap.home_tab_btn_icon3, R.mipmap.home_tab_btn_icon4, R.mipmap.home_tab_btn_icon5};
        for (int i = 0; i < Math.random() * 5; i++) {
            list.add(new HomeTabInfo(tabNames[i], tabImages[i]));
        }
        mHomeTabAdapter = new HomeTabAdapter(list);
        mRecyclerFunction.setLayoutManager(new GridLayoutManager(this.getContext(), 5));
        mRecyclerFunction.setAdapter(mHomeTabAdapter);

        String[] bannerNames = {"精选推荐", "办信用卡", "代还信用卡", "测试用的", "123"};
        int[] bannerImages = {R.mipmap.home_banner1, R.mipmap.home_banner1, R.mipmap.home_banner2,
                R.mipmap.home_banner3, R.mipmap.home_banner3};

        Random rand = new Random();
        List<HomeBannerInfo> homeBannerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            homeBannerInfos.add(new HomeBannerInfo("" + i, bannerNames[i], bannerImages[i], rand.nextInt(3)));
        }

        mHomeRecommendAdapter = new HomeRecommendAdapter(homeBannerInfos);
        mRecyclerRecommend.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerRecommend.setAdapter(mHomeRecommendAdapter);
        mRecyclerRecommend.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initLazyView() {

    }

    @OnClick({R.id.txt_scan, R.id.txt_payment_code, R.id.txt_reserve_1, R.id.txt_reserve_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_scan:
                break;
            case R.id.txt_payment_code:
                break;
            case R.id.txt_reserve_1:
                break;
            case R.id.txt_reserve_2:
                break;
        }
    }
}
