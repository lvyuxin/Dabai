package com.suomate.dabaiserver.widget.dialog;

/**
 * Created by fanxi on 2018/7/8.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

public class BaseDialog extends Dialog {

    //    public BaseDialog(@NonNull Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        settings();
//    }
//
//    private void settings() {
//        //显示在底部
//        getWindow().setGravity(Gravity.BOTTOM);
//
//        WindowManager m = getWindow().getWindowManager();
//        Display d = m.getDefaultDisplay();
//        WindowManager.LayoutParams p = getWindow().getAttributes();
//        //设置dialog的宽度为当前手机屏幕的宽度，默认宽度不是全屏
//        p.width = d.getWidth();
//        getWindow().setAttributes(p);
//    }
    protected Context mContext;
    private boolean isShowBottom;

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);

        this.mContext = context;
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId, boolean isShowBottom) {
        super(context, themeResId);

        this.mContext = context;
        this.isShowBottom = isShowBottom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isShowBottom) {
            getWindow().setGravity(Gravity.BOTTOM); //显示在底部
        }

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
    }
    }
