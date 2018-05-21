package com.bypay.yifu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bypay.yifu.R;
import com.bypay.yifu.adapter.HomeGoodsAdapter_a;
import com.bypay.yifu.adapter.ShoppingMallTabAdapter;
import com.bypay.yifu.base.BaseFragment;
import com.bypay.yifu.bean.HomeTabInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CreditStewardshipFragment extends BaseFragment {
    @BindView(R.id.credit_stewardship_tab)
    RecyclerView mCreditStewardshipTab;

    private ShoppingMallTabAdapter mShoppingMallTabAdapter;

    @Override
    protected void initLazyView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_credit_stewardship;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        List<HomeTabInfo> list = new ArrayList<>();
        String[] tabNames = {"账户总览", "免息期", "办信用卡", "我要贷款"};
        int[] tabImages = {R.mipmap.list_icon_account_overview, R.mipmap.list_icon_interest_free_period,
                R.mipmap.list_icon_credit_card, R.mipmap.list_icon_loan};
        for (int i = 0; i < 4; i++) {
            list.add(new HomeTabInfo(tabNames[i], tabImages[i]));
        }
        mShoppingMallTabAdapter = new ShoppingMallTabAdapter(list);
        mCreditStewardshipTab.setLayoutManager(new GridLayoutManager(this.getActivity(), 4));
        mCreditStewardshipTab.setAdapter(mShoppingMallTabAdapter);
    }

}
