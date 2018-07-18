package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.ConfigSingleDeviceAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.ConfigSingleDeviceBean;
import com.suomate.dabaiserver.bean.DeviceInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.DeviceUtils;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ConfigSingleDeviceActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tb)
    TitleBar titleBar;
    private int type;
    private String serial, title, id;
    private List<ConfigSingleDeviceBean> list = new ArrayList<>();
    private List<DeviceInfoBean> deviceInfoList = new ArrayList<>();
    private ConfigSingleDeviceAdapter adapter;
    public static final int REQUEST_CODE = 101;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConfigSingleDeviceAdapter(R.layout.item_config_single_device, list);
        getData();
        requestData();
        bindEvent();
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.getDeciceAccessId, Content.LIST_TYPE, RequestMethod.POST, DeviceInfoBean.class);
        request.add("guid", getGuid());
        request.add("main_engine_id", id);
        LogUtils.e("fancyid", id);
        executeNetwork(2, request);
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("serial", serial);
                bundle.putString("port", list.get(position).getPort() + "");

                if (TextUtils.isEmpty(list.get(position).getName())) {//添加设备
                    bundle.putInt("type", 1);
                    bundle.putString("title", "未命名");
                } else {//修改设备
                    bundle.putSerializable("deviceInfo", list.get(position).getDeviceInfoBean());
                    bundle.putInt("type", 2);
                    bundle.putString("title", list.get(position).getName());
                }
                startActivityForResult(ConfigAddDeviceActivity.class, bundle, REQUEST_CODE);
            }
        });

    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 2:
                deviceInfoList.clear();
//                list.clear();
                deviceInfoList.addAll((List<DeviceInfoBean>) result.getData());
                for (int i = 0; i < deviceInfoList.size(); i++) {
                    for (int i1 = 0; i1 < list.size(); i1++) {
                        if (deviceInfoList.get(i).getPort() == list.get(i1).getPort()) {
                            list.get(i1).setDeviceInfoBean(deviceInfoList.get(i));
                            list.get(i1).setName(deviceInfoList.get(i).getArea_name() + "/" + deviceInfoList.get(i).getDevice_name());
                            list.get(i1).setIconType(deviceInfoList.get(i).getDevice_icon());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                requestData();
                break;


        }

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_config_single_device;
    }

    public void getData() {

        View headerView = LayoutInflater.from(this).inflate(R.layout.config_device_header, null);
        ImageView ivHead = headerView.findViewById(R.id.head_iv);
        TextView tvHead = headerView.findViewById(R.id.head_tv);

        adapter.addHeaderView(headerView);
        recycler.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        serial = bundle.getString("serial");
        type = bundle.getInt("type", 0);
        title = bundle.getString("title");
        titleBar.setTextTitle(title);
        tvHead.setText(DeviceUtils.getDeviceType(id, serial));
        switch (type) {
            case Content.DEVICETYPE.SWITCH://开关执行模块
                if (Content.SERIAL.SWITCH4.equals(serial) || Content.SERIAL.SWITCH4_OLD.equals(serial)) {
                    getDevicePorts(4);
                    ivHead.setImageResource(R.mipmap.icon_four_sixteen_lu);
                } else if (Content.SERIAL.SWITCH8.equals(serial) || Content.SERIAL.SWITCH8_OLD.equals(serial)) {
                    getDevicePorts(8);
                    ivHead.setImageResource(R.mipmap.icon_eight_sixteen);
                    //四路弱点执行模块 6个
                } else if (Content.SERIAL.SWITCH4_CURTAIN.equals(serial) || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(serial)) {
                    getDevicePorts(4);

                    //窗帘模块 port 4
                } else if (Content.SERIAL.SWITCH485_CURTAIN.equals(serial)) {
                    getDevicePorts(4);
                }
                break;
            case Content.DEVICETYPE.DIMMING://调光执行模块
                //4路16A开关执行模块
                if (Content.SERIAL.DIMMING2.equals(serial) || Content.SERIAL.DIMMING2_OLD.equals(serial)) {
                    getDevicePorts(2);
                    //4路16A开关执行模块
                } else if (Content.SERIAL.DIMMING4.equals(serial) || Content.SERIAL.DIMMING4_OLD.equals(serial)) {
                    getDevicePorts(4);
                }
                break;
            case Content.DEVICETYPE.PANEL://面板
                if (Content.SERIAL.PANEL.equals(serial) || Content.SERIAL.PANEL_OLD.equals(serial)) {
                    getDevicePorts(6);
                }
                break;
            case Content.DEVICETYPE.COLOREDLIGHT://彩灯
                if (Content.SERIAL.COLORFUL_LIGHT.equals(serial) || Content.SERIAL.COLORFUL_LIGHT_OLD.equals(serial)) {
                    getDevicePorts(8);
                }
                break;
            case Content.DEVICETYPE.EXTENDED:
                if (Content.SERIAL.EXTENDED_XINFEN.equals(serial)) {
                    getDevicePorts(1);
                } else if (Content.SERIAL.EXTENDED_DINUAN.equals(serial)) {
                    getDevicePorts(4);
                } else if (Content.SERIAL.ELECTRICITY.equals(serial)) {
                    getDevicePorts(1);
                }
                break;
            case Content.DEVICETYPE.IO:
                if (Content.SERIAL.IO.equals(serial) || Content.SERIAL.IO_OLD.equals(serial)) {
                    getDevicePorts(6);
                }
                break;
            case Content.DEVICETYPE.CURTAIN:
                if (Content.SERIAL.SWITCH4_CURTAIN.equals(serial) || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(serial)) {
                    getDevicePorts(4);
                }
                break;
        }
    }

    private void getDevicePorts(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new ConfigSingleDeviceBean(i + 1));
        }
    }

}
