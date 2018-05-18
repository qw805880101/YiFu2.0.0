package com.bypay.yifu.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
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
 * 首页功能模块
 * Created by tianchao on 2018/4/4.
 */

public class HomeFunctionAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    private List<GoodsInfo> list;

    private Map<Integer, Boolean> isChose = new HashMap<>();

    public HomeFunctionAdapter(List<GoodsInfo> list) {
        super(R.layout.item_home_funciton, list);
        this.list = list;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final GoodsInfo mGoodsInfo) {

    }

}
