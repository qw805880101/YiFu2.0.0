package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.psylife.wrmvplibrary.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 银行卡认证
 * Created by tianchao on 2018/4/3.
 */

public class BankCardAuthActivity extends BaseActivity {

    @BindView(R.id.image_bank_card)
    ImageView mImageBankCard;
    @BindView(R.id.bt_bank_card_auth_submit)
    Button mBtBankCardAuthSubmit;

    @Override
    public View getTitleView() {
        return new TitleBuilder(this)
                .setLeftImage(R.drawable.bt_selector_nav_menu)
                .setTitleText("银行卡认证")
                .setTitleBgRes(R.color.txt_color_f7f7f7)
                .build();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card_auth;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.image_bank_card, R.id.bt_bank_card_auth_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_bank_card:
                break;
            case R.id.bt_bank_card_auth_submit:
                break;
        }
    }
}
