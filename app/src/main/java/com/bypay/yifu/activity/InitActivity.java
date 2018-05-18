package com.bypay.yifu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bypay.yifu.BuildConfig;
import com.bypay.yifu.MyApplication;
import com.bypay.yifu.R;
import com.bypay.yifu.Utils.DesUtil;
import com.bypay.yifu.Utils.Md5Util;
import com.bypay.yifu.Utils.SignUtil;
import com.bypay.yifu.Utils.StringUtil;
import com.bypay.yifu.adapter.ViewPagerAdapter;
import com.bypay.yifu.base.BaseActivity;
import com.bypay.yifu.bean.GoodsInfo;
import com.bypay.yifu.bean.InitInfo;
import com.bypay.yifu.bean.UserInfoDto;
import com.bypay.yifu.db.DBManager;
import com.bypay.yifu.db.MyDatabaseHelper;
import com.psylife.wrmvplibrary.utils.SpUtils;
import com.psylife.wrmvplibrary.utils.ToastUtils;
import com.psylife.wrmvplibrary.utils.helper.RxUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Action1;
import util.UpdateAppUtils;

import static android.os.Build.VERSION_CODES.M;
import static com.bypay.yifu.MyApplication.SESSION_ID;

/**
 * Created by tianchao on 2018/4/3.
 */

public class InitActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager mViewPager;

    public static final int EXTERNAL_STORAGE_REQ_CAMERA_CODE = 10;

    private ViewPagerAdapter mViewPagerAdapter;

    private int[] images = {};

    private static final String LOGIN_STATUS = "loginStatus";

    Handler mHandler = new Handler();

    @Override
    public View getTitleView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_init;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    Intent intent = new Intent(InitActivity.this, UserLongin_Activity.class);
//                    InitActivity.this.startActivity(intent);
//                    InitActivity.this.finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        requestCameraPerm();

        mViewPagerAdapter = new ViewPagerAdapter(this, images);

        mViewPager.setAdapter(mViewPagerAdapter);//对viewpager设置数据适配器

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当是最后一个页面的时候将按钮显示出来
                if (position == images.length - 1) {
                } else {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initdata() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            return false;
        }
        return false;
    }

    private void requestCameraPerm() {
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_REQ_CAMERA_CODE);
            } else {
                start();
            }
        } else {
            start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_STORAGE_REQ_CAMERA_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                start();
            } else {
                ToastUtils.showToast(this, "未获取权限");
                start();
            }
        }
    }

    private void start() {
        startProgressDialog(this);
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this, "SHOP_CART.db", null, 1);
        myDatabaseHelper.getWritableDatabase();

        Map map = new HashMap();
        map.put("version", BuildConfig.VERSION_NAME);
        map.put("terminalInfo", "02"); //02 android
        String sign = SignUtil.coverMap2String(SignUtil.filterBlank(map)) + "|EF312B6C52A7B8E4716D24E518716626";
//                String sign = SignUtil.sign(map, MyApplication.
//                        PRIVATE_KEY);
        map.put("sign", StringUtil.byteArrayToHexString(Md5Util.MD5(sign)));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(map));
        Observable<InitInfo> register = mApi.init(requestBody).compose(RxUtil.<InitInfo>rxSchedulerHelper());
        mRxManager.add(register.subscribe(new Action1<InitInfo>() {
            @Override
            public void call(InitInfo initInfo) {
                stopProgressDialog();
                if (initInfo.getRspCode().equals("0000")) {
                    try {
                        stopProgressDialog();
                        Intent intent = new Intent(InitActivity.this, UserLongin_Activity.class);
                        InitActivity.this.startActivity(intent);
                        InitActivity.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (initInfo.getRspCode().equals("0001")) {
                    UpdateAppUtils.from(InitActivity.this)
                            .serverVersionName(initInfo.getVersion()) //服务器versionName
                            .serverVersionCode(BuildConfig.VERSION_CODE + 1)
                            .apkPath(initInfo.getUpdatePath()) //最新apk下载地址
                            .updateInfo(initInfo.getUpdateInfo())  //更新日志信息 String
//                                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER) //下载方式：app下载、手机浏览器下载。默认app下载
                            .isForce(true) //是否强制更新，默认false 强制更新情况下用户不同意更新则不能使用app
                            .update();
                } else {
                    toastMessage(initInfo.getRspCode(), initInfo.getRspDesc());
                }
            }
        }, InitActivity.this));

        boolean loginStatus = SpUtils.getBoolean(this, LOGIN_STATUS, false);
        if (loginStatus) {
            mViewPager.setVisibility(View.VISIBLE);
        } else {
            mViewPager.setVisibility(View.GONE);
        }
    }
}
