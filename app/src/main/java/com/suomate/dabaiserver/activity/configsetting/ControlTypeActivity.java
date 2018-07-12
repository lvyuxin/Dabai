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
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ControlTypeActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<DeviceAddBean> list = new ArrayList<>();
    private String device_icon, control_type, main_engine_id, type, area_id, classify_id, device_name, port, address, search_version, is_thirdly, device_background_im, control_type_name;
    private AddDeviceAdapter adapter;
    public static final int REQUEST_PANEL_IO = 100, REQUEST_PANEL_INTELLIGENT = 101;
    private int panel_number;


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

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        //开关灯 switchLight
        if (Content.SERIAL.SWITCH8.equals(type) || Content.SERIAL.SWITCH8_OLD.equals(type) || Content.SERIAL.SWITCH4.equals(type) || Content.SERIAL.SWITCH4_OLD.equals(type)) {
            list.add(new DeviceAddBean(ContentStr.Control_type_name.switchLight, ""));
            list.add(new DeviceAddBean(ContentStr.Control_type_name.electricDoor, ""));
            list.add(new DeviceAddBean(ContentStr.Control_type_name.electromagneticlock, ""));
            //调光 dimmerLight
        } else if (Content.SERIAL.DIMMING4.equals(type) || Content.SERIAL.DIMMING4_OLD.equals(type) || Content.SERIAL.DIMMING2.equals(type) || Content.SERIAL.DIMMING2_OLD.equals(type)) {
            list.add(new DeviceAddBean(ContentStr.Control_type_name.dimmerLight, ""));
            //彩灯 colorDimmerLight
        } else if (Content.SERIAL.COLORFUL_LIGHT.equals(type) || Content.SERIAL.COLORFUL_LIGHT_OLD.equals(type)) {
            list.add(new DeviceAddBean(ContentStr.Control_type_name.colorDimmerLight, ""));
            //新风 newWind
        } else if (Content.SERIAL.EXTENDED_XINFEN.equals(type)) {
            list.add(new DeviceAddBean(ContentStr.Control_type_name.newWind, ""));
            //地暖 floorHeating
        } else if (Content.SERIAL.EXTENDED_DINUAN.equals(type)) {
            list.add(new DeviceAddBean(ContentStr.Control_type_name.floorHeating, ""));

            //窗帘  窗帘485  单轨、双轨
        } else if (Content.SERIAL.SWITCH4_CURTAIN.equals(type) || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(type) || Content.SERIAL.SWITCH485_CURTAIN.equals(type)) {
//            control_type="dimmerLight";
            list.add(new DeviceAddBean(ContentStr.Control_type_name.curtains, ""));
            list.add(new DeviceAddBean(ContentStr.Control_type_name.windowCurtains, ""));

            //io 模块
        } else if (Content.SERIAL.IO.equals(type) || Content.SERIAL.IO_OLD.equals(type)) {
            list.add(new DeviceAddBean("io面板", ""));
            list.add(new DeviceAddBean(ContentStr.Control_type_name.humanFeeling, ""));
            list.add(new DeviceAddBean(ContentStr.Control_type_name.gas, ""));
            list.add(new DeviceAddBean(ContentStr.Control_type_name.smokeFeeling, ""));
            //智能面板
        } else if (Content.SERIAL.PANEL.equals(type) || Content.SERIAL.PANEL_OLD.equals(type)) {
            list.add(new DeviceAddBean("智能面板", ""));
            //电量
        } else if (Content.SERIAL.ELECTRICITY.equals(type) || Content.SERIAL.ELECTRICITY_OLD.equals(type)) {
            list.add(new DeviceAddBean(ContentStr.Control_type_name.electricityConversion, ""));
        }
        adapter.notifyDataSetChanged();
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getControl_type(position);
                if (control_type.equals(ContentStr.Control_type.panel)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    startActivityForResult(PanelNumberSelectActivity.class, bundle, REQUEST_PANEL_IO);
                } else if (control_type.equals(ContentStr.Control_type.intelligentPanel)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 2);
                    startActivityForResult(PanelNumberSelectActivity.class, bundle, REQUEST_PANEL_INTELLIGENT);
                } else {
                    Intent intent = getIntent();
                    intent.putExtra("control_type", control_type);
                    intent.putExtra("control_type_name", list.get(position).getName());
                    setResult(RESULT_OK, intent);
                    finish();

                }
            }
        });
    }

    private void getControl_type(int position) {

        //开关灯 switchLight
        if (Content.SERIAL.SWITCH8.equals(type) || Content.SERIAL.SWITCH8_OLD.equals(type) || Content.SERIAL.SWITCH4.equals(type) || Content.SERIAL.SWITCH4_OLD.equals(type)) {
            switch (position) {
                case 0:
                    control_type = ContentStr.Control_type.switchLight;
                    break;
                case 1:
                    control_type = ContentStr.Control_type.electricDoor;
                    break;
                case 2:
                    control_type = ContentStr.Control_type.electromagneticlock;
                    break;
            }

            //调光 dimmerLight
        } else if (Content.SERIAL.DIMMING4.equals(type) || Content.SERIAL.DIMMING4_OLD.equals(type) || Content.SERIAL.DIMMING2.equals(type) || Content.SERIAL.DIMMING2_OLD.equals(type)) {
            control_type = ContentStr.Control_type.dimmerLight;
            //彩灯 colorDimmerLight
        } else if (Content.SERIAL.COLORFUL_LIGHT.equals(type) || Content.SERIAL.COLORFUL_LIGHT_OLD.equals(type)) {
            control_type = ContentStr.Control_type.colorDimmerLight;
            //新风 newWind
        } else if (Content.SERIAL.EXTENDED_XINFEN.equals(type)) {
            control_type = ContentStr.Control_type.newWind;
            //地暖 floorHeating
        } else if (Content.SERIAL.EXTENDED_DINUAN.equals(type)) {
            control_type = ContentStr.Control_type.floorHeating;
            //窗帘  窗帘485  单轨、双轨
        } else if (Content.SERIAL.SWITCH4_CURTAIN.equals(type) || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(type) || Content.SERIAL.SWITCH485_CURTAIN.equals(type)) {
            switch (position) {
                case 0:
                    control_type = ContentStr.Control_type.curtains;
                    break;
                case 1:
                    control_type = ContentStr.Control_type.windowCurtains;
                    break;
            }

            //io 模块
        } else if (Content.SERIAL.IO.equals(type) || Content.SERIAL.IO_OLD.equals(type)) {

            switch (position) {
                case 0:
                    control_type = ContentStr.Control_type.panel;
                    break;
                case 1:
                    control_type = ContentStr.Control_type.humanFeeling;
                    break;
                case 2:
                    control_type = ContentStr.Control_type.gas;
                    break;
                case 3:
                    control_type = ContentStr.Control_type.smokeFeeling;
                    break;
            }
            //智能面板
        } else if (Content.SERIAL.PANEL.equals(type) || Content.SERIAL.PANEL_OLD.equals(type)) {
            control_type = ContentStr.Control_type.intelligentPanel;

        } else if (Content.SERIAL.ELECTRICITY.equals(type) || Content.SERIAL.ELECTRICITY_OLD.equals(type)) {
            control_type = ContentStr.Control_type.electricityConversion;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();
        if (resultCode == RESULT_OK) {
            panel_number = data.getIntExtra("panel_number", 0);
            control_type = data.getStringExtra("control_type");
            control_type_name = data.getStringExtra("control_type_name");
            LogUtils.e("fancycy:" + control_type + "  " + control_type_name + "   " + panel_number);
            intent.putExtra("control_type", control_type);
            intent.putExtra("panel_number", panel_number);
            intent.putExtra("control_type_name", control_type_name);
            setResult(2, intent);
            finish();
        }
    }
}
