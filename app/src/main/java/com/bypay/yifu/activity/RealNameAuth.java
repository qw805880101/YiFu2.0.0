package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.psylife.wrmvplibrary.utils.TitleBuilder;

public class RealNameAuth extends BaseActivity {

    @Override
    public View getTitleView() {
        return new TitleBuilder(this)
                .setLeftImage(R.mipmap.ic_back)
                .setTitleText("实名认证")
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
}
