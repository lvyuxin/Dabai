package com.suomate.dabaiserver.base.activity;

import android.os.Bundle;

import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.manager.DialogManager;
import com.suomate.dabaiserver.utils.config.AppConfig;

/**
 * 默认的网络实现
 */
public class BaseActivity extends BaseNetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
    }
    @Override
    protected void mHandleReLogin(final int what, Result result) {
        AppConfig.getInstance().putBoolean("first_login",true);
        dm.showAlertDialog2(result.getMessage(), null, null, new DialogManager.Callback() {
            @Override
            public void handleLeft() {
//                startActivityForResult(LoginActivity.class, null, what);
            }
        });
    }

    @Override
    protected void mHandleFailed(int what) {

    }


    @Override
    protected void mHandleNoNetwork(int what) {

    }

}
