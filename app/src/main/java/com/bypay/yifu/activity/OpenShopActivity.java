package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenShopActivity extends BaseActivity {
    @BindView(R.id.et_shop_name)
    EditText mEtShopName;  //店铺名称
    @BindView(R.id.txt_jop)
    TextView mTxtJop; //职业
    @BindView(R.id.txt_jop_select)
    TextView mTxtJopSelect; //选择职业
    @BindView(R.id.et_address)
    EditText mEtAddress; //地址
    @BindView(R.id.et_mail)
    EditText mEtMail; //邮箱
    @BindView(R.id.txt_shop_type)
    TextView mTxtShopType; //经营产品类型
    @BindView(R.id.txt_shop_type_select)
    TextView mTxtShopTypeSelect; //经营产品类型选择
    @BindView(R.id.txt_shop_price)
    TextView mTxtShopPrice; //产品单价
    @BindView(R.id.txt_shop_price_select)
    TextView mTxtShopPriceSelect;//产品单价选择
    @BindView(R.id.txt_shop_experience)
    TextView mTxtShopExperience;//微商经营经验
    @BindView(R.id.txt_shop_experience_select)
    TextView mTxtShopExperienceSelect;//微商经营经验选择
    @BindView(R.id.txt_shop_when)
    TextView mTxtShopWhen;//是否有门店
    @BindView(R.id.txt_shop_when_select)
    TextView mTxtShopWhenSelect;//是否有门店选择
    @BindView(R.id.bt_shop_next)
    Button mBtShopNext;//下一步

    @Override
    public int getLayoutId() {
        return R.layout.activity_open_shop;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.txt_jop_select, R.id.txt_shop_type_select, R.id.txt_shop_price_select, R.id.txt_shop_experience_select, R.id.txt_shop_when_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_jop_select:
                break;
            case R.id.txt_shop_type_select:
                break;
            case R.id.txt_shop_price_select:
                break;
            case R.id.txt_shop_experience_select:
                break;
            case R.id.txt_shop_when_select:
                break;
        }
    }
}
