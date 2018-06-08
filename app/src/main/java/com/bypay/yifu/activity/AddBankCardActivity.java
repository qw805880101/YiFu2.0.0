package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.psylife.wrmvplibrary.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加结算卡
 * Created by tianchao on 2018/4/3.
 */
public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.et_add_card_number)
    EditText mEtAddCardNumber;
    @BindView(R.id.et_add_card_phone)
    EditText mEtAddCardPhone;
    @BindView(R.id.et_add_card_ver)
    EditText mEtAddCardVer;
    @BindView(R.id.bt_add_card_get_ver)
    Button mBtAddCardGetVer;
    @BindView(R.id.bt_add_card_next)
    Button mBtAddCardNext;

    @Override
    public View getTitleView() {
        return new TitleBuilder(this)
                .setLeftImage(R.drawable.bt_selector_nav_menu)
                .setTitleBgRes(R.color.txt_color_f7f7f7)
                .setTitleText("添加结算卡")
                .setRightText("支持银行")
                .setRightTextColor(this, R.color.txt_color_6f6f6f)
                .build();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.bt_add_card_get_ver, R.id.bt_add_card_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add_card_get_ver:
                break;
            case R.id.bt_add_card_next:
                break;
        }
    }
}
