package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/10.
 */

public class SelectAreaDialog extends BaseDialog {
    private TextView tvCancle, tvSure;
    private NumberPickerView pickerView;
    List<AreaSelectListBean.DataBean> areaList=new ArrayList<>();
    private String[] values;
    private CallBackIml callBackIml;
    private String name;
    private int curPosition;

    public void setCallBackIml(CallBackIml callBackIml) {
        this.callBackIml = callBackIml;
    }

    public SelectAreaDialog(@NonNull Context context, int themeResId, boolean isShowBottom, final List<AreaSelectListBean.DataBean> areaList) {
        super(context, themeResId, isShowBottom);
        this.areaList = areaList;
        tvCancle = findViewById(R.id.tv_cancle);
        tvSure = findViewById(R.id.tv_sure);
        pickerView = findViewById(R.id.picker);
        if (areaList.size() > 0) {
            name = areaList.get(0).getArea_name();
            values = new String[areaList.size()];
            for (int i = 0; i < areaList.size(); i++) {
                values[i] = areaList.get(i).getArea_name() + "";
            }
        }
        setPickerShadowData();


        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), TestDataActivity.class);
//                context.startActivity(intent);
//                ((Activity)context).startActivityForResult();
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackIml.callBack(areaList.get(curPosition).getArea_id() + "", name);
                dismiss();
            }
        });

    }


    private void setPickerShadowData() {
        pickerView.setDisplayedValues(values);
        pickerView.setMinValue(0);
        pickerView.setMaxValue(values.length - 1);
        pickerView.setValue(0);   //默认边框宽度
        pickerView.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                curPosition = oldVal;
                if (areaList.size() > 0) {
                    name = values[oldVal];
                }

            }
        });
    }


    @Override
    protected int bindLayout() {
        return R.layout.dialog_config_select;
    }

    @Override
    protected void initViews() {
//        tvCancle = findViewById(R.id.tv_cancle);
//        tvSure = findViewById(R.id.tv_sure);
//        pickerView = findViewById(R.id.picker);
//        if (areaList.size() > 0) {
//            name = areaList.get(0).getArea_name();
//            values = new String[areaList.size()];
//            for (int i = 0; i < areaList.size(); i++) {
//                values[i] = areaList.get(i).getArea_name() + "";
//            }
//        }
//        setPickerShadowData();
    }

    @Override
    protected void bindEvent() {


    }
}
