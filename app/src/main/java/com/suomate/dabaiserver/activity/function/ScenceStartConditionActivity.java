package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.IoDeviceAdapter;
import com.suomate.dabaiserver.adapter.function.SelectTimeAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.CustromScenceBean;
import com.suomate.dabaiserver.bean.IoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.bean.TimeBean;
import com.suomate.dabaiserver.bean.TimeLaunchBean;
import com.suomate.dabaiserver.utils.DeviceUtils;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.suomate.dabaiserver.widget.dialog.LinkSelectTimeDialog;
import com.suomate.dabaiserver.widget.dialog.LinkStateSelectDialog;
import com.suomate.dabaiserver.widget.dialog.LinkWeekDialog;
import com.yanzhenjie.nohttp.RequestMethod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class ScenceStartConditionActivity extends BaseActivity {
    private Intent intent;
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.number_pickerview_left)
    NumberPickerView pickerViewLeft;
    @BindView(R.id.number_pickerview_right)
    NumberPickerView pickerViewRight;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.link_time_rb)
    RadioButton rbTime;
    @BindView(R.id.io_recycler)
    RecyclerView ioRecycler;

    @BindView(R.id.check_instant_start)
    CheckBox checkInstant;
    @BindView(R.id.check_time_start)
    CheckBox checkTime;
    @BindView(R.id.check_link_start)
    CheckBox checkLink;

    private SelectTimeAdapter adapter;
    private String hour, minute1;
    //    private int modeType;
    private int intstantType, timeType, linkType;
    private int chooseMin, chooseHour;
    private List<TimeBean> weeksList = new ArrayList<>();
    private String strWeek = "", timeLaunchStr = "";
    private LinkSelectTimeDialog linkSelectTimeDialog;
    private LinkWeekDialog linkWeekDialog;
    private String strLinkTime;
    private List<IoBean> ioList = new ArrayList<>();
    private IoDeviceAdapter ioDeviceAdapter;
    private CheckBox checkBox;
    private LinkStateSelectDialog linkStateSelectDialog;
    private int ioCurPosition;
    private TimeBean.LinkTimeBean linkTimeBean = new TimeBean.LinkTimeBean();
    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_start_mudle;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setWindowStatusBarColor(R.color.title_color);
        intent = getIntent();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SelectTimeAdapter(R.layout.item_select_time, weeksList);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        ioRecycler.setLayoutManager(new LinearLayoutManager(this));
        ioDeviceAdapter = new IoDeviceAdapter(R.layout.item_io_layout, ioList);
        ioRecycler.setAdapter(ioDeviceAdapter);
        requestIoTypeData();
        setData();
        bindEvent();
        setPickerShadowData();
        setPickerShadowDataRight();
    }

    private void requestIoTypeData() {
        AbstractRequest request = buildRequest(UrlUtils.getIoDevice, Content.LIST_TYPE, RequestMethod.GET, IoBean.class);
        request.add("guid", getGuid());
        executeNetwork(1, request);
    }


    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                ioList.addAll((List<IoBean>) result.getData());

