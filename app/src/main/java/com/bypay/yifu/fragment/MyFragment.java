package com.bypay.yifu.fragment;

import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.activity.AddBankCardActivity;
import com.bypay.yifu.activity.AddressListActivity;
import com.bypay.yifu.activity.AddressManageActivity;
import com.bypay.yifu.activity.OpenShopActivity;
import com.bypay.yifu.activity.RealNameAuthActivity;
import com.bypay.yifu.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 * Created by tianchao on 2018/4/2.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.image_head)
    ImageView mImageHead;
    @BindView(R.id.txt_user_name)
    TextView mTxtUserName;
    @BindView(R.id.txt_auth_type)
    TextView mTxtAuthType;
    @BindView(R.id.txt_my_shop_select)
    TextView mTxtMyShopSelect;
    @BindView(R.id.txt_auth_type_select)
    TextView mTxtAuthTypeSelect;
    @BindView(R.id.txt_my_older_select)
    TextView mTxtMyOlderSelect;
    @BindView(R.id.txt_my_payment)
    TextView mTxtMyPayment;
    @BindView(R.id.txt_my_send_goods)
    TextView mTxtMySendGoods;
    @BindView(R.id.txt_my_take_goods)
    TextView mTxtMyTakeGoods;
    @BindView(R.id.txt_my_return_goods)
    TextView mTxtMyReturnGoods;
    @BindView(R.id.lin_vip)
    LinearLayout mLinVip;
    @BindView(R.id.lin_bank_card)
    LinearLayout mLinBankCard;
    @BindView(R.id.lin_address)
    LinearLayout mLinAddress;
    @BindView(R.id.lin_set)
    LinearLayout mLinSet;
    @BindView(R.id.lin_about)
    LinearLayout mLinAbout;
    Unbinder unbinder;
    @BindView(R.id.lin_my_bg)
    LinearLayout mLinMyBg;
    @BindView(R.id.my_scrollview)
    ScrollView mMyScrollview;
    @BindView(R.id.lin_my_title)
    LinearLayout mLinMyTitle;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mLinMyTitle.getBackground().setAlpha(0);
        mMyScrollview.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if ((mLinMyBg.getHeight() / 255 * scrollY) >= 255) {
                    mLinMyTitle.getBackground().setAlpha(255);
                } else {
                    mLinMyTitle.getBackground().setAlpha(mLinMyBg.getHeight() / 255 * scrollY);
                }
                if (scrollY < 0) {
                    mLinMyTitle.setAlpha(0); //刷新界面
                }
                if (scrollY >= 0) {
                    mLinMyTitle.setAlpha(1); //刷新界面
                }
            }
        });
    }

    @Override
    protected void initLazyView() {

    }

    @OnClick({R.id.txt_my_shop_select, R.id.txt_auth_type_select, R.id.txt_my_older_select, R.id.txt_my_payment,
            R.id.txt_my_send_goods, R.id.txt_my_take_goods, R.id.txt_my_return_goods, R.id.lin_vip,
            R.id.lin_bank_card, R.id.lin_address, R.id.lin_set, R.id.lin_about})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.txt_my_shop_select:
                intent.setClass(this.getActivity(), OpenShopActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.txt_auth_type_select:
                intent.setClass(this.getActivity(), RealNameAuthActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.txt_my_older_select:
                break;
            case R.id.txt_my_payment:
                break;
            case R.id.txt_my_send_goods:
                break;
            case R.id.txt_my_take_goods:
                break;
            case R.id.txt_my_return_goods:
                break;
            case R.id.lin_vip:
                break;
            case R.id.lin_bank_card:
                intent.setClass(this.getActivity(), AddBankCardActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.lin_address:
                intent.setClass(this.getActivity(), AddressManageActivity.class);
                this.getActivity().startActivity(intent);
                break;
            case R.id.lin_set:
                break;
            case R.id.lin_about:
                break;
        }
    }
}
