package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.TimeBean;
import com.suomate.dabaiserver.utils.CommonMethod;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog;

import org.greenrobot.eventbus.EventBus;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/12.
 */

public class LinkSelectTimeDialog extends BaseDialog {
    private NumberPickerView startHourPicker, startMinutePicker, endHourPicker, endMinutePicker;
    private TextView tvCancel,tvSure;
    private int  shour=0,sminute=0,fhour=23,fminute=59;

    public LinkSelectTimeDialog(@NonNull Context context, int themeResId, boolean isShowBottom) {
        super(context, themeResId, isShowBottom);
    }

    @Override
    protected int bindLayout() {
        return R.layout.dialog_link_time;
    }

    @Override
    protected void initViews() {
        startHourPicker = findViewById(R.id.picker_start_hour);
        startMinutePicker = findViewById(R.id.picker_start_minute);
        endHourPicker = findViewById(R.id.picker_end_hour);
        endMinutePicker = findViewById(R.id.picker_end_minute);

        tvCancel=findViewById(R.id.dialog_cancel);
        tvSure=findViewById(R.id.ldialog_sure);
        setPickerShadowHourData();

    }




    private void setPickerShadowHourData() {
        String[] showNums = CommonMethod.getShowNums(24);
//        LogUtils.e("fancycyshowNums:"+showNums.length);
        if (startHourPicker!=null) {
            startHourPicker.setDisplayedValues(showNums);
            startHourPicker.setMinValue(0);
            startHourPicker.setMaxValue(showNums.length - 1);
            startHourPicker.setValue(0);   //默认边框宽度
        }



        String[] showNums1 = CommonMethod.getShowNums(60);
        startMinutePicker.setDisplayedValues(showNums1);
        startMinutePicker.setMinValue(0);
        startMinutePicker.setMaxValue(showNums1.length - 1);
        startMinutePicker.setValue(0);   //默认边框宽度

        String[] showNums2 = CommonMethod.getShowNums(24);
        endHourPicker.setDisplayedValues(showNums2);
        endHourPicker.setMinValue(0);
        endHourPicker.setMaxValue(showNums2.length - 1);
        endHourPicker.setValue(23);   //默认边框宽度
        String[] showNums3 = CommonMethod.getShowNums(60);
        endMinutePicker.setDisplayedValues(showNums3);
        endMinutePicker.setMinValue(0);
        endMinutePicker.setMaxValue(showNums3.length - 1);
        endMinutePicker.setValue(59);   //默认边框宽度

    }


    @Override
    protected void bindEvent() {
        startHourPicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                shour = oldVal;
            }
        });
        startMinutePicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                sminute = oldVal;
            }
        });

        endHourPicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                fhour = oldVal;
            }
        });
        endMinutePicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                fminute = oldVal;
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBean.LinkTimeBean linkTimeBean = new TimeBean.LinkTimeBean(shour+"", sminute+"", fhour+"", fminute+"");
                EventBus.getDefault().post(linkTimeBean);
                dismiss();
//                LogUtils.e("fancycyshour:"+shour+"  sminute:"+sminute+"   fhour"+fhour+"    fminute:"+fminute);
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
