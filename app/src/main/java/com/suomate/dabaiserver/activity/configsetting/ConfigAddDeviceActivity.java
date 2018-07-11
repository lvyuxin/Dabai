package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.bean.DeviceInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.utils.DeviceUtils;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.suomate.dabaiserver.widget.dialog.ConfigSelectDialog;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfigAddDeviceActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.device_icon_iv)
    ImageView ivDeviceIcon;
    @BindView(R.id.port_tv)
    TextView tvPort;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_control_type)
    TextView tvControlType;
    private String title, main_engine_id, serial, area_id, classify_id, device_name,device_id, port, address, control_type;
    public static final int REQUEST_CODE_CLASSIFY = 102;
    public static final int REQUEST_CODE_NOMINATE = 103;
    public static final int REQUEST_CODE_ICON = 104, REQUEST_CONTROLTYPE = 105;
    private DeviceInfoBean deviceInfo;
    private ConfigSelectDialog configSelectDialog;
    private List<AreaSelectListBean.DataBean> areaList = new ArrayList<>();
    private int requestType;
    private int panel_number;
    @Override

    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        getData();
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        port = bundle.getString("port");
        main_engine_id = bundle.getString("id");
        serial = bundle.getString("serial");
        tb.setTextTitle(title);
        tvPort.setText("端口" + port);
        title = bundle.getString("title");
        tb.setTextTitle(title);
        if (main_engine_id.length() == 1) {
            tvType.setText("ID00" + main_engine_id + "-D-" + serial);
        } else if (main_engine_id.length() == 2) {
            tvType.setText("ID0" + main_engine_id + "-D-" + serial);
        } else if (main_engine_id.length() == 3) {
            tvType.setText("ID" + main_engine_id + "-D-" + serial);
        }
        requestType = bundle.getInt("type");
        if (requestType == 1) {
        } else if (requestType == 2) {
            deviceInfo = (DeviceInfoBean) bundle.getSerializable("deviceInfo");
            tvName.setText(deviceInfo.getDevice_name());
            tvArea.setText(deviceInfo.getArea_name());
            tvClassify.setText(deviceInfo.getClassify_name());
            tvControlType.setText(DeviceUtils.getControlTypeName(deviceInfo.getControl_type()));
            area_id = deviceInfo.getArea_id() + "";
            classify_id = deviceInfo.getClassify_id() + "";
            device_name = deviceInfo.getDevice_name();
            control_type = deviceInfo.getControl_type();
            device_id=deviceInfo.getDevice_id()+"";
            tvControlType.setText(DeviceUtils.getControlTypeName(control_type));
        }

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_config_add_device;
    }

    @OnClick({R.id.rl_area, R.id.rl_classification, R.id.rl_niminate, R.id.rl_icon, R.id.save_btn, R.id.rl_control_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_area:
                requestAreaData();
                break;
            case R.id.rl_classification:
                startActivityForResult(ClassifyDeviceListActivity.class, null, REQUEST_CODE_CLASSIFY);
                break;
            case R.id.rl_niminate:
                Bundle bundle = new Bundle();
                bundle.putInt("type", Content.TYPE.NOMINATE);
                startActivityForResult(EditNameActivity.class, bundle, REQUEST_CODE_NOMINATE);
                break;
            case R.id.rl_icon:
                startActivityForResult(DeviceIconSelectActivity.class, null, REQUEST_CODE_ICON);
                break;
            case R.id.rl_control_type://类型
                Bundle bundle2 = new Bundle();
                bundle2.putString("type", serial);
                startActivityForResult(AddDeviceActivity.class, bundle2, REQUEST_CONTROLTYPE);
                break;
            case R.id.save_btn:
                if (requestType == 1) {//添加
                    requestAdd();
                } else if (requestType == 2) {//修改
                    requestUpdate();
                }

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
        request.add("type", serial);
        request.add("search_version", "011");//
        request.add("show_version", "无用");//无用
        request.add("is_thirdly", "0");
        request.add("address", getAddress());
        request.add("device_background_im", "");//
        request.add("panel_number", panel_number);//
        request.add("control_type", control_type);
        executeNetwork(1, "请稍后", request);
    }

    private void requestUpdate() {
        AbstractRequest request = buildRequest(UrlUtils.DEVICE_UPDATE, Content.STRING_TYPE, RequestMethod.POST, null);
        request.add("guid", getGuid());
        request.add("area_id", area_id);
        request.add("classify_id", classify_id);
        request.add("device_name", device_name);
        request.add("device_id", device_id);
        request.add("port", port);
        request.add("main_engine_id", main_engine_id);
        //乱填的
        request.add("device_icon", "http://101.201.50.1:808");
        request.add("type", serial);
        request.add("search_version", "011");//
        request.add("show_version", "无用");//无用
        request.add("is_thirdly", "0");
        request.add("address", getAddress());
        request.add("device_background_im", "");//
        request.add("panel_number", panel_number);//
        request.add("control_type", control_type);
        executeNetwork(2, "请稍后", request);
    }


    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                showToast("设备配置成功！");
                finish();
                break;
            case 2:
                showToast("修改设备配置成功！");
                finish();
                break;
            case 3:
                result.getData();
                areaList.addAll((List<AreaSelectListBean.DataBean>) result.getData());
                configSelectDialog = new ConfigSelectDialog(this, R.style.basedialog_style, true, areaList);
                configSelectDialog.setCallBackIml(new CallBackIml() {
                    @Override
                    public void callBack(String id, String name) {
                        super.callBack(id, name);
                        area_id = id;
                        tvArea.setText(name);
                    }
                });
                configSelectDialog.show();
                break;
        }
    }

    public String getAddress() {
        if (serial.equals(Content.SERIAL.PANEL) || Content.SERIAL.PANEL_OLD.equals(serial)) {//智能面板
            address = "{254.251.1." + main_engine_id + "};";
        } else if (serial.equals(Content.SERIAL.SWITCH485_CURTAIN)) {
            address = "{254.0." + main_engine_id + ".1" + "};";
        } else if (serial.equals(Content.SERIAL.EXTENDED_DINUAN)) {
            address = "{254.0." + main_engine_id + ".1" + "};";
        } else if (serial.equals(Content.SERIAL.EXTENDED_XINFEN)) {
            address = "{254.0." + main_engine_id + ".1" + "};";
        } else {
            address = "{254.0." + main_engine_id + "." + port + "};";
        }
        return address;
    }

    private void requestAreaData() {
        AbstractRequest request = buildRequest(UrlUtils.AREA_LIST, Content.LIST_TYPE, RequestMethod.GET, AreaSelectListBean.DataBean.class);
        request.add("guid", getGuid());
        executeNetwork(3, holdonMsg, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CONTROLTYPE:
                if (resultCode == RESULT_OK) {
                    control_type = data.getStringExtra("control_type");
                    tvControlType.setText(data.getStringExtra("control_type_name"));
                } else if (resultCode == 2) {
                    control_type = data.getStringExtra("control_type");
                    panel_number= data.getIntExtra("panel_number",0);
                }
                break;
            case REQUEST_CODE_CLASSIFY:
                if (RESULT_OK == resultCode) {
                    classify_id = data.getStringExtra("classifyId");
                    LogUtils.e(TAG, "classify_id" + classify_id);
                    tvClassify.setText(data.getStringExtra("classifyName"));
                }
                break;
            case REQUEST_CODE_NOMINATE:
                if (RESULT_OK == resultCode) {
                    device_name = data.getStringExtra("name");
                    tvName.setText(device_name);
                }
                break;
            case REQUEST_CODE_ICON:
                if (RESULT_OK == resultCode) {
                    ivDeviceIcon.setImageResource(data.getIntExtra("imgSrc", 0));
                }
                break;
        }
    }


}