//        //io设备类添加默认状态
//        for (int i = 0; i < ioList.size(); i++) {
//            if (ioList.get(i).getControl_type().equals(ContentStr.Control_type.humanFeeling)) {//人感
//                ioList.get(i).setIotype(Content.TYPE.TRIGGER);
//            } else if (ioList.get(i).getControl_type().equals(ContentStr.Control_type.smokeFeeling)) {//烟感
//                ioList.get(i).setIotype(Content.TYPE.TRIGGER);
//            } else if (ioList.get(i).getControl_type().equals(ContentStr.Control_type.gas)) {//燃气报警
//                ioList.get(i).setIotype(Content.TYPE.TRIGGER);
//            } else {//
//                ioList.get(i).setIotype(Content.TYPE.TRIGGER);
//            }
//
//        }
                ioDeviceAdapter.notifyDataSetChanged();
                break;

        }
    }

    @OnClick({R.id.btn_cancle, R.id.btn_sure, R.id.link_time_rb, R.id.link_week_rb
            , R.id.btn_submit})
    public void onClick(View v) {
        switch (v.getId()) {
//                case R.id.tv_instant_start:
//                    intent.putExtra("type", 1);
//                    modeType = 1;
//                    setResult(RESULT_OK, intent);
//                    finish();
//                    break;
//            case R.id.tv_time_start:
//                modeType = 2;
//                new ChooseTimeDialog(this, R.style.basedialog_style, true).show();
//                break
            case R.id.btn_cancle:
                break;
            case R.id.btn_sure:
                break;
            case R.id.link_time_rb://全天
                linkSelectTimeDialog = new LinkSelectTimeDialog(this, R.style.detail_dialog_style, true);
                linkSelectTimeDialog.show();
                break;
            case R.id.link_week_rb://每天
                linkWeekDialog = new LinkWeekDialog(this, R.style.detail_dialog_style, true);
                linkWeekDialog.show();
                break;
            case R.id.btn_submit:
                submitData();
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(TimeBean.LinkTimeBean linkTimeBean) {
        timeLaunchStr = JSON.toJSONString(linkTimeBean);
        LogUtils.e("launchStr:" + timeLaunchStr);
    }

    private void setData() {
        weeksList.add(new TimeBean("每天", true, "1,2,3,4,5,6,0"));
        weeksList.add(new TimeBean("星期一", true, "1"));
        weeksList.add(new TimeBean("星期二", true, "2"));
        weeksList.add(new TimeBean("星期三", true, "3"));
        weeksList.add(new TimeBean("星期四", true, "4"));
        weeksList.add(new TimeBean("星期五", true, "5"));
        weeksList.add(new TimeBean("星期六", true, "6"));
        weeksList.add(new TimeBean("星期天", true, "0"));
        adapter.notifyDataSetChanged();

    }

    private void setPickerShadowData() {
        String[] showNums = getShowNums(24);
        pickerViewLeft.setDisplayedValues(showNums);
        pickerViewLeft.setMinValue(0);
        pickerViewLeft.setMaxValue(showNums.length - 1);
        pickerViewLeft.setValue(0);   //默认边框宽度
        pickerViewLeft.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                chooseHour = oldVal;
            }
        });
    }

    private void setPickerShadowDataRight() {
        String[] showNums = getShowNums(60);
        pickerViewRight.setDisplayedValues(showNums);
        pickerViewRight.setMinValue(0);
        pickerViewRight.setMaxValue(showNums.length - 1);
        pickerViewRight.setValue(0);   //默认边框宽度
        pickerViewRight.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                chooseMin = oldVal;
                LogUtils.d(TAG, "chooseMin:" + chooseMin);
            }
        });
    }

    private String[] getShowNums(int maxValue) {
        String[] nums = new String[maxValue];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + "";
            if (nums[i].length() == 1) {
                nums[i] = "0" + nums[i];
            }
        }
        return nums;
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isSelect = weeksList.get(position).isSelect();
                weeksList.get(position).setSelect(!isSelect);
                if (position == 0) {
                    changeAllState(weeksList.get(position).isSelect());
                } else {
                    int count = 0;
                    for (int i = 1; i < weeksList.size(); i++) {
                        if (weeksList.get(i).isSelect()) {
                            count = +1;
                        }
                    }
                    if (count < 7) {
                        weeksList.get(0).setSelect(false);
                    } else {
                        weeksList.get(0).setSelect(true);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        /**
         * 每个item的点击事件
         */
        ioDeviceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ioCurPosition = position;
                if (ioList.get(position).getControl_type().equals(ContentStr.Control_type.humanFeeling)) {
                    linkStateSelectDialog = new LinkStateSelectDialog(ScenceStartConditionActivity.this, R.style.detail_dialog_style, true, ioList.get(position).getDevice_name(), Content.TYPE.TYPE_HUMAN_FEELING);
                    linkStateSelectDialog.show();
                } else if (ioList.get(position).getControl_type().equals(ContentStr.Control_type.gas)) {
                    linkStateSelectDialog = new LinkStateSelectDialog(ScenceStartConditionActivity.this, R.style.detail_dialog_style, true, ioList.get(position).getDevice_name(), Content.TYPE.TYPE_GAS);
                    linkStateSelectDialog.show();
                } else if (ioList.get(position).getControl_type().equals(ContentStr.Control_type.smokeFeeling)) {
                    linkStateSelectDialog = new LinkStateSelectDialog(ScenceStartConditionActivity.this, R.style.detail_dialog_style, true, ioList.get(position).getDevice_name(), Content.TYPE.TYPE_SMOKE);
                    linkStateSelectDialog.show();
                } else if (ioList.get(position).getControl_type().equals(ContentStr.Control_type.panel)) {
                    linkStateSelectDialog = new LinkStateSelectDialog(ScenceStartConditionActivity.this, R.style.detail_dialog_style, true, ioList.get(position).getDevice_name(), Content.TYPE.TYPE_IO_PANEL);
                    linkStateSelectDialog.show();
                } else if (ioList.get(position).getControl_type().equals(ContentStr.Control_type.intelligentPanel)) {
                    linkStateSelectDialog = new LinkStateSelectDialog(ScenceStartConditionActivity.this, R.style.detail_dialog_style, true, ioList.get(position).getDevice_name(), Content.TYPE.TYPE_INTELLIGENT_PANEL);
                    linkStateSelectDialog.show();
                }

            }
        });

        ioDeviceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.check:
                        checkBox = (CheckBox) view;
                        ioList.get(position).setSelect(checkBox.isChecked());
                        break;
                }
            }
        });

    }

    private void changeAllState(boolean state) {
        for (int i = 0; i < weeksList.size(); i++) {
            weeksList.get(i).setSelect(state);
        }
    }

    private void submitData() {
        Bundle bundle = new Bundle();
        if (checkInstant.isChecked()) {
            intstantType = Content.TYPE.TYPE_INSTANT;
        } else {
            intstantType = 0;
        }
        /**
         * 定时启动
         */
        if (checkTime.isChecked()) {
            timeType = Content.TYPE.TYPE_TIME;
            if (weeksList.get(0).isSelect()) {
                strWeek = weeksList.get(0).getType();
            } else {
                for (int i = 1; i < weeksList.size(); i++) {
                    if (weeksList.get(i).isSelect()) {
                        strWeek = strWeek + weeksList.get(i).getType() + ",";
                    }
                }
                strWeek = strWeek.substring(0, strWeek.lastIndexOf(","));
            }

            TimeLaunchBean bean = new TimeLaunchBean();
            bean.setHour(DeviceUtils.getTime(chooseHour + ""));
            bean.setMinute(DeviceUtils.getTime(chooseMin + ""));
            bean.setWeek(strWeek);
//            timeLaunchStr = JSON.toJSONString(bean);
            bundle.putSerializable("timeLaunchBean", bean);
        } else {
            timeType = 0;
        }
/**
 * 联动启动
 */
        if (checkLink.isChecked()) {
            linkType = Content.TYPE.TYPE_LINK;
            if (TextUtils.isEmpty(linkTimeBean.getFhour()) || TextUtils.isEmpty(linkTimeBean.getFminute()) ||
                    TextUtils.isEmpty(linkTimeBean.getShour()) || TextUtils.isEmpty(linkTimeBean.getSminute())) {
                showToast("请选择时间");
                return;
            } else {
                if (TextUtils.isEmpty(linkTimeBean.getWeek())) {
                    showToast("请选择周天数");
                    return;
                } else {
                    bundle.putSerializable("linkTimeBean", linkTimeBean);
                }
            }

            List<TimeBean.LinkLanchDevice> linkLanchDeviceList = new ArrayList<>();
            for (int i = 0; i < ioList.size(); i++) {
                if (ioList.get(i).isSelect()) {
                    linkLanchDeviceList.add(new TimeBean.LinkLanchDevice(ioList.get(i).getDevice_id(), ioList.get(i).getIotype(), ioList.get(i).getPanel_number(),ioList.get(i).getDevice_name()));
                }
            }
            if (linkLanchDeviceList.size() <= 0) {
                showToast("请选择联动触发设备");
                return;
            } else {
                bundle.putSerializable("linkLanchDeviceList", (Serializable) linkLanchDeviceList);
//                String toJSONString = JSONArray.toJSONString(linkLanchDeviceList);
            }
        } else {
            linkType = 0;
        }


        if (intstantType == 0 && timeType == 0 && linkType == 0) {
            showToast("请至少选择一种启动方式");
            return;
        }
        bundle.putInt("intstantType", intstantType);
        bundle.putInt("timeType", timeType);
        bundle.putInt("linkType", linkType);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Subscribe
    public void onMianThread(CustromScenceBean.StateBean stateBean) {
        ioList.get(ioCurPosition).setIotype(stateBean.getType());
        ioDeviceAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onMianThread1(TimeBean.LinkTimeBean linkTimeBean1) {
        this.linkTimeBean = linkTimeBean1;
        linkTimeBean.setFhour(linkTimeBean1.getFhour());
        linkTimeBean.setFminute(linkTimeBean1.getFminute());
        linkTimeBean.setShour(linkTimeBean1.getShour());
        linkTimeBean.setSminute(linkTimeBean1.getSminute());
//        LogUtils.e("fancylinkTimeBean:"+JSON.toJSONString(linkTimeBean));

    }

    @Subscribe
    public void onMianThread2(String strWeek) {
        linkTimeBean.setWeek(strWeek);
        LogUtils.e("fancylinkTimeBean:" + JSON.toJSONString(linkTimeBean));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
