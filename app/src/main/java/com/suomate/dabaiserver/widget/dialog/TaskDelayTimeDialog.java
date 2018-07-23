package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog1;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/20.
 */

public class TaskDelayTimeDialog extends BaseDialog1 {
    private NumberPickerView pickerView;
    private int curpostion;
    private String time="";
    private CallBackIml callBackIml;

    public void setCallBackIml(CallBackIml callBackIml) {
        this.callBackIml = callBackIml;
    }

    public TaskDelayTimeDialog(@NonNull Context context, int themeResId, boolean isShowBottom) {
        super(context, themeResId, isShowBottom);
        setDialogTitle("延时选择");
//        pickerView=findViewById(R.id.picker_start_hour);
        setPickerShadowHourData();
    }

    @Override
    protected void initViews() {
        super.initViews();
        pickerView=findViewById(R.id.picker_start_hour);
    }

    private void setPickerShadowHourData() {
        String[] showNums = getShowNums(60);
        if (pickerView!=null) {
            pickerView.setDisplayedValues(showNums);
            pickerView.setMinValue(0);
            pickerView.setMaxValue(showNums.length - 1);
            pickerView.setValue(0);   //默认边框宽度
        }
    }
    public  String[] getShowNums(int maxValue){
        String[] nums = new String[maxValue];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + "";
//            if(nums[i].length() == 1){
//                nums[i] = "0" + nums[i];
//            }
        }
        return nums;
    }
    @Override
    protected int bindLayout() {
        return R.layout.dialog_delay_time;
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        pickerView.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                curpostion = oldVal;
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackIml.callBack(curpostion);
                dismiss();
            }
        });
    }
}
