package com.suomate.dabaiserver.activity.configsetting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.DeviceListAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.ReadJson;
import com.suomate.dabaiserver.utils.config.ContentConfig;
import com.suomate.dabaiserver.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceListActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tb)
    TitleBar titleBar;
    private DeviceListAdapter adapter;
    private String strTitle;
    private List<ReadJson.AlldeviceBean.DataBean> switchList;
    private List<ReadJson.AlldeviceBean.DataBean> dimmingList;
    private List<ReadJson.AlldeviceBean.DataBean> panelList;
    private List<ReadJson.AlldeviceBean.DataBean> colouredLightsList;
    private List<ReadJson.AlldeviceBean.DataBean> curtainList;
    private List<ReadJson.AlldeviceBean.DataBean> extendedList;
    private List<ReadJson.AlldeviceBean.DataBean> ioList;
    private int type;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        getData();
        event();

    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        switchList = (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("switchList");
        dimmingList = (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("dimmingList");
        panelList = (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("panelList");
        colouredLightsList = (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("colouredLightsList");
        extendedList = (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("extendedList");
        ioList = (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("ioList");
        curtainList= (List<ReadJson.AlldeviceBean.DataBean>) bundle.getSerializable("curtainList");
        type = bundle.getInt("type", 0);
        if (switchList != null) {//开关执行模块
            strTitle = "开关执行模块";
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < switchList.size(); i++) {
                if (ContentConfig.SERIAL.SWITCH8.equals(switchList.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH8_OLD.equals(switchList.get(i).getSerial().trim())) {
                    switchList.get(i).setName("8路16A开关执行模块");
                } else if (ContentConfig.SERIAL.SWITCH4.equals(switchList.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH4_OLD.equals(switchList.get(i).getSerial().trim())) {
                    switchList.get(i).setName("4路16A开关执行模块");
                } else if (ContentConfig.SERIAL.SWITCH4_CURTAIN.equals(switchList.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH4_CURTAIN_OLD.equals(switchList.get(i).getSerial().trim())) {
                    switchList.get(i).setName("4路弱电开关执行模块");
                } else if (ContentConfig.SERIAL.SWITCH485_CURTAIN.equals(switchList.get(i).getSerial().trim())) {
                    switchList.get(i).setName("窗帘485模块");

                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, switchList);
        } else if (dimmingList != null) {//调光执行模块
            strTitle = "调光执行模块";
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < dimmingList.size(); i++) {
                if (ContentConfig.SERIAL.DIMMING2.equals(dimmingList.get(i).getSerial().trim()) || ContentConfig.SERIAL.DIMMING2_OLD.equals(dimmingList.get(i).getSerial().trim())) {
                    dimmingList.get(i).setName("2路1A照明调光模块");
                } else if (ContentConfig.SERIAL.DIMMING4.equals(dimmingList.get(i).getSerial().trim()) || ContentConfig.SERIAL.DIMMING4_OLD.equals(dimmingList.get(i).getSerial().trim())) {
                    dimmingList.get(i).setName("4路16A开关执行模块");
                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, dimmingList);
        } else if (panelList != null) {//面板
            strTitle = "面板";
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < panelList.size(); i++) {
                if (ContentConfig.SERIAL.PANEL.equals(panelList.get(i).getSerial().trim()) || ContentConfig.SERIAL.PANEL_OLD.equals(panelList.get(i).getSerial().trim())) {
                    panelList.get(i).setName("面板模块");
                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, panelList);

        } else if (colouredLightsList != null) {
            strTitle = "彩灯控制模块";
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < colouredLightsList.size(); i++) {
                if (ContentConfig.SERIAL.COLORFUL_LIGHT.equals(colouredLightsList.get(i).getSerial().trim()) || ContentConfig.SERIAL.COLORFUL_LIGHT_OLD.equals(colouredLightsList.get(i).getSerial().trim())) {
                    colouredLightsList.get(i).setName("彩灯控制模块");
                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, colouredLightsList);
        } else if (extendedList != null) {//扩展模块
            strTitle = "扩展模块";
            titleBar.setTextTitle(strTitle);
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < extendedList.size(); i++) {
                if (ContentConfig.SERIAL.EXTENDED_XINFEN.equals(extendedList.get(i).getSerial().trim())) {
                    extendedList.get(i).setName("新风模块");
                }else if( ContentConfig.SERIAL.EXTENDED_DINUAN.equals(extendedList.get(i).getSerial().trim())){
                    extendedList.get(i).setName("地暖模块");
                }else if(ContentConfig.SERIAL.ELECTRICITY.equals(extendedList.get(i).getSerial().trim()) || ContentConfig.SERIAL.ELECTRICITY_OLD.equals(extendedList.get(i).getSerial().trim())){
                    extendedList.get(i).setName("电量模块");
                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, extendedList);
        } else if (ioList != null) {
            strTitle = "io控制模块";
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < ioList.size(); i++) {
                if (ContentConfig.SERIAL.IO.equals(ioList.get(i).getSerial().trim()) || ContentConfig.SERIAL.IO_OLD.equals(ioList.get(i).getSerial().trim())) {
                    ioList.get(i).setName("io控制模块");
                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, ioList);

        }else if(curtainList!=null){
            strTitle = "窗帘控制模块";
            titleBar.setTextTitle(strTitle);
            for (int i = 0; i < curtainList.size(); i++) {
                if (ContentConfig.SERIAL.SWITCH4_CURTAIN.equals(curtainList.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH4_CURTAIN_OLD.equals(curtainList.get(i).getSerial().trim())) {
                    curtainList.get(i).setName("窗帘控制模块");
                }
            }
            adapter = new DeviceListAdapter(R.layout.item_device_list, ioList);
        }
//        if (TextUtils.isEmpty(strTitle)) {
//            titleBar.setTextTitle(strTitle);
//        }
        recycler.setAdapter(adapter);
    }

    private void event() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putInt("type",type);
                switch (type) {
                    case ContentConfig.DEVICETYPE.SWITCH:
                        bundle.putString("title",switchList.get(position).getName());
                        bundle.putString("id",switchList.get(position).getId()+"");
                        bundle.putString("serial",switchList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);
//                        startActivity(new Intent(DeviceListActivity.this,ConfigSingleDeviceActivity.class));
                        break;
                    case ContentConfig.DEVICETYPE.DIMMING:
                        bundle.putString("title",dimmingList.get(position).getName());
                        bundle.putString("id",dimmingList.get(position).getId()+"");
                        bundle.putString("serial",dimmingList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);

                        break;
                    case ContentConfig.DEVICETYPE.PANEL:
                        bundle.putString("title",panelList.get(position).getName());
                        bundle.putString("id",panelList.get(position).getId()+"");
                        bundle.putString("serial",panelList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);

                        break;
                    case ContentConfig.DEVICETYPE.COLOREDLIGHT:
                        bundle.putString("title",colouredLightsList.get(position).getName());
                        bundle.putString("id",colouredLightsList.get(position).getId()+"");
                        bundle.putString("serial",colouredLightsList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);
                        break;
                    case ContentConfig.DEVICETYPE.EXTENDED:
                        bundle.putString("title",extendedList.get(position).getName());
                        bundle.putString("id",extendedList.get(position).getId()+"");
                        bundle.putString("serial",extendedList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);
                        break;
                    case ContentConfig.DEVICETYPE.CURTAIN:
                        bundle.putString("title",colouredLightsList.get(position).getName());
                        bundle.putString("id",colouredLightsList.get(position).getId()+"");
                        bundle.putString("serial",colouredLightsList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);

                        break;
                    case ContentConfig.DEVICETYPE.IO:
                        bundle.putString("title",ioList.get(position).getName());
                        bundle.putString("id",ioList.get(position).getId()+"");
                        bundle.putString("serial",ioList.get(position).getSerial().trim());
                        startActivity(ConfigSingleDeviceActivity.class,bundle);
                        break;
                }
            }
        });
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_device_list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
