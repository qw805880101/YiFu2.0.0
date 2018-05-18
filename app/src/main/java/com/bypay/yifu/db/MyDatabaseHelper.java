package com.bypay.yifu.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.bypay.yifu.Utils.Utils;

/**
 * Created by tianchao on 2018/3/30.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //卡号、账单金额、账单日、还款日、

    public static String COMMODITY_MER_ID = "MER_ID";
    public static String COMMODITY_SKU_ID = "SKU_ID";
    public static String COMMODITY_IMG = "COMMODITY_IMG";
    public static String COMMODITY_NAME = "COMMODITY_NAME";
    public static String COMMODITY_PRICE = "COMMODITY_PRICE";
    public static String COMMODITY_NUM = "COMMODITY_NUM";
    public static String IS_SELECTED = "IS_SELECTED";

    public static final String CREATE_BOOK = "CREATE TABLE SHOP_CART ("

            + "ID integer primary key autoincrement, "

            + "MER_ID text, "

            + "SKU_ID text, "

            + "COMMODITY_IMG text, "

            + "COMMODITY_NAME text, "

            + "COMMODITY_PRICE number, "

            + "COMMODITY_NUM number, "

            + "IS_SELECTED text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) throws SQLException {
        db.execSQL(CREATE_BOOK);
        Utils.log("DB", "创建数据库成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}