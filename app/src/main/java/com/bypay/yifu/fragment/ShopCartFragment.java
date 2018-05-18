package com.bypay.yifu.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bypay.yifu.MyApplication;
import com.bypay.yifu.R;
import com.bypay.yifu.Utils.Base64Class;
import com.bypay.yifu.Utils.KeyboardChangeListener;
import com.bypay.yifu.Utils.KeyboardChangeListener.KeyBoardListener;
import com.bypay.yifu.Utils.Utils;
import com.bypay.yifu.activity.HomeActivity;
import com.bypay.yifu.activity.UserLongin_Activity;
import com.bypay.yifu.activity.WebActivity;
import com.bypay.yifu.adapter.ShopAdapter;
import com.bypay.yifu.adapter.ShopAdapter.ShopCartInterface;
import com.bypay.yifu.base.BaseFragment;
import com.bypay.yifu.bean.GoodsInfo;
import com.bypay.yifu.db.DBManager;
import com.bypay.yifu.db.MyDatabaseHelper;
import com.psylife.wrmvplibrary.base.WRBaseFragment;
import com.psylife.wrmvplibrary.utils.LogUtil;
import com.psylife.wrmvplibrary.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import butterknife.BindView;

/**
 * 购物车
 * Created by psylife00 on 2017/5/12.
 */

public class ShopCartFragment extends BaseFragment implements OnClickListener, ShopCartInterface, KeyBoardListener {

    @BindView(R.id.cb_chose_all)
    CheckBox mCheckBox;
    @BindView(R.id.txt_amt)
    TextView mTxtAmt;
    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.recycler_shop)
    RecyclerView mRecyclerShop;
    @BindView(R.id.swipeLayout_shop)
    SwipeRefreshLayout mSwipeLayoutShop;
    @BindView(R.id.lin_none)
    LinearLayout mLayoutNone;

    private ShopAdapter mShopAdapter;

    private List<GoodsInfo> list;

    private double allAmt;

    private Cursor cursor;

    DBManager dbManager;

    private Map<Integer, Boolean> isChose = new HashMap<>();

    private KeyboardChangeListener mKeyboardChangeListener;

    private int editTextPosition;

    private int editTextNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        dbManager = new DBManager(mContext);

        getData();

        mShopAdapter = new ShopAdapter(list);
        mShopAdapter.setShopCartInterface(this);
        mRecyclerShop.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerShop.setAdapter(mShopAdapter);

        mSwipeLayoutShop.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayoutShop.setRefreshing(false);
                getData();
                mShopAdapter.cancel();
                mShopAdapter.setNewData(list);
                setLayoutNone();
            }
        });

        mBtNext.setOnClickListener(this);

        mCheckBox.setOnClickListener(this);

        setLayoutNone();

        mKeyboardChangeListener = new KeyboardChangeListener(this.getActivity());

        mKeyboardChangeListener.setKeyBoardListener(this);
    }

    @Override
    protected void initLazyView() {
        LogUtil.d("init~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        mShopAdapter.cancel();
        mShopAdapter.setNewData(list);
        setLayoutNone();
    }

    @Override
    public void onClick(View v) {
        if (v == mBtNext) {
            startProgressDialog(this.getContext());
            List<Map> maps = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                GoodsInfo goodsInfo = list.get(i);
                if (goodsInfo.getIsChecked().equals("true")) {
                    Map map = new HashMap();
                    map.put("skuId", goodsInfo.getSkuId());
                    map.put("num", goodsInfo.getGoodsNumber());
                    maps.add(map);
                }
            }
            if (maps.size() <= 0) {
                stopProgressDialog();
                ToastUtils.showToast(this.getContext(), "暂无选中商品");
                return;
            }
            final String str = JSON.toJSONString(maps);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogUtil.d(MyApplication.URL
                                + "/jdData/getJDOrder?sku=" + new String(Base64Class.encode(str.getBytes(), 0), "utf-8") + "&jSessionId=" + MyApplication.SESSION_ID);
                        Thread.sleep(2000);
                        Intent intent = new Intent(ShopCartFragment.this.getContext(), WebActivity.class);
                        intent.putExtra("url", MyApplication.URL
                                + "/jdData/getJDOrder?sku=" + new String(Base64Class.encode(str.getBytes(), 0), "utf-8") + "&jSessionId=" + MyApplication.SESSION_ID);
                        stopProgressDialog();
                        ShopCartFragment.this.getContext().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        if (v == mCheckBox) {
            if (list.size() <= 0) {
                mCheckBox.setChecked(false);
                ToastUtils.showToast(this.getContext(), "暂无商品");
                return;
            }
            if (!mCheckBox.isChecked()) {
                mShopAdapter.clearAll();
                mCheckBox.setChecked(false);
                for (int i = 0; i < list.size(); i++) {
                    goodsSelect(i, false, list.get(i).getGoodsNumber());
                    updateDB(i, list.get(i).getGoodsNumber(), false);
                }
            } else {
                allAmt = 0;
                allNum = 0;
                mShopAdapter.choseAll();
                mCheckBox.setChecked(true);
                for (int i = 0; i < list.size(); i++) {
                    goodsSelect(i, true, list.get(i).getGoodsNumber());
                    updateDB(i, list.get(i).getGoodsNumber(), true);
                }
            }
        }
    }

    @Override
    public void deleteGoods(View v, int pos) {
        delDB(pos);
        setGoodsNum(-list.get(pos).getGoodsNumber());
        if (list.get(pos).getIsChecked().equals("true"))
            goodsSelect(pos, false, list.get(pos).getGoodsNumber());
        list.remove(pos);
        mShopAdapter.setNewData(list);
        setLayoutNone();
        if (allNum == list.size() && list.size() > 0) {
            mCheckBox.setChecked(true);
        }
    }

    @Override
    public void clickGoods(View v, int pos) {

    }

    @Override
    public void isChoseLister(int pos, boolean isChecked, int goodsNumber, boolean isInit) {
        updateDB(pos, goodsNumber, isChecked);
        goodsSelect(pos, isChecked, goodsNumber);
    }

    @Override
    public void addGoodsNumber(int pos, boolean isAdd, int goodsNumber, boolean isChecked) {
        updateDB(pos, goodsNumber, isChecked);
        goodsNumber(pos, isAdd, isChecked);
    }

    @Override
    public boolean changerPosition(int position, int goodsNum) {
        editTextPosition = position;
        editTextNum = goodsNum;
        return true;
    }

    /**
     * 商品总金额 数量增加/减少
     */
    private void goodsNumber(int pos, boolean isAdd, boolean isChecked) {
        if (isChecked) {
            double price = Double.parseDouble(list.get(pos).getPrice());
            if (isAdd) {
                allAmt += price;
            } else {
                allAmt -= price;
            }
            mTxtAmt.setText("合计：" + allAmt);
        }
        if (isAdd) {
            setGoodsNum(1);
        } else {
            setGoodsNum(-1);
        }
    }

    int allNum = 0;

    /**
     * 商品总金额 选中/未选中
     */
    private void goodsSelect(int pos, boolean isChecked, int goodsNumber) {
        double price = Double.parseDouble(list.get(pos).getPrice());
        if (isChecked) {
            allNum++;
            allAmt += price * goodsNumber;
            list.get(pos).setIsChecked("true");
        } else {
            allNum--;
            mCheckBox.setChecked(false);
            allAmt -= price * goodsNumber;
            list.get(pos).setIsChecked("false");
        }
        isChose.put(pos, isChecked);
        mTxtAmt.setText("合计：" + allAmt);
        Utils.getDataNum(this.getContext());
        ((HomeActivity) this.getActivity()).setGoodNum();
        if (allNum == list.size()) {
            mCheckBox.setChecked(true);
        }
    }

    /**
     * 设置购物车底部显示数量
     *
     * @param a
     */
    private void setGoodsNum(int a) {
        MyApplication.GOODS_NUM += a;
        ((HomeActivity) this.getActivity()).setGoodNum();
    }

    /**
     * 循环判断是否有商品未选中
     */
    private void checkedAll() {
        if (list.size() <= 0) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsChecked().equals("true")) {
                goodsSelect(i, true, list.get(i).getGoodsNumber());
            }
        }

        for (Entry s : isChose.entrySet()) {
            if (!(Boolean) s.getValue()) {
                mCheckBox.setChecked(false);
                return;
            }
        }
