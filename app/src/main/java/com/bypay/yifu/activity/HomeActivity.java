package com.bypay.yifu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.bypay.yifu.MyApplication;
import com.bypay.yifu.R;
import com.bypay.yifu.Utils.Utils;
import com.bypay.yifu.base.BaseActivity;
import com.bypay.yifu.fragment.HomePageFragment;
import com.bypay.yifu.fragment.ShopCartFragment;
import com.bypay.yifu.fragment.WebFragment;
import com.psylife.wrmvplibrary.utils.StatusBarUtil;
import com.psylife.wrmvplibrary.utils.ToastUtils;
import com.xuanxing.tc.bottomtabbar.BottomTabBar;
import com.xuanxing.tc.bottomtabbar.CustomFragmentTabHost;

import butterknife.BindView;

/**
 * 首页
 * <p>
 * Created by tc on 2017/8/24.
 */

public class HomeActivity extends BaseActivity implements OnClickListener, BottomTabBar.OnTabChangeListener {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    private CustomFragmentTabHost mCustomFragmentTabHost;

    private Class fragmentArray[] = {HomePageFragment.class, WebFragment.class, ShopCartFragment.class, WebFragment.class};

    FragmentManager fragmentManager;

    public void setStatusBarColor() {
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.color_a8abb3));
    }

    @Override
    public View getTitleView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        bottomTabBar.init(fragmentManager);
        bottomTabBar.setChangeColor(getResources().getColor(R.color.title_bg_e83646), getResources().getColor(R.color.txt_828282));
        bottomTabBar.setTabPadding(this.getResources().getDimensionPixelSize(R.dimen.middle_5), this.getResources().getDimensionPixelSize(R.dimen.middle_5), this.getResources().getDimensionPixelSize(R.dimen.middle_5));
        bottomTabBar.setOnTabChangeListener(this);
        bottomTabBar.isShowDivider(true);
        bottomTabBar.setTabBarBackgroundResource(R.color.back_ececed);
        bottomTabBar.setDividerColor(this.getResources().getColor(R.color.color_ccd0cf));
        mCustomFragmentTabHost = bottomTabBar.getTapHost();
        String[] mTabs = {"首页", "分类", "购物车", "我的"};
        int[] imageIds = {R.mipmap.menu_icon1_orange, R.mipmap.menu_icon2_orange, R.mipmap.menu_icon3_orange, R.mipmap.menu_icon4_orange};
        int[] imageId = {R.mipmap.menu_icon1_gary, R.mipmap.menu_icon2_gary, R.mipmap.menu_icon3_gary, R.mipmap.menu_icon4_gary};
        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < mTabs.length; i++) {
            bottomTabBar.addTabItem(mTabs[i], imageIds[i], imageId[i], fragmentArray[i]);
        }
        setGoodNum();
//        mCustomFragmentTabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (MyApplication.loginInfo == null) {
//                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                } else {
//                mCustomFragmentTabHost.setCurrentTab(2);
//                }
//            }
//        });
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onTabChange(int position, String name) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 3:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            if (intent.getBooleanExtra("isLoginOut", false)) {
                mCustomFragmentTabHost.setCurrentTab(0);
            }
            if (intent.getIntExtra("id", 0) >= 0) {
                mCustomFragmentTabHost.setCurrentTab(intent.getIntExtra("id", 0));
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setGoodNum();
        if (mCustomFragmentTabHost.getCurrentTab() == 2) {
            Utils.getDataNum(this);
        }
    }

    /**
     * 设置底部购物车显示数量
     */
    public void setGoodNum() {
        bottomTabBar.setNum(MyApplication.GOODS_NUM);
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                ToastUtils.showToast(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return false;
    }
}
