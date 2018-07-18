package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CommonBean;
import com.suomate.dabaiserver.bean.CustromScenceBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog1;
import com.yanzhenjie.nohttp.RequestMethod;

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
    private List<CustromScenceBean.StateBean> stateList4;
    private String[] values;
    private int curPosition;
    private int type;
    private NumberPickerView pickerView, pickerView2, pickerView3, pickerView4;
    private RelativeLayout rl2, rl3, rl4;
    private TextView tvleft, tvMid, tvRight, tvEnd;
    private List<CommonBean.PanelNumberBean> panelNumberBeanList;


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
        pickerView4 = findViewById(R.id.picker4);
        rl2 = findViewById(R.id.rl2);
        rl3 = findViewById(R.id.rl3);
        rl4 = findViewById(R.id.rl4);
        tvMid = findViewById(R.id.tv2);
        tvRight = findViewById(R.id.tv3);
        tvleft = findViewById(R.id.tv1);
        tvEnd = findViewById(R.id.tv4);
        startTaskDialogBean = (CommonBean.StartTaskDialogBean) object;
        if (startTaskDialogBean.getJson_type()== Content.JSON_TYPE.SCENCE) {//场景
            tvleft.setText("状态");
            setRlVisible(false,false,false);
            stateList.add(new CustromScenceBean.StateBean(1,"失效"));
            stateList.add(new CustromScenceBean.StateBean(2,"执行"));
            stateList.add(new CustromScenceBean.StateBean(3,"生效"));
            setPickerShadowData(stateList,pickerView);
        }else if(startTaskDialogBean.getJson_type()== Content.JSON_TYPE.SCENCE_TIME){//定时场景
            tvleft.setText("状态");
            setRlVisible(false,false,false);
            stateList.add(new CustromScenceBean.StateBean(1,"失效"));
            stateList.add(new CustromScenceBean.StateBean(2,"执行"));
            stateList.add(new CustromScenceBean.StateBean(3,"生效"));
            setPickerShadowData(stateList,pickerView);
        }

        if (ContentStr.Control_type.switchLight.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.electricDoor.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.electromagneticlock.equals(startTaskDialogBean.getControl_type())) {//开关灯
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            setRlVisible(false, false, false);
            setPickerShadowData(stateList, pickerView);
        } else if (ContentStr.Control_type.dimmerLight.equals(startTaskDialogBean.getControl_type())) {
            setDimmerLight();
        } else if (ContentStr.Control_type.colorDimmerLight.equals(startTaskDialogBean.getControl_type())) {//虚拟键
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            setRlVisible(false, false, false);
            setPickerShadowData(stateList, pickerView);
        } else if (ContentStr.Control_type.newWind.equals(startTaskDialogBean.getControl_type())) {
            setNewWind();
            /**
             * 地暖
             */
        } else if (ContentStr.Control_type.floorHeating.equals(startTaskDialogBean.getControl_type())) {
            tvleft.setText("状态");
            tvMid.setText("温度");
            //状态
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            //温度
            for (int i = 18; i <= 35; i++) {
                stateList2.add(new CustromScenceBean.StateBean(i, i + ""));
            }

            /**
             * 单轨道窗帘
             */
        } else if (ContentStr.Control_type.curtains.equals(startTaskDialogBean.getControl_type())) {
            setSingleCurtain();

            /**
             * 双轨
             */
        } else if (ContentStr.Control_type.windowCurtains.equals(startTaskDialogBean.getControl_type())) {
            setDoubleCurtain();

        } else if (ContentStr.Control_type.humanFeeling.equals(startTaskDialogBean.getControl_type())) {
            tvleft.setText("状态");
            setRlVisible(false,false,false);
            stateList.add(new CustromScenceBean.StateBean(1, "无人"));
            stateList.add(new CustromScenceBean.StateBean(2, "有人"));
            setPickerShadowData(stateList,pickerView);
        } else if (ContentStr.Control_type.gas.equals(startTaskDialogBean.getControl_type())) {
            tvleft.setText("状态");
            setRlVisible(false,false,false);
            stateList.add(new CustromScenceBean.StateBean(1, "无燃气泄漏"));
            stateList.add(new CustromScenceBean.StateBean(2, "有燃气泄漏"));
            setPickerShadowData(stateList,pickerView);
        } else if (ContentStr.Control_type.smokeFeeling.equals(startTaskDialogBean.getControl_type())) {
            tvleft.setText("状态");
            setRlVisible(false,false,false);
            stateList.add(new CustromScenceBean.StateBean(1, "无烟雾报警"));
            stateList.add(new CustromScenceBean.StateBean(2, "有烟雾报警"));
            setPickerShadowData(stateList,pickerView);
        } else if (ContentStr.Control_type.panel.equals(startTaskDialogBean.getControl_type())) {//面板信息
            setPanel();
        } else if (ContentStr.Control_type.intelligentPanel.equals(startTaskDialogBean.getControl_type())) {
            setPanel();
        } else if (ContentStr.Control_type.electricityConversion.equals(startTaskDialogBean.getControl_type())) {
            /**
             * 空调
             */
        } else if (ContentStr.Control_type.airCondition.equals(startTaskDialogBean.getControl_type())) {
            setAirCondition();
            /**
             * 虚拟键
             */
        } else if(ContentStr.Control_type.fictitiousDevice.equals(startTaskDialogBean.getControl_type())){//虚拟端口
            stateList.add(new CustromScenceBean.StateBean(1, "开"));
            stateList.add(new CustromScenceBean.StateBean(2, "关"));
            setRlVisible(false, false, false);
            setPickerShadowData(stateList, pickerView);
        }
    }

    private void setAirCondition() {
        tvleft.setText("模式");
        tvMid.setText("状态");
        tvleft.setText("风速");
        tvEnd.setText("温度");
        setRlVisible(true, true, true);
        stateList4 = new ArrayList<>();
        //模式
        stateList.add(new CustromScenceBean.StateBean(1, "冷"));
        stateList.add(new CustromScenceBean.StateBean(2, "热"));
        stateList.add(new CustromScenceBean.StateBean(3, "智能"));
        //状态
        stateList2.add(new CustromScenceBean.StateBean(1, "开"));
        stateList2.add(new CustromScenceBean.StateBean(2, "关"));
        //风速
        stateList3.add(new CustromScenceBean.StateBean(1, "高"));
        stateList3.add(new CustromScenceBean.StateBean(2, "中"));
        stateList3.add(new CustromScenceBean.StateBean(3, "低"));
        //温度
        for (int i = 16; i <= 35; i++) {
            stateList4.add(new CustromScenceBean.StateBean(16, i + ""));
        }
        setPickerShadowData(stateList, pickerView);
        setPickerShadowData(stateList2, pickerView2);
        setPickerShadowData(stateList3, pickerView3);
        setPickerShadowData(stateList4, pickerView4);

    }

    private void setPanel() {
        tvleft.setText("面板按键");
        tvMid.setText("执行模式");
        tvRight.setText("背光灯状态");
//        tvEnd.setText("指向");
        setRlVisible(true, true, false);
        panelNumberBeanList = new ArrayList<>();
        for (int i = 0; i < panelNumberBeanList.size(); i++) {
            stateList.add(new CustromScenceBean.StateBean(i+1,"按键"+i+"："+ panelNumberBeanList.get(i).getName()));
        }
        stateList2.add(new CustromScenceBean.StateBean(1,"长按"));
        stateList2.add(new CustromScenceBean.StateBean(2,"按下"));
        stateList2.add(new CustromScenceBean.StateBean(3,"生效"));

        stateList3.add(new CustromScenceBean.StateBean(1,"失效"));
        stateList3.add(new CustromScenceBean.StateBean(2,"生效"));
        stateList3.add(new CustromScenceBean.StateBean(3,"执行"));
        requestPanelInfo();
        setPickerShadowData(stateList2,pickerView2);
        setPickerShadowData(stateList3,pickerView3);

    }

    private void setSingleCurtain() {
        tvleft.setText("窗帘轨道");
        tvMid.setText("状态");
        tvMid.setText("百分比");
        setRlVisible(true, true, false);
        //模式
        stateList.add(new CustromScenceBean.StateBean(1, "纱帘"));
        //状态
        stateList2.add(new CustromScenceBean.StateBean(1, "开"));
        stateList2.add(new CustromScenceBean.StateBean(2, "关"));
        //风速
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

    }

    private void setDoubleCurtain() {
        tvleft.setText("窗帘轨道");
        tvMid.setText("状态");
        tvMid.setText("百分比");
        setRlVisible(true, true, false);
        //窗帘轨道
        stateList.add(new CustromScenceBean.StateBean(1, "纱帘"));
        stateList.add(new CustromScenceBean.StateBean(2, "布帘"));
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
    }

    private void setNewWind() {
        tvleft.setText("模式");
        tvMid.setText("状态");
        tvMid.setText("风速");
        setRlVisible(true, true, false);
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
    }

    private void setDimmerLight() {
        stateList.add(new CustromScenceBean.StateBean(1, "开"));
        stateList.add(new CustromScenceBean.StateBean(2, "关"));
        tvMid.setText("亮度");
        tvRight.setText("延时");
        setRlVisible(true, true, false);
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
    }

    private void setRlVisible(boolean b2, boolean b3, boolean b4) {
        if (b2) {
            rl2.setVisibility(View.VISIBLE);
        } else {
            rl2.setVisibility(View.GONE);
        }
        if (b3) {
            rl3.setVisibility(View.VISIBLE);
        } else {
            rl3.setVisibility(View.GONE);
        }
        if (b4) {
            rl4.setVisibility(View.VISIBLE);
        } else {
            rl4.setVisibility(View.GONE);
        }
    }

    private void requestPanelInfo() {
        AbstractRequest request = buildRequest(UrlUtils.getPanelNumberInfo, Content.LIST_TYPE, RequestMethod.GET, CommonBean.PanelNumberBean.class);
        request.add("deviceId", startTaskDialogBean.getDeviceId());
        executeNetwork(1, request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                panelNumberBeanList.addAll((List<CommonBean.PanelNumberBean>) result.getData());
                setPickerShadowData1(panelNumberBeanList, pickerView);
                break;
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

    /**
     * 面板
     *
     * @param panelNumberBeanList
     * @param pickerView
     */
    private void setPickerShadowData1(List<CommonBean.PanelNumberBean> panelNumberBeanList, NumberPickerView pickerView) {
        if (panelNumberBeanList.size() > 0) {
            values = new String[panelNumberBeanList.size()];
            for (int i = 0; i < panelNumberBeanList.size(); i++) {
                values[i] = panelNumberBeanList.get(i).getName() ;
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
