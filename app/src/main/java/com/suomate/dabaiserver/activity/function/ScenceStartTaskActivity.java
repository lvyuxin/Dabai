package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSON;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.ScenceStartTaskAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.CommonBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.bean.ScenceStartTaskBean;
import com.suomate.dabaiserver.utils.CommonMethod;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.yanzhenjie.nohttp.RequestMethod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScenceStartTaskActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private ScenceStartTaskAdapter adapter;
    private List<ScenceStartTaskBean> list = new ArrayList<>();
    private int allCount, selectCount;
    private CheckBox checkAll;

    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_start_task;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setWindowStatusBarColor(R.color.black);
        super.initViews(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScenceStartTaskAdapter(R.layout.item_scence_task, list, this);

        initRecycler();
        bindEvent();
        requestTaskData();
    }

    private void bindEvent() {

//        //判断全选装态
//        adapter.setCallBackIml(new CallBackIml(){
//            @Override
//            public void confirmHandle() {
//                super.confirmHandle();
//                for (int i = 0; i < list.size(); i++) {
//                    allCount+= list.get(i).getDeviceOrSceneInfo().size();
//                    for (int i1 = 0; i1 < list.get(i).getDeviceOrSceneInfo().size(); i1++) {
//                        if (list.get(i).getDeviceOrSceneInfo().get(i1).isSelect()) {
//                            selectCount+=1;
//                        }
//                    }
//                }
//                LogUtils.e("fancyallCount:"+allCount);
//                LogUtils.e("fancyallCount1:"+selectCount);
//                if (allCount==selectCount) {
//                     checkAll.setChecked(true);
//                }else{
//                    checkAll.setChecked(false);
//                }
//                allCount=0;
//                selectCount=0;
//            }
//        });

    }

    private void initRecycler() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_scence_task, null);
        View footer = LayoutInflater.from(this).inflate(R.layout.item_scence_task_footer, null);
        Button btnSubmit = footer.findViewById(R.id.footer_submit_btn);
        //提交数据
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//判断哪些是选中的

            }
        });

        checkAll = header.findViewById(R.id.head_check);
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (ScenceStartTaskBean scenceStartTaskBean : list) {
                        for (ScenceStartTaskBean.DeviceOrSceneInfoBean deviceOrSceneInfoBean : scenceStartTaskBean.getDeviceOrSceneInfo()) {
                            deviceOrSceneInfoBean.setSelect(true);
                        }
                    }

                } else {
                    for (ScenceStartTaskBean scenceStartTaskBean : list) {
                        for (ScenceStartTaskBean.DeviceOrSceneInfoBean deviceOrSceneInfoBean : scenceStartTaskBean.getDeviceOrSceneInfo()) {
                            deviceOrSceneInfoBean.setSelect(false);
                        }
                    }

                }
                adapter.notifyDataSetChanged();
            }
        });
        adapter.addHeaderView(header);
        adapter.addFooterView(footer);
        recyclerView.setAdapter(adapter);
    }

    private void requestTaskData() {
        AbstractRequest request = buildRequest(UrlUtils.getSceneDeviceAccessClassify, Content.LIST_TYPE, RequestMethod.GET, ScenceStartTaskBean.class);
        request.add("guid", getGuid());
        executeNetwork(1, request);
    }

    @Subscribe
    public void onEventMainThread1(CommonBean.StartTaskDialogBean startTaskDialogBean) {
        LogUtils.e("fancycytype:"+JSON.toJSONString(startTaskDialogBean));
        if (startTaskDialogBean.getJson_type() == Content.JSON_TYPE.SCENCE || startTaskDialogBean.getJson_type() == Content.JSON_TYPE.SCENCE_TIME) {//场景
            switch (startTaskDialogBean.getValue1()) {
                case 1://失效
                    startTaskDialogBean.setDetail("失效");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.scence_lose_efficacy);
                    break;
                case 2://执行
                    startTaskDialogBean.setDetail("执行");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.scence_execute);
                    break;
                case 3://生效
                    startTaskDialogBean.setDetail("生效");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.scence_take_efficacy);
                    break;
            }

        } else if (ContentStr.Control_type.switchLight.equals(startTaskDialogBean.getControl_type()) ||
                ContentStr.Control_type.electricDoor.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.colorDimmerLight.equals(startTaskDialogBean.getControl_type()) ||
                ContentStr.Control_type.electromagneticlock.equals(startTaskDialogBean.getControl_type())) {//开关灯
            switch (startTaskDialogBean.getValue1()) {
                case 1://开
                    startTaskDialogBean.setDetail("开");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.open_light);
                    break;
                case 2:
                    startTaskDialogBean.setDetail("关");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.close_light);
                    break;
            }

        } else if (ContentStr.Control_type.dimmerLight.equals(startTaskDialogBean.getControl_type())) {
            String start = "1000";
            String time = CommonMethod.getHexStringValue(startTaskDialogBean.getValue2() - 1);
            String percent = CommonMethod.getHexStringValue(startTaskDialogBean.getValue3());
            if (startTaskDialogBean.getValue3()==0) {
                startTaskDialogBean.setDetail("关");
            }else{
                startTaskDialogBean.setDetail("开"+startTaskDialogBean.getValue3()*10+"%");
            }

            setValue(startTaskDialogBean, start + time + percent);
        } else if (ContentStr.Control_type.newWind.equals(startTaskDialogBean.getControl_type())) {
            String order = "";
            switch (startTaskDialogBean.getValue1()) {
                case 1://换气
                    switch (startTaskDialogBean.getValue2()) {
                        case 1://gao
                            startTaskDialogBean.setDetail("换气，高");
                            order = "00000005";
                            break;
                        case 2:
                            startTaskDialogBean.setDetail("换气，高");
                            order = "00000004";
                            break;
                        case 3:
                            startTaskDialogBean.setDetail("换气，低");
                            order = "00000003";
                            break;
                    }
                    break;
                case 2://排风
                    switch (startTaskDialogBean.getValue2()) {
                        case 1://gao
                            startTaskDialogBean.setDetail("排风，高");
                            order = "00000008";
                            break;
                        case 2:
                            startTaskDialogBean.setDetail("排风，中");
                            order = "00000007";
                            break;
                        case 3:
                            startTaskDialogBean.setDetail("排风，低");
                            order = "00000006";
                            break;
                    }
                    break;
            }

            setValue(startTaskDialogBean, order);
            /**
             * 地暖
             */
        } else if (startTaskDialogBean.getControl_type().equals("floorHeating")) {
            String order = "000000";
            switch (startTaskDialogBean.getPort()) {
                case 1:
                    if (startTaskDialogBean.getValue1() == 1) {//开
//                        order = order + "01";
                        //18到35度
                        for (int i = 18; i <= 35; i++) {
                            if ((startTaskDialogBean.getValue2() + 17) == i) {
                                order = order + CommonMethod.getHexStringValue(i);
                                startTaskDialogBean.setDetail("开，" + i + "度");
                                setValue(startTaskDialogBean, order);
                            }
                        }

                    } else if (startTaskDialogBean.getValue1() == 2) {//关
                        order = order + "02";
                        startTaskDialogBean.setDetail("关");
                        setValue(startTaskDialogBean, order);
                    }
                    break;
                case 2:
                    if (startTaskDialogBean.getValue1() == 1) {//开
                        //18到35度
                        for (int i = 23; i <= 40; i++) {
                            if ((startTaskDialogBean.getValue2() + 22) == i) {
                                order = order + CommonMethod.getHexStringValue(i);
                                startTaskDialogBean.setDetail("开，" + startTaskDialogBean.getValue2() + "度");

                                setValue(startTaskDialogBean, order);

                            }
                        }

                    } else if (startTaskDialogBean.getValue1() == 2) {//关
                        order = order + "16";
                        startTaskDialogBean.setDetail("关");

                        setValue(startTaskDialogBean, order);
                    }
                case 3:

                    if (startTaskDialogBean.getValue1() == 1) {//开
                        //18到35度
                        for (int i = 43; i <= 60; i++) {//2b-3c
                            if ((startTaskDialogBean.getValue2() + 42) == i) {
                                order = order + CommonMethod.getHexStringValue(i);
                                startTaskDialogBean.setDetail("开，" + startTaskDialogBean.getValue2() + "度");
                                setValue(startTaskDialogBean, order);

                            }
                        }

                    } else if (startTaskDialogBean.getValue1() == 2) {//关
                        order = order + "2A";
                        startTaskDialogBean.setDetail("关");
                        setValue(startTaskDialogBean, order);

                    }
                    break;
                case 4:
                    if (startTaskDialogBean.getValue1() == 1) {//开
                        //18到35度
                        for (int i = 63; i <= 80; i++) {//3f-50
                            if ((startTaskDialogBean.getValue2() + 62) == i) {
                                order = order + CommonMethod.getHexStringValue(i);
                                startTaskDialogBean.setDetail("开，" + startTaskDialogBean.getValue2() + "度");

                                setValue(startTaskDialogBean, order);

                            }
                        }

                    } else if (startTaskDialogBean.getValue1() == 2) {//关
                        order = order + "3E";
                        startTaskDialogBean.setDetail("关");

                        setValue(startTaskDialogBean, order);
                    }
                    break;
            }

            /**
             * 485单轨道窗帘
             */
        } else if (ContentStr.Control_type.curtains.equals(startTaskDialogBean.getControl_type())) {
            switch (startTaskDialogBean.getPort()) {
                case 1:
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3()));
                    break;
                case 2:
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 25));
                    break;
                case 3:
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 50));
                    break;
                case 4:
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 75));

                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");
                    break;
            }
            /**
             * 485双轨
             */
        } else if (ContentStr.Control_type.windowCurtains.equals(startTaskDialogBean.getControl_type())) {
            switch (startTaskDialogBean.getPort()) {
                case 1://Bu
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3()));
                    break;
                case 2:
                    startTaskDialogBean.setDetail("纱" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 12));
                    break;
                case 3://Bu
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 25));
                    break;
                case 4:
                    startTaskDialogBean.setDetail("纱" + startTaskDialogBean.getValue3() * 10 + "%");
                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 37));//26-30
                    break;
                case 5://Bu
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");

                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 50));
                    break;
                case 6:
                    startTaskDialogBean.setDetail("纱" + startTaskDialogBean.getValue3() * 10 + "%");

                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 62));//3f-49
                    break;
                case 7://Bu
                    startTaskDialogBean.setDetail("布" + startTaskDialogBean.getValue3() * 10 + "%");

                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 75));
                    break;
                case 8:
                    startTaskDialogBean.setDetail("纱" + startTaskDialogBean.getValue3() * 10 + "%");

                    setValue(startTaskDialogBean, CommonMethod.getHexStringValue(startTaskDialogBean.getValue3() + 87));//58-62
                    break;
            }

