package com.suomate.dabaiserver.base.fragment;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.suomate.dabaiserver.base.activity.BaseUIActivity;
import com.suomate.dabaiserver.manager.DialogManager;

import butterknife.ButterKnife;


/**
 * fragment ui基类
 */
public abstract class BaseUIFragment extends Fragment {
    protected BaseUIActivity.PermissionCallback requestPermissionAndBack;
    protected DialogManager dm;
    protected View view;
    protected String TAG= getClass().getSimpleName();

    public BaseUIFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this,view.getRootView());
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        dm = new DialogManager(getActivity());
        this.view = view;
        initViews();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 绑定视图id    (setContentView());
     *
     * @return 视图id
     */
    protected abstract int bindLayout();

    /**
     * 控件的初始化
     */
    protected abstract void initViews();

    /**
     * 简化findviewbyid
     *
     * @param resId 控件id
     * @param <T>   view
     * @return view
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T fv(int resId) {
        return (T) view.findViewById(resId);
    }

    /**
     * 吐司
     *
     * @param content 内容
     */
    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public interface PermissionCallback {
        void requestPermissionAndBack(boolean isOk);
    }

    /**
     * 申请权限
     */
    protected void checkAndRequestAllPermission(String[] permissions, BaseUIActivity.PermissionCallback requestPermissionAndBack) {
        this.requestPermissionAndBack = requestPermissionAndBack;
        if (permissions == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkAllPermissions(permissions)) {//没有所有的权限
                requestPermissions(permissions, 1);
            } else {
                if (requestPermissionAndBack != null) {
                    requestPermissionAndBack.requestPermissionAndBack(true);//有权限
                }
            }
        } else {
            if (requestPermissionAndBack != null) {
                requestPermissionAndBack.requestPermissionAndBack(true);//有权限
            }
        }
    }

    private boolean checkAllPermissions(@NonNull String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] per,
                                           @NonNull int[] grantResults) {
        boolean isAll = true;
        for (int i = 0; i < per.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                isAll = false;
                break;
            }
        }
        if (!isAll) {
            showToast("部分功能可能无法使用，因为你拒绝了一些权限");
        }
        if (requestPermissionAndBack != null) {
            requestPermissionAndBack.requestPermissionAndBack(isAll);//isAll 用户是否拥有所有权限
        }
        super.onRequestPermissionsResult(requestCode, per, grantResults);
    }
}
