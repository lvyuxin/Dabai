package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CommonBean;
import com.suomate.dabaiserver.bean.CustromScenceBean;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog1;

import java.util.ArrayList;
import java.util.List;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/16.
 */

public class StartTaskDialog extends BaseDialog1 {
    private CommonBean.StartTaskDialogBean startTaskDialogBean;
    private List<CustromScenceBean.StateBean> stateList;
    private List<CustromScenceBean.StateBean> stateList2;
    private List<CustromScenceBean.StateBean> stateList3;
    private String[] values;
    private int curPosition;
    private int type;
    private NumberPickerView pickerView, pickerView2, pickerView3;
    private RelativeLayout rl2, rl3;
    private TextView tvleft, tvMid, tvRight;


    public StartTaskDialog(@NonNull Context context, int themeResId, boolean isShowBottom, Object object) {
        super(context, themeResId, isShowBottom, object);
        setDialogTitle(startTaskDialogBean.getTitle());
    }

    @Override
    protected int bindLayout() {
        return R.layout.dialog_start_task;
    }

    @Override
    protected void initViews() {
        stateList = new ArrayList<>();
        stateList2 = new ArrayList<>();
        stateList3 = new ArrayList<>();
        pickerView = findViewById(R.id.picker);
        pickerView2 = findViewById(R.id.picker2);
        pickerView3 = findViewById(R.id.picker3);
        rl2 = findViewById(R.id.rl2);
        rl3 = findViewById(R.id.rl3);
        tvMid = findViewById(R.id.tv2);
        tvRight = findViewById(R.id.tv3);
        tvleft = findViewById(R.id.tv1);
        startTaskDialogBean = (CommonBean.StartTaskDialogBean) object;
        if (ContentStr.Control_type.switchLight.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.electricDoor.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.electromagneticlock.equals(startTaskDialogBean.getControl_type())) {//开关灯
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            setPickerShadowData(stateList, pickerView);
        } else if (ContentStr.Control_type.dimmerLight.equals(startTaskDialogBean.getControl_type())) {
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            tvMid.setText("亮度");
            tvRight.setText("延时");
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            //亮度
            stateList2.add(new CustromScenceBean.StateBean(0, "0%"));
            stateList2.add(new CustromScenceBean.StateBean(1, "10%"));
            stateList2.add(new CustromScenceBean.StateBean(2, "20%"));
            stateList2.add(new CustromScenceBean.StateBean(3, "30%"));
            stateList2.add(new CustromScenceBean.StateBean(4, "40%"));
            stateList2.add(new CustromScenceBean.StateBean(5, "50%"));
            stateList2.add(new CustromScenceBean.StateBean(6, "60%"));
            stateList2.add(new CustromScenceBean.StateBean(7, "70%"));
            stateList2.add(new CustromScenceBean.StateBean(8, "80%"));
            stateList2.add(new CustromScenceBean.StateBean(9, "90%"));
            stateList2.add(new CustromScenceBean.StateBean(10, "100%"));
            //延时
            for (int i = 0; i < 60; i++) {
                stateList3.add(new CustromScenceBean.StateBean(i, i + "s"));
            }
            setPickerShadowData(stateList, pickerView);
            setPickerShadowData(stateList2, pickerView2);
            setPickerShadowData(stateList3, pickerView3);
        } else if (ContentStr.Control_type.colorDimmerLight.equals(startTaskDialogBean.getControl_type())) {//虚拟键
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            setPickerShadowData(stateList, pickerView);
        } else if (ContentStr.Control_type.newWind.equals(startTaskDialogBean.getControl_type())) {
            tvleft.setText("模式");
            tvMid.setText("状态");
            tvMid.setText("风速");
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            //模式
            stateList.add(new CustromScenceBean.StateBean(1, "换气"));
            stateList.add(new CustromScenceBean.StateBean(2, "排风"));
            //状态
            stateList2.add(new CustromScenceBean.StateBean(1, "开"));
            stateList2.add(new CustromScenceBean.StateBean(2, "关"));
            //百分比
            stateList3.add(new CustromScenceBean.StateBean(0, "0%"));
            stateList3.add(new CustromScenceBean.StateBean(1, "10%"));
            stateList3.add(new CustromScenceBean.StateBean(2, "20%"));
            stateList3.add(new CustromScenceBean.StateBean(3, "30%"));
            stateList3.add(new CustromScenceBean.StateBean(4, "40%"));
            stateList3.add(new CustromScenceBean.StateBean(5, "50%"));
            stateList3.add(new CustromScenceBean.StateBean(6, "60%"));
            stateList3.add(new CustromScenceBean.StateBean(7, "70%"));
            stateList3.add(new CustromScenceBean.StateBean(8, "80%"));
            stateList3.add(new CustromScenceBean.StateBean(9, "90%"));
            stateList3.add(new CustromScenceBean.StateBean(10, "100%"));
            setPickerShadowData(stateList, pickerView);
            setPickerShadowData(stateList2, pickerView2);
            setPickerShadowData(stateList3, pickerView3);

        } else if (ContentStr.Control_type.floorHeating.equals(startTaskDialogBean.getControl_type())) {

        } else if (ContentStr.Control_type.curtains.equals(startTaskDialogBean.getControl_type())) {
           //双轨
        } else if (ContentStr.Control_type.windowCurtains.equals(startTaskDialogBean.getControl_type())) {
            tvleft.setText("窗帘轨道");
            tvMid.setText("状态");
            tvMid.setText("百分比");
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            //模式
            stateList.add(new CustromScenceBean.StateBean(1, "换气"));
            stateList.add(new CustromScenceBean.StateBean(2, "排风"));
            //状态
            stateList2.add(new CustromScenceBean.StateBean(1, "开"));
            stateList2.add(new CustromScenceBean.StateBean(2, "关"));
            //风速
            stateList3.add(new CustromScenceBean.StateBean(1, "高"));
            stateList3.add(new CustromScenceBean.StateBean(2, "中"));
            stateList3.add(new CustromScenceBean.StateBean(3, "低"));
            setPickerShadowData(stateList, pickerView);
            setPickerShadowData(stateList2, pickerView2);
            setPickerShadowData(stateList3, pickerView3);
        } else if (ContentStr.Control_type.humanFeeling.equals(startTaskDialogBean.getControl_type())) {

        } else if (ContentStr.Control_type.gas.equals(startTaskDialogBean.getControl_type())) {

        } else if (ContentStr.Control_type.smokeFeeling.equals(startTaskDialogBean.getControl_type())) {

        } else if (ContentStr.Control_type.panel.equals(startTaskDialogBean.getControl_type())) {

        } else if (ContentStr.Control_type.intelligentPanel.equals(startTaskDialogBean.getControl_type())) {

        } else if (ContentStr.Control_type.electricityConversion.equals(startTaskDialogBean.getControl_type())) {

        } else {
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
        }


    }

    private void setPickerShadowData(List<CustromScenceBean.StateBean> stateList, NumberPickerView pickerView) {
        if (stateList.size() > 0) {
            values = new String[stateList.size()];
            for (int i = 0; i < stateList.size(); i++) {
                values[i] = stateList.get(i).getName() + "";
            }
        }
        pickerView.setDisplayedValues(values);
        pickerView.setMinValue(0);
        pickerView.setMaxValue(values.length - 1);
        pickerView.setValue(0);   //默认边框宽度
        pickerView.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                curPosition = oldVal;
            }
        });
    }

    @Override
    protected void bindEvent() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
}
