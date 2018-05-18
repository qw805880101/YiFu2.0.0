package com.bypay.yifu.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.psylife.wrmvplibrary.utils.LogUtil;

/**
 * Created by tianchao on 2018/3/30.
 */

public class DBManager {
    private static DBManager dbManager;
    private Context context;
    SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;
        if (db == null) {
            db = new MyDatabaseHelper(context, "SHOP_CART.db", null, 1).getWritableDatabase();
        }
    }

    public static DBManager getInstance(Context context) {
        if (null == dbManager) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    /**
     * @param
     * @description 通过执行sql语句来操作数据库
     */
    public void execSql(String sql) {
        if (null != db && !TextUtils.isEmpty(sql)) {
            db.execSQL(sql);
        }
    }

    public Cursor rawSql(String sql) {
        if (null != db && !TextUtils.isEmpty(sql)) {
            return db.rawQuery(sql, null);
        }
        return null;
    }

    public String delSql(String merId, String skuId) {
        String delSql = "DELETE FROM SHOP_CART WHERE MER_ID = '" + merId + "' AND SKU_ID = '" + skuId + "'";
        LogUtil.d("delSql:" + delSql);
        return delSql;
    }

    public String insertSql(String merId, String skuId, String commodityImg, String commodityName, double commodityPrice, int commodityNum, String isSelected) {
        String insertSql = "INSERT INTO SHOP_CART (MER_ID, SKU_ID, COMMODITY_IMG, COMMODITY_NAME, COMMODITY_PRICE, COMMODITY_NUM, IS_SELECTED) " +
                "values('" + merId + "','" + skuId + "','" + commodityImg + "','" + commodityName + "'," + commodityPrice + "," + commodityNum + ",'" + isSelected + "')";
        LogUtil.d("insertSql:" + insertSql);
        return insertSql;
    }

    public String updateSql(String merId, String skuId, int commodityNum) {
        String updateSql = "UPDATE SHOP_CART SET COMMODITY_NUM = " + commodityNum + " WHERE MER_ID = '" + merId + "' AND SKU_ID = '" + skuId + "'";
        LogUtil.d("updateSql:" + updateSql);
        return updateSql;
    }

    public String updateSql(String merId, String skuId, int commodityNum, String isChecked) {
        String updateSql = "UPDATE SHOP_CART SET COMMODITY_NUM = " + commodityNum + ", IS_SELECTED = '" + isChecked + "' WHERE MER_ID = '" + merId + "' AND SKU_ID = '" + skuId + "'";
        LogUtil.d("updateSql:" + updateSql);
        return updateSql;
    }

    /**
     * 查询商品是否存在
     *
     * @param merId
     * @return
     */
    public Cursor queryBySkuId(String merId, String skuID) {
        String sql = "SELECT * FROM SHOP_CART WHERE MER_ID = '" + merId + "' AND SKU_ID = '" + skuID + "'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    /**
     * @param
     * @description 查询数据并显示数据内容
     */
    public Cursor queryAndShow(String merId) {
        String sql = "SELECT * FROM SHOP_CART WHERE MER_ID = '" + merId + "'";
        StringBuffer sb = new StringBuffer();
//        while (cursor.moveToNext()) {
//            sb.append("cardNum=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.CARD_NUM))).append(" ")
//                    .append("billPrice=").append(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.BILL_PRICE))).append(" ")
//                    .append("billDate=").append(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.BILL_DATE))).append(" ")
//                    .append("repaymentDate=").append(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.REPAYMENT_DATE)))
//                    .append("\n");
//        }
//        System.out.println(sb.toString());
        return db.rawQuery(sql, null);
    }


}