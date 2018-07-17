package com.suomate.dabaiserver.widget.dialog.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.Result;

/**
 * Created by fanxi on 2018/7/13.
 */

public   class BaseDialog1 extends CustomDialog{


    private boolean isShowBottom;
    protected TextView tvTitle, tvSure, tvCancle;
    protected Object object;

    public BaseDialog1(@NonNull Context context, @StyleRes int themeResId, boolean isShowBottom) {
        super(context, themeResId);
        setContentView(bindLayout());
//        this.mContext = context;
        this.isShowBottom = isShowBottom;
        tvSure=findViewById(R.id.ldialog_sure);
        tvCancle=findViewById(R.id.dialog_cancel);
        tvTitle=findViewById(R.id.dialog_title);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        initViews();
        bindEvent();
    }
    public BaseDialog1(@NonNull Context context, @StyleRes int themeResId, boolean isShowBottom,String title) {
        super(context, themeResId);
        setContentView(bindLayout());
//        this.mContext = context;
        this.isShowBottom = isShowBottom;

        tvSure=findViewById(R.id.ldialog_sure);
        tvCancle=findViewById(R.id.dialog_cancel);
        tvTitle=findViewById(R.id.dialog_title);
        tvTitle.setText(title);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        initViews();
        bindEvent();
    }
    public BaseDialog1(@NonNull Context context, @StyleRes int themeResId, boolean isShowBottom,Object object) {
        super(context, themeResId);
        setContentView(bindLayout());
//        this.mContext = context;
        this.isShowBottom = isShowBottom;
        this.object=object;
        tvSure=findViewById(R.id.ldialog_sure);
        tvCancle=findViewById(R.id.dialog_cancel);
        tvTitle=findViewById(R.id.dialog_title);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        initViews();
        bindEvent();
    }
    protected  void setDialogTitle(String title){
        tvTitle.setText(title);
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

    @Override
    protected int bindLayout() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {

    }

    @Override
    protected void mHandleReLogin(int what, Result result) {

    }

    @Override
    protected void mHandle403(int what, Result result) {

    }

    @Override
    protected void mHandleFailed(int what) {

    }

    @Override
    protected void mHandleNoNetwork(int what) {

    }
}
