package com.suomate.dabaiserver.activity.configsetting;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.ConfigSingleDeviceAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.ConfigSingleDeviceBean;
import com.suomate.dabaiserver.bean.Result;
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
    private ConfigSingleDeviceAdapter adapter;
    private int curPosition;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        getData();
        adapter = new ConfigSingleDeviceAdapter(R.layout.item_config_single_device, list);
        View headerView = LayoutInflater.from(this).inflate(R.layout.config_device_header, null);
        adapter.addHeaderView(headerView);
        recycler.setAdapter(adapter);
        bindEvent();
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                curPosition=position;
                AbstractRequest request = buildRequest(UrlUtils.CHECK_DEVICE_PORT, Content.STRING_TYPE, RequestMethod.POST, null);
                request.add("guid", getGuid());
                request.add("main_engine_id", id);
                request.add("port", list.get(position).getPort() + "");
                executeNetwork(1, holdonMsg, request);
            }
        });
    }


    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("id", id);
                bundle.putString("serial", serial);
                bundle.putString("port", list.get(curPosition).getPort() + "");
                startActivity(ConfigAddDeviceActivity.class, bundle);
                break;
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_config_single_device;
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        serial = bundle.getString("serial");
        type = bundle.getInt("type", 0);
        title = bundle.getString("title");
        titleBar.setTextTitle(title);
        switch (type) {
            case Content.DEVICETYPE.SWITCH://开关执行模块
                if (Content.SERIAL.SWITCH4.equals(serial) || Content.SERIAL.SWITCH4_OLD.equals(serial)) {
                    getDevicePorts(4);
                } else if (Content.SERIAL.SWITCH8.equals(serial) || Content.SERIAL.SWITCH8_OLD.equals(serial)) {
                    getDevicePorts(8);
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
