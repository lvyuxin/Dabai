package com.suomate.dabaiserver.widget.dialog.base;

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

public abstract class BaseDialog extends Dialog {

    protected Context mContext;
    private boolean isShowBottom;

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId, boolean isShowBottom) {
        super(context, themeResId);
        setContentView(bindLayout());
        this.mContext = context;
        this.isShowBottom = isShowBottom;
//
        initViews();
        bindEvent();
    }
//

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

    protected abstract void bindEvent();

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
