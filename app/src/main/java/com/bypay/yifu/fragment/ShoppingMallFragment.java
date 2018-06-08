package com.bypay.yifu.fragment;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bypay.yifu.R;
import com.bypay.yifu.Utils.GlideImageLoader;
import com.bypay.yifu.adapter.HomeGoodsAdapter_a;
import com.bypay.yifu.adapter.ShoppingMallGoodsListAdapter;
import com.bypay.yifu.adapter.ShoppingMallTabAdapter;
import com.bypay.yifu.base.BaseFragment;
import com.bypay.yifu.bean.HomeGoodsInfo;
import com.bypay.yifu.bean.HomeTabInfo;
import com.bypay.yifu.view.InterestSpaceItemDecorationList;
import com.bypay.yifu.view.SpringScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 商城界面
 * <p>
 * Created by tc on 2017/8/24.
 */

public class ShoppingMallFragment extends BaseFragment {

    @BindView(R.id.shopping_scrollview)
    SpringScrollView mNestedScrollView;
    @BindView(R.id.lin_shopping_title)
    LinearLayout mLinTitle;
    @BindView(R.id.shopping_mall_banner)
    Banner mShoppingMallBanner;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.shopping_mall_tab)
    RecyclerView mShoppingMallTab;
    @BindView(R.id.recycler_mall_goods)
    RecyclerView mRecyclerMallGoods;
    @BindView(R.id.recycler_mall_goods_list)
    RecyclerView mRecyclerMallGoodsList;

    private List<String> images = new ArrayList<>();

    private ShoppingMallTabAdapter mShoppingMallTabAdapter;
    private ShoppingMallGoodsListAdapter mShoppingMallGoodsListAdapter;
    private HomeGoodsAdapter_a mHomeGoodsAdapter_a;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shoppingmall;
    }

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //设置图片加载器
        mShoppingMallBanner.setImageLoader(new GlideImageLoader());
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526883546799&di=5acdd6bef77d8cc7f8db4dcb14dca803&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F21%2F22%2F71g58PICBQT_1024.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526883546798&di=34296c471267e8b590416f2dbd464a9d&imgtype=0&src=http%3A%2F%2Fpic2.16pic.com%2F00%2F07%2F67%2F16pic_767824_b.jpg");
        //设置图片集合
        mShoppingMallBanner.setImages(images);
        //设置指示器位置（当banner模式中有指示器时）
        mShoppingMallBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置轮播时间
        mShoppingMallBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mShoppingMallBanner.start();

        List<HomeTabInfo> list = new ArrayList<>();
        String[] tabNames = {"新品", "产品分类", "购物车", "我的订单"};
        int[] tabImages = {R.mipmap.mall_icon_new_products, R.mipmap.mall_icon_products,
                R.mipmap.mall_icon_shopping_cart, R.mipmap.mall_icon_my_order};
        for (int i = 0; i < 4; i++) {
            list.add(new HomeTabInfo(tabNames[i], tabImages[i]));
        }
        mShoppingMallTabAdapter = new ShoppingMallTabAdapter(list);
        mShoppingMallTab.setLayoutManager(new GridLayoutManager(this.getActivity(), 4));
        mShoppingMallTab.setAdapter(mShoppingMallTabAdapter);

        String[] goodsNames = {"缓解湿疹牛皮癣", "补水保湿", "美白亮肤抗皱"};
        String[] goodsAmts = {"99.00", "319.00", "349.00"};
        String[] goodsOldAmts = {"139.00", "799.00", "560.00"};
        int[] goodsImages = {R.mipmap.home_commodity_iamge1, R.mipmap.home_commodity_iamge2, R.mipmap.home_commodity_iamge3};
        List<HomeGoodsInfo> homeGoodsInfosa = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            homeGoodsInfosa.add(new HomeGoodsInfo(goodsNames[i], goodsAmts[i], goodsOldAmts[i], goodsImages[i]));
        }
        mHomeGoodsAdapter_a = new HomeGoodsAdapter_a(homeGoodsInfosa);
        mRecyclerMallGoods.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerMallGoods.setAdapter(mHomeGoodsAdapter_a);
        mRecyclerMallGoods.setNestedScrollingEnabled(false);

        List<HomeTabInfo> homeGoodsInfos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            homeGoodsInfos.add(new HomeTabInfo(tabNames[i], tabImages[i]));
        }
        mShoppingMallGoodsListAdapter = new ShoppingMallGoodsListAdapter(homeGoodsInfos);
        mRecyclerMallGoodsList.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerMallGoodsList.addItemDecoration(new InterestSpaceItemDecorationList(mContext.getResources().getDimensionPixelSize(R.dimen.bottom_1), 4));
        mRecyclerMallGoodsList.setAdapter(mShoppingMallGoodsListAdapter);
        mRecyclerMallGoodsList.setNestedScrollingEnabled(false);

        mLinTitle.getBackground().setAlpha(0);
        mNestedScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if ((mShoppingMallBanner.getHeight() / 255 * scrollY) >= 255) {
                    mLinTitle.getBackground().setAlpha(255);
                } else {
                    mLinTitle.getBackground().setAlpha(mShoppingMallBanner.getHeight() / 255 * scrollY);
                }
            }
        });
    }

    @Override
    protected void initLazyView() {

    }
}