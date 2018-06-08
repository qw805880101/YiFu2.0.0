package com.bypay.yifu.base;

import android.content.Intent;

import com.bypay.yifu.R;
import com.bypay.yifu.api.Api;
import com.bypay.yifu.api.GoodsInterface;
import com.psylife.wrmvplibrary.RxManager;
import com.psylife.wrmvplibrary.base.WRBaseActivity;
import com.psylife.wrmvplibrary.data.net.RxService;
import com.psylife.wrmvplibrary.utils.LogUtil;
import com.psylife.wrmvplibrary.utils.StatusBarUtil;
import com.psylife.wrmvplibrary.utils.ToastUtils;

import rx.functions.Action1;

/**
 * Created by admin on 2017/8/23.
 */

public abstract class BaseActivity extends WRBaseActivity implements Action1<Throwable>, GoodsInterface {

    public Api mApi = RxService.createApi(Api.class);

    public RxManager mRxManager = new RxManager();

    public void setStatusBarColor() {
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.txt_color_f7f7f7));
    }


    /**
     * 显示错误日志
     *
     * @param code
     * @param msg
     */
    public void toastMessage(String code, String msg) {
        if (code.equals("1006")) {

        }
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void call(Throwable throwable) {
        LogUtil.d(throwable.getMessage());
        stopProgressDialog();
        ToastUtils.showToast(this, "网络错误");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }

    @Override
    public void setGoodsNum(int goodsNum) {
    }
}
