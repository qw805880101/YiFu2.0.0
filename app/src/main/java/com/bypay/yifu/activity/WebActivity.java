package com.bypay.yifu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bypay.yifu.R;
import com.bypay.yifu.base.BaseActivity;
import com.bypay.yifu.jsApi.LogOutToJS;
import com.bypay.yifu.jsApi.ShopCartToJs;
import com.bypay.yifu.jsApi.YiFuToJs;
import com.psylife.wrmvplibrary.utils.StatusBarUtil;
import com.psylife.wrmvplibrary.webview.Html5WebView;
import com.psylife.wrmvplibrary.webview.MyWebView;
import com.psylife.wrmvplibrary.webview.MyWebView.Take;
import com.tencent.smtt.sdk.ValueCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tianchao on 2018/4/8.
 */

public class WebActivity extends BaseActivity implements Take {

    @BindView(R.id.web_view)
    Html5WebView mWebView;

    @BindView(R.id.swipeLayout_home)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String webUrl;

    private MyWebView mMyWebView;

    private LogOutToJS mLogOutToJS;

    private ShopCartToJs mShopCartToJs;

    private YiFuToJs mYiFuToJs;

    public void setStatusBarColor() {
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.txt_color_f7f7f7));
    }

    @Override
    public View getTitleView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mSwipeRefreshLayout.setEnabled(false);
        mMyWebView = new MyWebView(this, mWebView, this);

        mLogOutToJS = new LogOutToJS(this);
        mShopCartToJs = new ShopCartToJs(this);
        mYiFuToJs = new YiFuToJs(mLogOutToJS, mShopCartToJs);
        mShopCartToJs.setGoodsInterface(this);
    }

    @Override
    public void initdata() {
        webUrl = this.getIntent().getStringExtra("url");
        mMyWebView.loadUrl(webUrl);
        mMyWebView.webSetting();
        mMyWebView.addJavascriptInterface(mYiFuToJs, "YSTWEB");
        mMyWebView.getWebView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (mMyWebView.getWebView().canGoBack()) {
//                mMyWebView.getWebView().goBack();
//            } else {
//                finish();
//            }
//            return true;
//        }
//
//        if (keyCode == event.KEYCODE_ENTER) {
//            /*隐藏软键盘*/
//            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (inputMethodManager.isActive()) {
//                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//            }
//
//        }

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            return false;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                Log.e("result", result + "");
                if (result == null) {
                    mUploadMessage.onReceiveValue(imageUri);
                    mUploadMessage = null;
                } else {
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                }
            }
        }
    }

    @SuppressWarnings("null")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
        return;
    }

    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>
    private Uri imageUri;

    @Override
    public void setPhoto(ValueCallback<Uri[]> filePathCallback, ValueCallback<Uri> uploadMsg) {
        if (filePathCallback != null) {
            this.mUploadCallbackAboveL = filePathCallback;
        } else if (uploadMsg != null) {
            this.mUploadMessage = uploadMsg;
        }
        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
        // Create the storage directory if it does not exist
        if (!imageStorageDir.exists()) {
            imageStorageDir.mkdirs();
        }
        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        imageUri = Uri.fromFile(file);

        final List<Intent> cameraIntents = new ArrayList<>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            i.setPackage(packageName);
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(i);
        }
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Intent chooserIntent = Intent.createChooser(i, "选择获取方式");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        this.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public boolean getUrl(String url) {
        return false;
    }

}
