package com.bypay.yifu.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;

import com.bypay.yifu.MyApplication;
import com.bypay.yifu.R;
import com.bypay.yifu.activity.WebActivity;
import com.bypay.yifu.base.BaseFragment;
import com.bypay.yifu.jsApi.LogOutToJS;
import com.bypay.yifu.jsApi.ShopCartToJs;
import com.bypay.yifu.jsApi.YiFuToJs;
import com.psylife.wrmvplibrary.utils.LogUtil;
import com.psylife.wrmvplibrary.webview.ChangeStatusListener;
import com.psylife.wrmvplibrary.webview.Html5WebView;
import com.psylife.wrmvplibrary.webview.MyWebView;
import com.psylife.wrmvplibrary.webview.MyWebView.Take;

import butterknife.BindView;

import static com.bypay.yifu.MyApplication.SESSION_ID;

/**
 * Created by tianchao on 2018/4/4.
 */

public class WebFragment extends BaseFragment implements Take {

    @BindView(R.id.web_view)
    Html5WebView mWebView;
    @BindView(R.id.swipeLayout_home)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String webUrl;

    private MyWebView mMyWebView;

    private LogOutToJS mLogOutToJS;

    private ShopCartToJs mShopCartToJs;

    private YiFuToJs mYiFuToJs;

    private boolean isResume = false;

    @Override
    protected void initLazyView() {
        if (super.getTag().equals("首页")) {
            webUrl = MyApplication.URL + "/index.jsp?jSessionId=" + SESSION_ID;
            LogUtil.d(webUrl);
        }
        if (super.getTag().equals("分类")) {
            webUrl = MyApplication.URL + "/jddata/class.jsp?jSessionId=" + SESSION_ID;
            mSwipeRefreshLayout.setEnabled(false);
        }
        if (super.getTag().equals("我的")) {
            webUrl = MyApplication.URL + "/ui-me.jsp?jSessionId=" + SESSION_ID;
        }
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
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mMyWebView = new MyWebView(this.getContext(), mWebView, this);

        mLogOutToJS = new LogOutToJS(this.getContext());
        mShopCartToJs = new ShopCartToJs(this.getContext());
        mYiFuToJs = new YiFuToJs(mLogOutToJS, mShopCartToJs);

        mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mMyWebView.loadUrl(webUrl);
            }
        });

        mMyWebView.setChangeStatusListener(new ChangeStatusListener() {
            @Override
            public void changeStatus(int y) {
                if (y == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void setPhoto(com.tencent.smtt.sdk.ValueCallback<Uri[]> filePathCallback, com.tencent.smtt.sdk.ValueCallback<Uri> uploadMsg) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public boolean getUrl(String url) {
//        if (!url.equals("")) {
//            int index = url.indexOf("/");
//            String temp = url.substring(index + 1);
//            String[] keyValue = temp.split("/");
//            for (String str : keyValue) {
//                if (str.contains("flag")) {
//                    String flag = str.replace("flag" + "=", "");
//                    LogUtil.d("flag = " + flag);
//                    if (flag.equals("Y")) {
//                    }
//                    break;
//                }
//            }
//        }
        Intent intent = new Intent(this.getContext(), WebActivity.class);
        intent.putExtra("url", url);
        this.getContext().startActivity(intent);
        return true;
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            String s = super.getTag();
//            if (super.getTag().equals("我的")) {
//                webUrl = MyApplication.URL + "/ui-me.jsp?jSessionId=" + SESSION_ID;
//                ToastUtils.showToast(this.getContext(), "刷新我的界面");
//            }
//        }
//    }


    @Override
    public void onPause() {
        super.onPause();
        isResume = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (super.getTag().equals("我的") && isResume) {
            webUrl = MyApplication.URL + "/ui-me.jsp?jSessionId=" + SESSION_ID;
            mMyWebView.loadUrl(webUrl);
        }
    }

    public void refreshFragment() {
        mMyWebView.loadUrl(webUrl);
    }
}