//        allNum = list.size();
        mCheckBox.setChecked(true);
    }

    /**
     * 显示无商品界面
     */
    private void setLayoutNone() {
        if (list.size() <= 0) {
            mLayoutNone.setVisibility(View.VISIBLE);
        } else {
            mLayoutNone.setVisibility(View.GONE);
        }
    }

    /**
     * 更新商品数据库参数
     *
     * @param pos
     * @param goodsNumber 商品数量
     * @param isChecked   是否选中
     */
    private void updateDB(int pos, int goodsNumber, boolean isChecked) {
        list.get(pos).setGoodsNumber(goodsNumber);
        String sql = dbManager.updateSql(MyApplication.MER_ID, list.get(pos).getSkuId(), goodsNumber, isChecked ? "true" : "false");
        dbManager.execSql(sql);
    }

    /**
     * 删除数据库商品
     *
     * @param pos
     */
    private void delDB(int pos) {
        dbManager.execSql(dbManager.delSql(MyApplication.MER_ID, list.get(pos).getSkuId()));
    }


    /**
     * 读取数据库数据
     */
    private void getData() {
        list.clear();
        isChose.clear();
        allNum = 0;
        allAmt = 0;
        mCheckBox.setChecked(false);
        mTxtAmt.setText("0.00");
        ((HomeActivity) this.getActivity()).setGoodNum();
        cursor = dbManager.queryAndShow(MyApplication.MER_ID);
        while (cursor.moveToNext()) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGoodsName(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_NAME)));
            goodsInfo.setImagePath(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_IMG)));
            goodsInfo.setPrice("" + cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_PRICE)));
            goodsInfo.setIsChecked(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.IS_SELECTED)));
            goodsInfo.setGoodsNumber(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_NUM)));
            goodsInfo.setSkuId(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COMMODITY_SKU_ID)));
            isChose.put(list.size(), goodsInfo.getIsChecked().equals("true") ? true : false);
            list.add(goodsInfo);
        }
        cursor.close();
        checkedAll();
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        if (!isShow) {
            int oldNum = list.get(editTextPosition).getGoodsNumber();
            updateDB(editTextPosition, editTextNum, true);
            goodsSelect(editTextPosition, false, oldNum);
            goodsSelect(editTextPosition, true, editTextNum);
            list.get(editTextPosition).setGoodsNumber(editTextNum);
        }
    }
}
