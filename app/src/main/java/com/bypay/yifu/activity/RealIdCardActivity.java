package com.bypay.yifu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.psylife.wrmvplibrary.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 身份证认证
 * Created by tianchao on 2018/4/3.
 */

public class RealIdCardActivity extends BaseActivity {

    @BindView(R.id.image_pic1)
    ImageView mImagePic1;
    @BindView(R.id.image_pic2)
    ImageView mImagePic2;
    @BindView(R.id.image_pic3)
    ImageView mImagePic3;
    @BindView(R.id.bt_real_id_card_submit)
    Button mBtRealIdCardSubmit;

    @Override
    public View getTitleView() {
        return new TitleBuilder(this)
                .setLeftImage(R.drawable.bt_selector_nav_menu)
                .setTitleText("身份认证")
                .setTitleBgRes(R.color.txt_color_f7f7f7)
                .build();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_real_id_card;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.image_pic1, R.id.image_pic2, R.id.image_pic3, R.id.bt_real_id_card_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_pic1:
                break;
            case R.id.image_pic2:
                break;
            case R.id.image_pic3:
                break;
            case R.id.bt_real_id_card_submit:
                break;
        }
    }
}
