package com.bypay.yifu.jsApi;

import android.content.Context;
import android.database.Cursor;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.bypay.yifu.MyApplication;
import com.bypay.yifu.Utils.Base64Class;
import com.bypay.yifu.api.GoodsInterface;
import com.bypay.yifu.bean.GoodsInfo;
import com.bypay.yifu.db.DBManager;
import com.bypay.yifu.db.MyDatabaseHelper;
import com.psylife.wrmvplibrary.utils.LogUtil;
import com.psylife.wrmvplibrary.utils.ToastUtils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 购物车js调用
 * Created by tianchao on 2018/4/8.
 */

public class ShopCartToJs {

    private Context mContext;

    public ShopCartToJs(Context mContext) {
        this.mContext = mContext;
    }

    private GoodsInterface mGoodsInterface;

    public void setGoodsInterface(GoodsInterface mGoodsInterface) {
        this.mGoodsInterface = mGoodsInterface;
    }

    /**
     * 购物车添加商品
     *
     * @param str
     */
    public void addGoods(String str) {

        str = new String(Base64Class.decode(str, 0));

        System.out.println(str);

        GoodsInfo goodsInfo = JSON.parseObject(str, GoodsInfo.class);

        goodsInfo.setMerId(MyApplication.MER_ID);

        DBManager dbManager = new DBManager(mContext);

        Cursor cursor = dbManager.queryBySkuId(goodsInfo.getMerId(), goodsInfo.getSkuId());

        String sql;

        if (cursor.moveToNext()) {
            goodsInfo.setGoodsNumber(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_NUM)));
            sql = dbManager.updateSql(goodsInfo.getMerId(), goodsInfo.getSkuId(), goodsInfo.getGoodsNumber() + 1);
        } else {
            sql = dbManager.insertSql(goodsInfo.getMerId(), goodsInfo.getSkuId(), goodsInfo.getImagePath()
                    , goodsInfo.getGoodsName(), Double.parseDouble(goodsInfo.getPrice()), goodsInfo.getGoodsNumber() + 1, "true");
        }
        dbManager.execSql(sql);

        if (mGoodsInterface != null)
            mGoodsInterface.setGoodsNum(MyApplication.GOODS_NUM += 1);

        ToastUtils.showToast(mContext, "商品添加成功");
//        cursor = dbManager.queryAndShow(goodsInfo.getMerId());
//        StringBuffer sb = new StringBuffer();
//        while (cursor.moveToNext()) {
//            sb.append("cardNum=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_MER_ID))).append(" ")
//                    .append("id=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_SKU_ID))).append(" ")
//                    .append("img=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_IMG))).append(" ")
//                    .append("name=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_NAME)))
//                    .append("price=").append(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_PRICE)))
//                    .append("num=").append(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_NUM)))
//                    .append("selected=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.IS_SELECTED)))
//                    .append("\n");
//        }
//        System.out.println(sb.toString());
    }

    /**
     * 清理购物车商品
     *
     * @param str
     */
    public void clearShopCard(String str) {
        str = new String(Base64Class.decode(str, 0));

        LogUtil.d(str);

        List<Map> maps = JSON.parseArray(str, Map.class);

        DBManager dbManager = new DBManager(mContext);

        for (Map<String, String> s : maps) {
            dbManager.execSql(dbManager.delSql(MyApplication.MER_ID, s.get("skuId")));
        }
    }

}
