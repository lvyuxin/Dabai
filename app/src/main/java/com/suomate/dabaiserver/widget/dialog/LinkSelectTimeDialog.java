package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.utils.CommonMethod;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/12.
 */

public class LinkSelectTimeDialog extends BaseDialog {
    private NumberPickerView startHourPicker, startMinutePicker, endHourPicker, endMinutePicker;
    private int startHour, startMinute, endHour, endMinute;

    public LinkSelectTimeDialog(@NonNull Context context, int themeResId, boolean isShowBottom) {
        super(context, themeResId, isShowBottom);
        initView();
        bindEvent();
    }


    private void initView() {
        startHourPicker = findViewById(R.id.picker_start_hour);
        startMinutePicker = findViewById(R.id.picker_start_minute);
        endHourPicker = findViewById(R.id.picker_end_hour);
        endMinutePicker = findViewById(R.id.picker_end_minute);
        setPickerShadowHourData(startHourPicker, 24);
        setPickerShadowHourData(startMinutePicker, 60);
        setPickerShadowHourData(endHourPicker, 24);
        setPickerShadowHourData(endMinutePicker, 60);
    }

    private void setPickerShadowHourData(NumberPickerView picker, int maxVlue) {
        String[] showNums = CommonMethod.getShowNums(maxVlue);
        picker.setDisplayedValues(showNums);
        picker.setMinValue(0);
        picker.setMaxValue(showNums.length - 1);
        picker.setValue(0);   //默认边框宽度
    }

    private void bindEvent() {
        startHourPicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                startHour = oldVal;
            }
        });
        startMinutePicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                startMinute = oldVal;
            }
        });

        endHourPicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                endHour = oldVal;
            }
        });
        endMinutePicker.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                endMinute = oldVal;
            }
        });
    }

}
