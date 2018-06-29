package com.suomate.dabaiserver.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.configsetting.DeviceListActivity;
import com.suomate.dabaiserver.activity.configsetting.IntelligentHostActivity;
import com.suomate.dabaiserver.adapter.ConfigSettingAdapter;
import com.suomate.dabaiserver.base.fragment.BaseFragment;
import com.suomate.dabaiserver.bean.ConfigSettingBean;
import com.suomate.dabaiserver.bean.ReadJson;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.ContentConfig;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ConfigFragment extends BaseFragment {
    @BindView(R.id.config_recycler)
    RecyclerView recyclerView;
    ReadJson readJson;
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    Unbinder unbinder;
    private List<ReadJson.AlldeviceBean.DataBean> switchList = new ArrayList<>();
    private List<ReadJson.AlldeviceBean.DataBean> dimmingList = new ArrayList<>();
    private List<ReadJson.AlldeviceBean.DataBean> panelList = new ArrayList<>();
    private List<ReadJson.AlldeviceBean.DataBean> colouredLightsList = new ArrayList<>();
    private List<ReadJson.AlldeviceBean.DataBean> extendedList = new ArrayList<>();
    private List<ReadJson.AlldeviceBean.DataBean> ioList = new ArrayList<>();
    //第三方的
    private List<ReadJson.AlldeviceBean.DataBean> environmentBoxList = new ArrayList<>();
    private List<ReadJson.OtherdeviceBean.AirConditionerBean.ListBean> airConditionerBeanList = new ArrayList<>();
    private List<ReadJson.OtherdeviceBean.CameraBean> cameraBeanList = new ArrayList<>();
    private List<ConfigSettingBean> list = new ArrayList<>();
    private ConfigSettingAdapter adapter;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_config;
    }

    @Override
    protected void initViews() {
        super.initViews();
        requestData();
        setTitleBarEvent();
        addNames();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ConfigSettingAdapter(R.layout.item_config_layout, list);
        recyclerView.setAdapter(adapter);
        goDeviceDetail();
    }

    private void goDeviceDetail() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        startActivity(IntelligentHostActivity.class, null);
                        break;
                    case 1:
                        if (switchList.size()>0) {
                            Intent intent=new Intent(getContext(),DeviceListActivity.class);
                            bundle.putSerializable("switchList", (Serializable) switchList);
                            bundle.putInt("type",1);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        break;
                    case 2:
                        if (dimmingList.size()>0) {
                            bundle.putSerializable("dimmingList", (Serializable) dimmingList);
                            bundle.putInt("type",2);
                            startActivity(DeviceListActivity.class, bundle);
                        }

                        break;
                    case 3:
                        if (panelList.size()>0) {
                            bundle.putSerializable("panelList", (Serializable) panelList);
                            bundle.putInt("type",3);
                            startActivity(DeviceListActivity.class, bundle);
                        }

                        break;
                    case 4:
                        if (colouredLightsList.size()>0) {
                            bundle.putSerializable("colouredLightsList", (Serializable) colouredLightsList);
                            bundle.putInt("type",4);
                            startActivity(DeviceListActivity.class, bundle);
                        }
                        break;
                    case 5:
                        if (extendedList.size()>0) {
                            bundle.putSerializable("extendedList", (Serializable) extendedList);
                            bundle.putInt("type",5);
                            startActivity(DeviceListActivity.class, bundle);
                            break;
                        }
                    case 6:
                        if (ioList.size()>0) {
                            bundle.putSerializable("ioList", (Serializable) ioList);
                            bundle.putInt("type",6);
                            startActivity(DeviceListActivity.class, bundle);
                        }
                        break;
                    case 7:
                        bundle.putSerializable("environmentBoxList", (Serializable) environmentBoxList);
                        startActivity(DeviceListActivity.class, bundle);
                        break;
                    case 8:
                        bundle.putSerializable("airConditionerBeanList", (Serializable) airConditionerBeanList);
                        startActivity(DeviceListActivity.class, bundle);
                        break;
                    case 9:
                        bundle.putSerializable("cameraBeanList", (Serializable) cameraBeanList);
                        startActivity(DeviceListActivity.class, bundle);
                        break;
                }
            }
        });
    }

    @Override
    protected void mHandleNoNetwork(int what) {
        super.mHandleNoNetwork(what);
        showToast("无网络，请检查");
    }

    private void addNames() {
        list.add(0, new ConfigSettingBean("智能网关主机", 1));
        list.add(1, new ConfigSettingBean("开光执行模块", 0));
        list.add(2, new ConfigSettingBean("调光模块", 0));
        list.add(3, new ConfigSettingBean("智能面板模块", 0));
        list.add(4, new ConfigSettingBean("彩灯控制模块", 0));
        list.add(5, new ConfigSettingBean("扩展模块", 0));
        list.add(6, new ConfigSettingBean("io模块", 0));
        list.add(7, new ConfigSettingBean("环境盒子", 0));
        list.add(8, new ConfigSettingBean("空调模块", 0));
        list.add(9, new ConfigSettingBean("视屏监控模块", 0));
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.GET_READJSON, ContentConfig.STRING_TYPE, RequestMethod.GET, null);
        request.add("jsonname", "data.json");
        request.add("guid",getGuid());
        executeNetwork(1, "请稍后", request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        String stringRequest = (String) result.getData();

        readJson = JSON.parseObject(stringRequest, ReadJson.class);
        ReadJson.AlldeviceBean alldevice = readJson.getAlldevice();
        ReadJson.OtherdeviceBean threeParty = readJson.getOtherdevice();
        classifyLocalDevice(alldevice);
        classifyThirdPartyDevice(threeParty);
        adapter.notifyDataSetChanged();
    }


    private void classifyLocalDevice(ReadJson.AlldeviceBean alldevice) {
        if (alldevice.getData()!=null) {
        List<ReadJson.AlldeviceBean.DataBean> alldeviceData = alldevice.getData();
        for (int i = 0; i < alldeviceData.size(); i++) {
            //开关执行模块
            if (ContentConfig.SERIAL.SWITCH8.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH8_OLD.equals(alldeviceData.get(i).getSerial().trim())
                    || ContentConfig.SERIAL.SWITCH4.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH4_OLD.equals(alldeviceData.get(i).getSerial().trim())
                    || ContentConfig.SERIAL.SWITCH4_CURTAIN.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.SWITCH4_CURTAIN_OLD.equals(alldeviceData.get(i).getSerial().trim())
                    || ContentConfig.SERIAL.SWITCH485_CURTAIN.equals(alldeviceData.get(i).getSerial().trim())) {
                switchList.add(alldeviceData.get(i));
                //调光执行模块
            } else if (ContentConfig.SERIAL.DIMMING4.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.DIMMING4_OLD.equals(alldeviceData.get(i).getSerial().trim())
                    || ContentConfig.SERIAL.DIMMING2.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.DIMMING2_OLD.equals(alldeviceData.get(i).getSerial().trim())) {
                dimmingList.add(alldeviceData.get(i));
                //彩灯模块
            } else if (ContentConfig.SERIAL.COLORFUL_LIGHT.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.COLORFUL_LIGHT_OLD.equals(alldeviceData.get(i).getSerial().trim())) {
                colouredLightsList.add(alldeviceData.get(i));

                //扩展模块
            } else if (ContentConfig.SERIAL.EXTENDED_XINFEN.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.EXTENDED_DINUAN.equals(alldeviceData.get(i).getSerial().trim())
                    || ContentConfig.SERIAL.ELECTRICITY.equals(alldeviceData.get(i).getSerial().trim())) {
                extendedList.add(alldeviceData.get(i));
                //面板
            } else if (ContentConfig.SERIAL.PANEL.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.PANEL_OLD.equals(alldeviceData.get(i).getSerial().trim())) {
                panelList.add(alldeviceData.get(i));
                //IO模块
            } else if (ContentConfig.SERIAL.IO.equals(alldeviceData.get(i).getSerial().trim()) || ContentConfig.SERIAL.IO_OLD.equals(alldeviceData.get(i).getSerial().trim())) {
                ioList.add(alldeviceData.get(i));
                //视屏监控
            } else if (ContentConfig.SERIAL.MONITOR.equals(alldeviceData.get(i).getSerial().trim())) {

            } else {//环境类的

            }
        }
        list.get(1).setDeviceCount(switchList.size());
        list.get(2).setDeviceCount(dimmingList.size());
        list.get(3).setDeviceCount(panelList.size());
        list.get(4).setDeviceCount(colouredLightsList.size());
        list.get(5).setDeviceCount(extendedList.size());
        list.get(6).setDeviceCount(ioList.size());
        list.get(7).setDeviceCount(environmentBoxList.size());
        }
    }

    private void setTitleBarEvent(){
        titlebar.setBackListener(new TitleBar.CallBack() {
            @Override
            public void clickBack(View back) {
                showToast("点击了左边菜单");
            }
        });

        titlebar.setOnMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                showToast("点击了右边菜单");
            }
        });
    }

    private void classifyThirdPartyDevice(ReadJson.OtherdeviceBean threeParty) {
        if (threeParty.getCamera()!=null) {
            cameraBeanList.addAll(threeParty.getCamera());
        }

        if (threeParty.getAir_conditioner()!=null) {
            airConditionerBeanList.addAll(threeParty.getAir_conditioner().getList());

        }
        list.get(8).setDeviceCount(airConditionerBeanList.size());
        list.get(9).setDeviceCount(cameraBeanList.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
