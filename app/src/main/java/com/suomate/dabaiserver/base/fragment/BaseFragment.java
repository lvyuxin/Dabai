package com.suomate.dabaiserver.base.fragment;


import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.config.AppConfig;

/**
 * Created by 李平 on 2017/8/14.
 * 默认的网络实现A
 */

public class BaseFragment extends BaseNetFragment {
    @Override
    protected <T> void mHandle200(int what, Result<T> result) {

    }
    @Override
    protected void mHandle404(int what, Result result) {
        showToast(result.getMessage());
    }

    @Override
    protected void mHandleReLogin(final int what, Result result) {
        AppConfig.getInstance().putBoolean("first_login",true);
//        dm.showAlertDialog2(result.getMessage(), null, null, new DialogManager.Callback() {
//            @Override
//            public void handleLeft() {
//                startActivityForResult(LoginActivity.class, null, what);
//            }
//        });
    }

    @Override
    protected void mHandleFailed(int what) {

    }

    @Override
    protected void mHandleNoNetwork(int what) {

    }

}
