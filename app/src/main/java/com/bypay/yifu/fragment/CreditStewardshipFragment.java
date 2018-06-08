package com.bypay.yifu.fragment;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bypay.yifu.R;
import com.bypay.yifu.adapter.CreditListAdapter;
import com.bypay.yifu.adapter.CreditTabAdapter;
import com.bypay.yifu.base.BaseFragment;
import com.bypay.yifu.bean.HomeTabInfo;
import com.bypay.yifu.view.SpringScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CreditStewardshipFragment extends BaseFragment {

    @BindView(R.id.credit_scrollview)
    SpringScrollView mNestedScrollView;
    @BindView(R.id.lin_banner)
    LinearLayout mLinBanner;
    @BindView(R.id.lin_credit_title)
    RelativeLayout mCreditTitle;
    @BindView(R.id.credit_stewardship_tab)
    RecyclerView mCreditStewardshipTab;
    @BindView(R.id.credit_stewardship_list)
    RecyclerView mCreditList;


    private CreditTabAdapter mCreditTabAdapter;
    private CreditListAdapter mCreditListAdapter;

    @Override
    protected void initLazyView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_credit_stewardship;
    }

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        List<HomeTabInfo> list = new ArrayList<>();
        String[] tabNames = {"账户总览", "免息期", "办信用卡", "我要贷款"};
        int[] tabImages = {R.mipmap.list_icon_account_overview, R.mipmap.list_icon_interest_free_period,
                R.mipmap.list_icon_credit_card, R.mipmap.list_icon_loan};
        for (int i = 0; i < 4; i++) {
            list.add(new HomeTabInfo(tabNames[i], tabImages[i]));
        }
        mCreditTabAdapter = new CreditTabAdapter(list);
        mCreditStewardshipTab.setLayoutManager(new GridLayoutManager(this.getActivity(), 4));
        mCreditStewardshipTab.setAdapter(mCreditTabAdapter);

        mCreditListAdapter = new CreditListAdapter(list);
        mCreditList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mCreditList.setAdapter(mCreditListAdapter);
        mCreditList.setNestedScrollingEnabled(false);

        mCreditTitle.getBackground().setAlpha(0);
        mNestedScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if ((mLinBanner.getHeight() / 255 * scrollY) >= 255) {
                    mCreditTitle.getBackground().setAlpha(255);
                } else {
                    mCreditTitle.getBackground().setAlpha(mLinBanner.getHeight() / 255 * scrollY);
                }
            }
        });

    }

}
