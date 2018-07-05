package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.AddDeviceAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.DeviceAddBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddDeviceActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<DeviceAddBean> list = new ArrayList<>();
    private String device_icon, control_type, main_engine_id, type, area_id, classify_id, device_name, port, address, search_version, is_thirdly, device_background_im;
    private AddDeviceAdapter adapter;
    public static final int REQUEST_PANEL_IO = 100, REQUEST_PANEL_INTELLIGENT = 101;

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_device;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddDeviceAdapter(R.layout.item_add_device, list);
        recycler.setAdapter(adapter);
        getData();
        bindEvent();
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getControl_type(position);
            }
        });
    }

    private void getControl_type(int position) {

        //开关灯 switchLight
        if (Content.SERIAL.SWITCH8.equals(type) || Content.SERIAL.SWITCH8_OLD.equals(type) || Content.SERIAL.SWITCH4.equals(type) || Content.SERIAL.SWITCH4_OLD.equals(type)) {
            switch (position) {
                case 0:
                    control_type = "switchLight";
                    break;
                case 1:
                    control_type = "electricDoor";
                    break;
                case 2:
                    control_type = "electromagneticlock";
                    break;
            }

            //调光 dimmerLight
        } else if (Content.SERIAL.DIMMING4.equals(type) || Content.SERIAL.DIMMING4_OLD.equals(type) || Content.SERIAL.DIMMING2.equals(type) || Content.SERIAL.DIMMING2_OLD.equals(type)) {
            control_type = "dimmerLight";
            //彩灯 colorDimmerLight
        } else if (Content.SERIAL.COLORFUL_LIGHT.equals(type) || Content.SERIAL.COLORFUL_LIGHT_OLD.equals(type)) {
            control_type = "colorDimmerLight";
            //新风 newWind
        } else if (Content.SERIAL.EXTENDED_XINFEN.equals(type)) {
            control_type = "newWind";
            //地暖 floorHeating
        } else if (Content.SERIAL.EXTENDED_DINUAN.equals(type)) {
            control_type = "floorHeating";
            //窗帘  窗帘485  单轨、双轨
        } else if (Content.SERIAL.SWITCH4_CURTAIN.equals(type) || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(type) || Content.SERIAL.SWITCH485_CURTAIN.equals(type)) {
            switch (position) {
                case 0:
                    control_type = "curtains";
                    break;
                case 1:
                    control_type = "windowCurtains";
                    break;
            }

            //io 模块
        } else if (Content.SERIAL.IO.equals(type) || Content.SERIAL.IO_OLD.equals(type)) {

            switch (position) {
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    startActivityForResult(PanelNumberSelectActivity.class, bundle, REQUEST_PANEL_IO);
                    break;
                case 1:
                    control_type = "humanFeeling";
                    break;
                case 2:
                    control_type = "gas";
                    break;
                case 3:
                    control_type = "smokeFeeling";
                    break;
            }
            //智能面板
        } else if (Content.SERIAL.PANEL.equals(type) || Content.SERIAL.PANEL_OLD.equals(type)) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", 2);
            startActivityForResult(PanelNumberSelectActivity.class, bundle, REQUEST_PANEL_INTELLIGENT);
        } else if (Content.SERIAL.ELECTRICITY.equals(type) || Content.SERIAL.ELECTRICITY_OLD.equals(type)) {
            control_type = "electricityConversion";
        }
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        area_id = bundle.getString("area_id");
        classify_id = bundle.getString("classify_id");
        device_name = bundle.getString("device_name");
        port = bundle.getString("port");
        main_engine_id = bundle.getString("main_engine_id");
        device_icon = bundle.getString("device_icon");
        type = bundle.getString("type");
        search_version = bundle.getString("search_version");
        is_thirdly = bundle.getString("is_thirdly");
        address = bundle.getString("address");
        device_background_im = bundle.getString("device_background_im");

        //开关灯 switchLight
        if (Content.SERIAL.SWITCH8.equals(type) || Content.SERIAL.SWITCH8_OLD.equals(type) || Content.SERIAL.SWITCH4.equals(type) || Content.SERIAL.SWITCH4_OLD.equals(type)) {
            list.add(new DeviceAddBean("开光灯", ""));
            list.add(new DeviceAddBean("车库门", ""));
            list.add(new DeviceAddBean("电磁锁", ""));
            //调光 dimmerLight
        } else if (Content.SERIAL.DIMMING4.equals(type) || Content.SERIAL.DIMMING4_OLD.equals(type) || Content.SERIAL.DIMMING2.equals(type) || Content.SERIAL.DIMMING2_OLD.equals(type)) {
            list.add(new DeviceAddBean("调光灯", ""));
            //彩灯 colorDimmerLight
        } else if (Content.SERIAL.COLORFUL_LIGHT.equals(type) || Content.SERIAL.COLORFUL_LIGHT_OLD.equals(type)) {
            list.add(new DeviceAddBean("彩光灯", ""));
            //新风 newWind
        } else if (Content.SERIAL.EXTENDED_XINFEN.equals(type)) {
            list.add(new DeviceAddBean("新风", ""));
            //地暖 floorHeating
        } else if (Content.SERIAL.EXTENDED_DINUAN.equals(type)) {
            list.add(new DeviceAddBean("地暖", ""));

            //窗帘  窗帘485  单轨、双轨
        } else if (Content.SERIAL.SWITCH4_CURTAIN.equals(type) || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(type) || Content.SERIAL.SWITCH485_CURTAIN.equals(type)) {
//            control_type="dimmerLight";
            list.add(new DeviceAddBean("单轨道窗帘", ""));
            list.add(new DeviceAddBean("双轨道窗帘", ""));

            //io 模块
        } else if (Content.SERIAL.IO.equals(type) || Content.SERIAL.IO_OLD.equals(type)) {
            list.add(new DeviceAddBean("io面板", ""));
            list.add(new DeviceAddBean("人感", ""));
            list.add(new DeviceAddBean("燃气报警", ""));
            list.add(new DeviceAddBean("烟雾报警", ""));
            //智能面板
        } else if (Content.SERIAL.PANEL.equals(type) || Content.SERIAL.PANEL_OLD.equals(type)) {
            list.add(new DeviceAddBean("智能面板", ""));
            //电量
        } else if (Content.SERIAL.ELECTRICITY.equals(type) || Content.SERIAL.ELECTRICITY_OLD.equals(type)) {
            list.add(new DeviceAddBean("电量", ""));
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                requestAdd();
                break;
        }
    }

    private void requestAdd() {
        AbstractRequest request = buildRequest(UrlUtils.DEVICE_ADD, Content.STRING_TYPE, RequestMethod.POST, null);
        LogUtils.e(TAG, "guid:" + getGuid());
        request.add("guid", getGuid());
        request.add("area_id", area_id);
        request.add("classify_id", classify_id);
        request.add("device_name", device_name);
        request.add("port", port);
        request.add("main_engine_id", main_engine_id);
        //乱填的
        request.add("device_icon", "http://101.201.50.1:808");
        request.add("type", type);
        request.add("search_version", "0");//
        request.add("show_version", "无用");//无用

        request.add("is_thirdly", "0");
        request.add("address", address);
        request.add("device_background_im", "");//
        request.add("panel_number", "");//
        request.add("control_type", control_type);//
        LogUtils.e(TAG, control_type);
        executeNetwork(1, "请稍后", request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        finish();
        showToast("添加设备成功！");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PANEL_IO:
                    control_type = data.getIntExtra("panel_number", 0) + "";
                    break;
                case REQUEST_PANEL_INTELLIGENT:
                    control_type = data.getIntExtra("panel_number", 0) + "";
                    break;
            }
        }
    }
}
