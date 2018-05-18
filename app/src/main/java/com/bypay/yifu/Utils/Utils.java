package com.bypay.yifu.Utils;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.bypay.yifu.MyApplication;
import com.bypay.yifu.db.DBManager;
import com.bypay.yifu.db.MyDatabaseHelper;

/**
 * Created by tianchao on 2018/4/3.
 */

public class Utils {

    public static void log(String tag, String str) {
        Log.d(tag, str);
    }


    public static boolean isEmpty(String str) {
        boolean fal = false;
        if (str == null || str.equals("")) {
            fal = true;
        } else {
            fal = false;
        }
        return fal;
    }

    /**
     * 获取商品数量
     * @param mContext
     */
    public static void getDataNum(Context mContext) {
        DBManager dbManager = new DBManager(mContext);
        Cursor cursor = dbManager.queryAndShow(MyApplication.MER_ID);
        MyApplication.GOODS_NUM = 0;
        while (cursor.moveToNext()) {
            MyApplication.GOODS_NUM += cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_NUM));
        }
        cursor.close();
    }
}
