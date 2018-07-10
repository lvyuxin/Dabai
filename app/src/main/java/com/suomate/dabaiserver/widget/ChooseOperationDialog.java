package com.suomate.dabaiserver.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.utils.CallBackIml;

/**
 * Created by fanxi on 2018/7/9.
 */

public class ChooseOperationDialog extends BaseDialog {
    private TextView tvDetail;
    private TextView tvDelete;
    private TextView tvCancel;
    private CallBackIml callBackIml;
    private int type;

    public void setCallBackIml(CallBackIml callBackIml) {
        this.callBackIml = callBackIml;
    }

    public ChooseOperationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_choose_operation);
//        tvDetail.setOnClickListener(context);
        init();
        bindEvent();
    }

    private void init() {
        tvDetail = findViewById(R.id.tv_detail);
        tvDelete = findViewById(R.id.tv_delete);
        tvCancel = findViewById(R.id.tv_cancel);
    }


    private void bindEvent() {
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=1;
                callBackIml.callBack(type);
                dismiss();
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=2;
                callBackIml.callBack(type);
                dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


}
