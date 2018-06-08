package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.psylife.wrmvplibrary.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 实名认证
 * Created by tianchao on 2018/4/3.
 */
public class RealNameAuthActivity extends BaseActivity {
    @BindView(R.id.et_real_name)
    EditText mEtRealName;
    @BindView(R.id.et_real_id_card)
    EditText mEtRealIdCard;
    @BindView(R.id.txt_real_sex)
    TextView mTxtRealSex;
    @BindView(R.id.txt_real_expiry)
    TextView mTxtRealExpiry;

    @Override
    public View getTitleView() {
        return new TitleBuilder(this)
                .setLeftImage(R.drawable.bt_selector_nav_menu)
                .setTitleText("实名认证")
                .setTitleBgRes(R.color.txt_color_f7f7f7)
                .build();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_real_name;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.txt_real_sex, R.id.txt_real_expiry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_real_sex:
                break;
            case R.id.txt_real_expiry:
                break;
        }
    }
}
