package com.suomate.dabaiserver.base.activity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.suomate.dabaiserver.manager.ActivityManager;
import com.suomate.dabaiserver.manager.DialogManager;

import butterknife.ButterKnife;


/**
 * UI 权限 activity管理 网络监听
 */
public abstract class BaseUIActivity extends AppCompatActivity {
    protected static final int UIMODE_NORMAL = 0;//状态栏着色，占位置(默认)
    protected static final int UIMODE_FULLSCREEN_NOTALL = 1;//全屏,虚拟键透明
    protected static final int UIMODE_FULLSCREEN = 2;//全屏(状态栏透明，虚拟键透明)
    protected static final int UIMODE_TRANSPARENT = 3;//状态栏透明，且不占位置 全透明
    protected static final int UIMODE_TRANSPARENT_NOTALL = 4;//状态栏透明，且不占位置 半透明
    protected static final int UIMODE_TRANSPARENT_FULLSCREEN = 5;//全屏，状态栏半透明，虚拟键半透明，状态栏有文字
    protected int uiMode;
    protected PermissionCallback requestPermissionAndBack;
    protected String TAG = getClass().getSimpleName();
    protected DialogManager dm;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//屏幕方向固定为竖屏
        uiMode = setUIMode();
        setUITheme();
        setContentView(bindLayout());
        ButterKnife.bind(this);
        dm = new DialogManager(this);
        ActivityManager.getInstance().addActivity(this);
        initViews(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }

    /**
     * 设置主题样式（主要针对是否全屏，状态栏是否透明）
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUITheme() {
        Window window = getWindow();
        if (uiMode == UIMODE_FULLSCREEN) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        } else if (uiMode == UIMODE_FULLSCREEN_NOTALL) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (uiMode == UIMODE_TRANSPARENT || uiMode == UIMODE_TRANSPARENT_NOTALL || uiMode == UIMODE_TRANSPARENT_FULLSCREEN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (uiMode == UIMODE_TRANSPARENT_FULLSCREEN) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (uiMode == UIMODE_TRANSPARENT_NOTALL) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//全透明或半透明的关键(此处为半透明)
                } else if (uiMode == UIMODE_TRANSPARENT_FULLSCREEN) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//全透明或半透明的关键(此处为半透明)
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    /**
     * 设置状态栏的颜色
     *
     * @param colorResId 要设置的颜色
     */
    protected void setWindowStatusBarColor(int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(colorResId));
        }
    }

    /**
     * 简化findviewbyid
     *
     * @param resId 控件id
     * @param <T>   view
     * @return view
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T fv(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 吐司
     *
     * @param content 内容
     */
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
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
    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract int setUIMode();

    public interface PermissionCallback {
        void requestPermissionAndBack(boolean isOk);
    }

    /**
     * 申请权限
     */
    protected void checkAndRequestAllPermission(String[] permissions, PermissionCallback requestPermissionAndBack) {
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
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
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
