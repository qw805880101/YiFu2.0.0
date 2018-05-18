package com.bypay.yifu.adapter;

import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.bumptech.glide.Glide;
import com.bypay.yifu.R;
import com.bypay.yifu.bean.GoodsInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.psylife.wrmvplibrary.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianchao on 2018/4/4.
 */

public class ShopAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    public static final int CHOSE_ALL = 10001; //全选

    public static final int CLEAR_ALL = 10002; //全不选

    private List<GoodsInfo> list;

    private ShopCartInterface mShopCartInterface;

    private Map<Integer, Boolean> isChose = new HashMap<>();

    private int choseState = -1;

    public ShopAdapter(List<GoodsInfo> list) {
        super(R.layout.item_shop, list);
        this.list = list;
    }

    public void setShopCartInterface(ShopCartInterface mShopCartInterface) {
        this.mShopCartInterface = mShopCartInterface;
    }

    public void setList(List<GoodsInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final GoodsInfo mGoodsInfo) {
        baseViewHolder.setText(R.id.txt_goods_name, mGoodsInfo.getGoodsName())
                .setText(R.id.txt_goods_price, "￥" + mGoodsInfo.getPrice())
                .setText(R.id.txt_goods_number, "" + mGoodsInfo.getGoodsNumber());
        Glide.with(mContext).load(mGoodsInfo.getImagePath())
                .placeholder(R.mipmap.error) //占位符 也就是加载中的图片，可放个gif
                .error(R.mipmap.error) //失败图片
                .into((ImageView) baseViewHolder.getView(R.id.img_goods));

        final CheckBox checkBox = baseViewHolder.getView(R.id.cb);

        final EditText editText = baseViewHolder.getView(R.id.txt_goods_number);

        if (choseState <= 0) {
            if (mGoodsInfo.getIsChecked().equals("true")) {
                checkBox.setChecked(true);
                isChose.put(baseViewHolder.getLayoutPosition(), true);
//                if (mShopCartInterface != null) {
//                    mShopCartInterface.isChoseLister(baseViewHolder.getLayoutPosition(), true, Integer.parseInt(editText.getText().toString()), true);
//                }
            } else {
                checkBox.setChecked(false);
                isChose.put(baseViewHolder.getLayoutPosition(), false);
            }
        }

        baseViewHolder.getView(R.id.right).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(mContext, "点击删除");
                if (mShopCartInterface != null)
                    mShopCartInterface.deleteGoods(v, baseViewHolder.getLayoutPosition());
            }
        });

        baseViewHolder.getView(R.id.bt_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsNum(editText, 1, baseViewHolder.getLayoutPosition(), checkBox.isChecked());
            }
        });

        baseViewHolder.getView(R.id.bt_minus).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("1")) {
                    return;
                }
                goodsNum(editText, -1, baseViewHolder.getLayoutPosition(), checkBox.isChecked());
            }
        });

        if (choseState == CHOSE_ALL) {
            isChose.put(baseViewHolder.getLayoutPosition(), true);
        } else if (choseState == CLEAR_ALL) {
            isChose.put(baseViewHolder.getLayoutPosition(), false);
        }

        if (null != isChose.get(baseViewHolder.getLayoutPosition()))
            checkBox.setChecked(isChose.get(baseViewHolder.getLayoutPosition()));
        else
            checkBox.setChecked(false);

        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isChose.put(baseViewHolder.getLayoutPosition(), checkBox.isChecked());
                if (mShopCartInterface != null) {
                    mShopCartInterface.isChoseLister(baseViewHolder.getLayoutPosition(), checkBox.isChecked(), Integer.parseInt(editText.getText().toString()), false);
                }
            }
        });

//        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                isChose.put(baseViewHolder.getLayoutPosition(), isChecked);
//
//            }
//        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    if (s.subSequence(0, 1).toString().equals("0")) {
                        s.replace(0, 1, "");
                    }

                    int a = Integer.parseInt(s.toString());
                    if (a == 0) {
                        editText.setText("1");
                    }

                    if (a > 99) {
                        ToastUtils.showToast(mContext, "单件商品最大只能选择99件");
                        editText.setText("99");
                    }
                    a = Integer.parseInt(editText.getText().toString());
                    mShopCartInterface.changerPosition(baseViewHolder.getLayoutPosition(), a);
                } else {
                    editText.setText("1");
                }
            }
        });

        editText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (editText.getText().toString().equals("0") || editText.getText().toString().equals("")) {
                        editText.setText("1");
                    }
//                    mShopCartInterface.isChoseLister(baseViewHolder.getLayoutPosition(), false, list.get(baseViewHolder.getLayoutPosition()).getGoodsNumber(), false);
//                    mShopCartInterface.isChoseLister(baseViewHolder.getLayoutPosition(), checkBox.isChecked(), Integer.parseInt(editText.getText().toString()), false);
//                    list.get(baseViewHolder.getLayoutPosition()).setGoodsNumber(Integer.parseInt(editText.getText().toString()));
                }
                return false;
            }
        });
    }

    public boolean choseAll() {
        choseState = CHOSE_ALL;
        notifyDataSetChanged();
        return true;
    }

    public boolean clearAll() {
        choseState = CLEAR_ALL;
        notifyDataSetChanged();
        return false;
    }

    public boolean cancel() {
        choseState = 0;
//        notifyDataSetChanged();
        return false;
    }


    private void goodsNum(EditText editText, int num, int position, boolean isChecked) {
        int a = Integer.parseInt(editText.getText().toString());
        if (a == 0 && num < 0) {
            return;
        }
        editText.setText("" + (a + num));
//        if (isChecked)
        mShopCartInterface.addGoodsNumber(position, num < 0 ? false : true, a + num, isChecked);
    }

    public void delete(List<GoodsInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface ShopCartInterface {
        /**
         * 删除商品
         *
         * @param v
         * @param pos
         */
        void deleteGoods(View v, int pos);

        /**
         * 点击商品详情
         *
         * @param v
         * @param pos
         */
        void clickGoods(View v, int pos);

        /**
         * 是否选中商品
         *
         * @param pos
         * @param isChecked
         * @param goodsNumber
         * @param isInit
         */
        void isChoseLister(int pos, boolean isChecked, int goodsNumber, boolean isInit);

        /**
         * 增加/减少商品数量
         *
         * @param pos
         * @param isAdd       是否增加商品数量
         * @param goodsNumber
         * @param isChecked
         */
        void addGoodsNumber(int pos, boolean isAdd, int goodsNumber, boolean isChecked);

        /**
         * 更改点击输入框位置
         *
         * @param position 条目位置
         * @param goodsNum 商品输入数量
         */
        boolean changerPosition(int position, int goodsNum);

        /**
         * 商品数量改变
         *
         * @param editText
         */
//        void goodsNumChange(EditText editText);
    }
}
