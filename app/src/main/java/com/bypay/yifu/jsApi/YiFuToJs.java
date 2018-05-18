package com.bypay.yifu.jsApi;

import android.webkit.JavascriptInterface;

/**
 * Created by tianchao on 2018/4/13.
 */

public class YiFuToJs {

    public LogOutToJS mLogOutToJS;

    public ShopCartToJs mShopCartToJs;

    public YiFuToJs(LogOutToJS mLogOutToJS, ShopCartToJs mShopCartToJs) {
        this.mLogOutToJS = mLogOutToJS;
        this.mShopCartToJs = mShopCartToJs;
    }

    /**
     * 添加购物车商品
     *
     * @param str
     */
    @JavascriptInterface
    public void addGoods(String str) {
        mShopCartToJs.addGoods(str);
    }

    /**
     * 清理购物车商品
     *
     * @param str
     */
    @JavascriptInterface
    public void clearShopCard(String str) {
        mShopCartToJs.clearShopCard(str);
    }

    /**
     * 返回首页
     *
     * @param str
     */
    @JavascriptInterface
    public void logout(String str) {
        mLogOutToJS.logout(str);
    }

    /**
     * 返回首页
     *
     * @param id 0 首页， 1 分类   2 购物车   3 我的  4 上一页
     */
    @JavascriptInterface
    public void goShopCart(int id) {
        mLogOutToJS.goShopCart(id);
    }

}
