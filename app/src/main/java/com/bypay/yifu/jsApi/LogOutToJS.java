package com.bypay.yifu.jsApi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.bypay.yifu.activity.HomeActivity;
import com.bypay.yifu.activity.UserLongin_Activity;
import com.psylife.wrmvplibrary.utils.ToastUtils;

/**
 * Created by tianchao on 2018/4/8.
 */

public class LogOutToJS {

    private Context mContext;

    public LogOutToJS(Context mContext) {
        this.mContext = mContext;
    }

    public void logout(String str) {
        Intent it = new Intent(mContext, UserLongin_Activity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ToastUtils.showToast(mContext, new String(str));
        mContext.startActivity(it);
    }

    public void goShopCart(int id) {
        if (id == 4) {
            ((Activity) mContext).finish();
        } else {
            Intent it = new Intent(mContext, HomeActivity.class);
            it.putExtra("id", id);
            mContext.startActivity(it);
            ((Activity) mContext).finish();
        }
    }


}
