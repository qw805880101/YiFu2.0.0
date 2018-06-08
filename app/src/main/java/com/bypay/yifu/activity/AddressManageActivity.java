package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.psylife.wrmvplibrary.utils.TitleBuilder;
import com.psylife.wrmvplibrary.widget.SwitchView;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import butterknife.BindView;

public class AddressManageActivity extends BaseActivity implements OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, AddressSelector.onSelectorAreaPositionListener {


    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_add_card_number)
    EditText mEtAddCardNumber;
    @BindView(R.id.remember_pwd)
    SwitchView mRememberPwd;
    @BindView(R.id.bt_add_address)
    Button mBtAddAddress;
    @BindView(R.id.txt_address)
    TextView mTxtAddress;
    @BindView(R.id.txt_address_select)
    TextView mTxtAddressSelect;

    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private int provincePosition;
    private int cityPosition;
    private int countyPosition;
    private int streetPosition;

    @Override
    public View getTitleView() {
        return new TitleBuilder(this)
                .setLeftImage(R.drawable.bt_selector_nav_menu)
                .setTitleText("添加地址")
                .setTitleBgRes(R.color.txt_color_f7f7f7)
                .build();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_manage;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTxtAddressSelect.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        if (v == mTxtAddressSelect) {
            if (dialog != null) {
                dialog.show();
            } else {
                dialog = new BottomDialog(this);
                dialog.setOnAddressSelectedListener(this);
                dialog.setDialogDismisListener(this);
                dialog.setTextSize(14);//设置字体的大小
                dialog.setIndicatorBackgroundColor(R.color.txt_color_ff5f59);//设置指示器的颜色
                dialog.setTextSelectedColor(R.color.txt_color_222222);//设置字体已选择颜色
                dialog.setTextUnSelectedColor(R.color.txt_color_ff5f59);//设置字体没有获得焦点的颜色
//            dialog.setDisplaySelectorArea("31",1,"2704",1,"2711",0,"15582",1);//设置已选中的地区
                dialog.setSelectorAreaPositionListener(this);
                dialog.show();
            }
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        mTxtAddress.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {
        this.provincePosition = provincePosition;
        this.cityPosition = cityPosition;
        this.countyPosition = countyPosition;
        this.streetPosition = streetPosition;
    }
}