//        } else if (ContentStr.Control_type.gas.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.smokeFeeling.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.humanFeeling.equals(startTaskDialogBean.getControl_type())) {
//        } else if (ContentStr.Control_type.panel.equals(startTaskDialogBean.getControl_type()) || ContentStr.Control_type.intelligentPanel.equals(startTaskDialogBean.getControl_type())) {//面板信息
//        } else if (ContentStr.Control_type.electricityConversion.equals(startTaskDialogBean.getControl_type())) {
            /**
             * 空调
             */
        } else if (ContentStr.Control_type.airCondition.equals(startTaskDialogBean.getControl_type())) {
            /**
             * 虚拟键
             */
        } else if (ContentStr.Control_type.fictitiousDevice.equals(startTaskDialogBean.getControl_type())) {//虚拟端口
            switch (startTaskDialogBean.getValue1()) {
                case 1://开
                    startTaskDialogBean.setDetail("开");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.open_light);
                    break;
                case 2:
                    startTaskDialogBean.setDetail("关");
                    setValue(startTaskDialogBean, ContentStr.Control_Order.close_light);
                    break;
            }
        }

    }

    private void setValue(CommonBean.StartTaskDialogBean startTaskDialogBean, String order) {
        LogUtils.e("fancycystartTaskDialogBean"+ JSON.toJSONString(startTaskDialogBean));
        List<ScenceStartTaskBean.DeviceOrSceneInfoBean> beans;
        for (int i = 0; i < list.size(); i++) {
            beans = list.get(i).getDeviceOrSceneInfo();
            for (int i1 = 0; i1 < beans.size(); i1++) {
                //设备
                if ((beans.get(i1).getDevice_or_scene_id() + "").equals(startTaskDialogBean.getDeviceId()) && beans.get(i1).getJson_type() == startTaskDialogBean.getJson_type()) {
                    beans.get(i1).setVal(order);
                    beans.get(i1).setDetail(startTaskDialogBean.getDetail());
                    LogUtils.e("fancycydetail1:" + beans.get(i1).getDetail());

                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                list.addAll((List<ScenceStartTaskBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
