package com.bypay.yifu.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bypay.yifu.MyApplication;
import com.bypay.yifu.R;
import com.bypay.yifu.Utils.DesUtil;
import com.bypay.yifu.Utils.Md5Util;
import com.bypay.yifu.Utils.SignUtil;
import com.bypay.yifu.Utils.StringUtil;
import com.bypay.yifu.Utils.Utils;
import com.bypay.yifu.base.BaseActivity;
import com.bypay.yifu.bean.UserInfoDto;
import com.bypay.yifu.db.DBManager;
import com.bypay.yifu.db.MyDatabaseHelper;
import com.psylife.wrmvplibrary.utils.SpUtils;
import com.psylife.wrmvplibrary.utils.StringUtils;
import com.psylife.wrmvplibrary.utils.ToastUtils;
import com.psylife.wrmvplibrary.utils.helper.RxUtil;
import com.psylife.wrmvplibrary.widget.SwitchView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Action1;

import static com.bypay.yifu.MyApplication.SESSION_ID;

public class UserLongin_Activity extends BaseActivity implements OnClickListener {

    @BindView(R.id.account_number)
    EditText mAccountNumber;
    @BindView(R.id.account_pwd)
    EditText mAccountPwd;
    @BindView(R.id.forget_pwd)
    TextView mForgetPwd;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.remember_pwd)
    SwitchView mSwitchView;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.textView2)
    RelativeLayout mTextView2;

    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";

    private String userName;
    private String password;

    @Override
    public View getTitleView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_userlogin;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mForgetPwd.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        userName = SpUtils.getString(this, USER_NAME);
        password = SpUtils.getString(this, PASSWORD);

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            mAccountNumber.setText(userName);
            mAccountPwd.setText(password);
            mSwitchView.setState(true);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == mLogin) {
            try {
                userName = mAccountNumber.getText().toString();
                password = mAccountPwd.getText().toString();
                if (userName.length() < 11) {
                    ToastUtils.showToast(this, "请输入正确的手机号码");
                    return;
                } else if (userName.length() < 6) {
                    ToastUtils.showToast(this, "密码格式错误");
                    return;
                } else {
                    if (mSwitchView.getState() != SwitchView.STATE_SWITCH_OFF) {
                        SpUtils.putString(this, USER_NAME, userName);
                        SpUtils.putString(this, PASSWORD, password);
                    } else {
                        SpUtils.remove(this, USER_NAME);
                        SpUtils.remove(this, PASSWORD);
                    }
                }
                startProgressDialog(this);
                byte[] md5 = Md5Util.MD5(password);
                String accountPwd = StringUtil.byteArrayToHexString(md5);
                Map map = new HashMap();
                map.put("userName", userName);
                map.put("userPassword", accountPwd);
                map.put("puthId", JPushInterface.getRegistrationID(this));
                map.put("terminalId", "123");
                map.put("terminalInfo", "02"); //02 android
                String sign = SignUtil.coverMap2String(SignUtil.filterBlank(map)) + "|EF312B6C52A7B8E4716D24E518716626";
//                String sign = SignUtil.sign(map, MyApplication.
//                        PRIVATE_KEY);
                map.put("sign", StringUtil.byteArrayToHexString(Md5Util.MD5(sign)));

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(map));

                Observable<UserInfoDto> register = mApi.userLogin(requestBody).compose(RxUtil.<UserInfoDto>rxSchedulerHelper());
                mRxManager.add(register.subscribe(new Action1<UserInfoDto>() {
                    @Override
                    public void call(UserInfoDto userInfoDto) {
                        stopProgressDialog();
                        if (userInfoDto.getRspCode().equals("0000")) {
                            try {
                                SESSION_ID = userInfoDto.getjSessionId();
                                MyApplication.MER_ID = DesUtil.decode(userInfoDto.getMerchantId(), userInfoDto.getjSessionId());
                                Intent intent = new Intent(UserLongin_Activity.this, HomeActivity.class);
                                Utils.getDataNum(UserLongin_Activity.this);
                                UserLongin_Activity.this.startActivity(intent);
                                UserLongin_Activity.this.finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            toastMessage(userInfoDto.getRspCode(), userInfoDto.getRspDesc());
                        }
                    }
                }, this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (v == mRegister) {
            Intent intent = new Intent(this, WebActivity.class);
            String s = MyApplication.URL + "/regist.jsp?userId=" + MyApplication.USER_ID + "&userLevel=" + MyApplication.LEVEL;
            intent.putExtra("url", s);
            this.startActivity(intent);
        }

        if (v == mForgetPwd){
            Intent intent = new Intent(this, WebActivity.class);
            String s = MyApplication.URL + "/forgetPassword.jsp";
            intent.putExtra("url", s);
            this.startActivity(intent);
        }
    }

    /**
     * 读取数据库数据
     */
//    private void getDataNum() {
//        DBManager dbManager = new DBManager(this);
//        String sql = "SELECT COUNT(*) FROM SHOP_CART WHERE MER_ID = '" + MyApplication.MER_ID + "'";
//        Cursor cursor = dbManager.rawSql(sql);
//        cursor.moveToFirst();
//        long count = cursor.getLong(0);
//        cursor.close();
//        MyApplication.GOODS_NUM = (int) count;
//    }

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
