package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.FunctionDeviceAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.FunctionDeviceListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.CommonMethod;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

public class FuntionDeviceListActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private String classify_id;
    private List<FunctionDeviceListBean> list=new ArrayList<>();
    private FunctionDeviceAdapter adapter;
    public static final int TYPE_LIGHT = 4, TYPE_DIMMER = 5, TYPE_FRESHAIR = 6, TYPE_DINUAN = 7,TYPE_CURTAIN=8;
    private String mDiNuanOpenPort;
    @Override
    protected int bindLayout() {
        return R.layout.activity_funtion_device_list;
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        Bundle bundle = getIntent().getExtras();
        classify_id = bundle.getString("classify_id");
        tb.setTextTitle(bundle.getString("name"));
        adapter=new FunctionDeviceAdapter(R.layout.item_area_device,list);
        recycler.setAdapter(adapter);
        requestData();
        bindevent();
    }

    private void bindevent() {

        //点击开关按钮
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FunctionDeviceListBean deviceEntity = list.get(position);
                switch (view.getId()) {
                    case R.id.switch_btn:
                        //4路电灯和8路电灯
                        if (Content.SERIAL.SWITCH8.equals(deviceEntity.getType().trim()) || Content.SERIAL.SWITCH8_OLD.equals(deviceEntity.getType().trim())
                                || Content.SERIAL.SWITCH4.equals(deviceEntity.getType().trim()) || Content.SERIAL.SWITCH4_OLD.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_LIGHT);
                            //调光模块
                        } else if (Content.SERIAL.DIMMING4.equals(deviceEntity.getType().trim()) || Content.SERIAL.DIMMING4_OLD.equals(deviceEntity.getType().trim())
                                || Content.SERIAL.DIMMING2.equals(deviceEntity.getType().trim()) || Content.SERIAL.DIMMING2_OLD.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_DIMMER);
                            //新风模块
                        } else if (Content.SERIAL.EXTENDED_XINFEN.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_FRESHAIR);
                            //地暖模块
                        } else if (Content.SERIAL.EXTENDED_DINUAN.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_DINUAN);
                            //窗帘
                        }else if(Content.SERIAL.SWITCH485_CURTAIN.equals(deviceEntity.getType().trim()) || Content.SERIAL.SWITCH4_CURTAIN.equals(deviceEntity.getType().trim())
                                ||Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(deviceEntity.getType().trim())){
                            sendOrder(deviceEntity,TYPE_CURTAIN);
                        }
                        break;
                }
            }
        });
    }

    private void sendOrder(FunctionDeviceListBean deviceEntity, int type) {
        String content = null;
        AbstractRequest request = buildRequest(UrlUtils.OPEN_NORMAL_LIGHT, Content.STRING_TYPE, RequestMethod.GET, null);
        request.add("guid", getGuid() + "");
        request.add("extend", "0");
        request.add("name", deviceEntity.getDevice_name());
        if (deviceEntity.isOpen()) {//状态是开的状态，关的动作
            switch (type) {
                case TYPE_LIGHT:
                    content = ContentStr.Control_Device.CLOSE_LIGHT;
                    break;
                case TYPE_DIMMER:
                    content = ContentStr.Control_Device.CLOSE_DIMMER;
                    break;
                case TYPE_FRESHAIR:
                    content = ContentStr.Control_Device.CLOSE_FRESH_AIR;
                    break;
                case TYPE_DINUAN:
                    switch (deviceEntity.getPort()) {
                        case 1:
                            mDiNuanOpenPort="01;";
                            break;
                        case 2:
                            mDiNuanOpenPort="15;";
                            break;
                        case 3:
                            mDiNuanOpenPort="29;";
                            break;
                        case 4:
                            mDiNuanOpenPort="3D;";
                            break;
                    }
                    content="SET;00000" + mDiNuanOpenPort;
                    break;
                case TYPE_CURTAIN:
                    String  str = "SET;000000";
                    switch (deviceEntity.getPort()) {//100%
                        case 1:
                            content = str + CommonMethod.getHexStringValue(11) + ";" ;
                            break;
                        case 2:
                            content = str + CommonMethod.getHexStringValue(36) + ";";
                            break;
                        case 3:
                            content = str + CommonMethod.getHexStringValue(61) + ";" ;
                            break;
                        case 4:
                            content = str + CommonMethod.getHexStringValue(86) + ";" ;
                            break;

                    }
                    break;
            }
            deviceEntity.setOpen(false);
        } else {//打开的动作
            switch (type) {
                case TYPE_LIGHT:
                    content = ContentStr.Control_Device.OPEN_LIGHT;
                    break;
                case TYPE_DIMMER:
                    content = ContentStr.Control_Device.OPEN_DIMMER;
                    break;
                case TYPE_FRESHAIR:
                    content = ContentStr.Control_Device.OPEN_FRESH_AIR;
                    break;
                case TYPE_DINUAN:
                    switch (deviceEntity.getPort()) {
                        case 1:
                            mDiNuanOpenPort="02;";
                            break;
                        case 2:
                            mDiNuanOpenPort="16;";
                            break;
                        case 3:
                            mDiNuanOpenPort="2A;";
                            break;
                        case 4:
                            mDiNuanOpenPort="3E;";
                            break;
                    }
                    content="SET;00000" + mDiNuanOpenPort;
                    break;
                case TYPE_CURTAIN:
                    String  str = "SET;000000";
                    switch (deviceEntity.getPort()) {//100%
                        case 1:
                            content = str + CommonMethod.getHexStringValue(1) + ";" ;
                            break;
                        case 2:
                            content = str + CommonMethod.getHexStringValue(26) + ";" ;
                            break;
                        case 3:
                            content = str + CommonMethod.getHexStringValue(51) + ";";
                            break;
                        case 4:
                            content = str + CommonMethod.getHexStringValue(76) + ";" ;
                            break;
                    }
                    break;
            }
            deviceEntity.setOpen(true);
        }
        request.add("content", content + deviceEntity.getAddress() + "$0D$0A");
        LogUtils.e(TAG, "guid:" + getGuid() + "  content:" + content + deviceEntity.getAddress() + "$0D$0A" + "  name:" + deviceEntity.getDevice_name());
        executeNetwork(4, holdonMsg, request);
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.FUNCTION_DEVICE_LIST, Content.LIST_TYPE, RequestMethod.POST, FunctionDeviceListBean.class);
        request.add("guid",getGuid());
        request.add("classify_id",classify_id);
        executeNetwork(1,holdonMsg,request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        list.addAll((Collection<? extends FunctionDeviceListBean>) result.getData());
        adapter.notifyDataSetChanged();
    }
}
