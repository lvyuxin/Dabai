package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.widget.TitleBar;

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
    private String title, main_engine_id, serial, area_id, classify_id, device_name, port, address;
    public static final int REQUEST_CODE_AREA = 101;
    public static final int REQUEST_CODE_CLASSIFY = 102;
    public static final int REQUEST_CODE_NOMINATE = 103;
    public static final int REQUEST_CODE_ICON = 104;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        getData();
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        main_engine_id = bundle.getString("id");
        serial = bundle.getString("serial");
        port = bundle.getString("port");
        tb.setTextTitle(title);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_config_add_device;
    }

    @OnClick({R.id.rl_area, R.id.rl_classification, R.id.rl_niminate, R.id.rl_icon, R.id.save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_area:
                startActivityForResult(AreaSelectListActivity.class, null, REQUEST_CODE_AREA);
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
            case R.id.save_btn:
                Bundle bundle1 = new Bundle();
                bundle1.putString("area_id",area_id);
                bundle1.putString("classify_id",classify_id);
                bundle1.putString("device_name",device_name);
                bundle1.putString("port",port);
                bundle1.putString("main_engine_id",main_engine_id);
                bundle1.putString("device_icon","");
                bundle1.putString("type",serial);
                bundle1.putString("search_version","");
                //0不是1是
                bundle1.putString("is_thirdly","0");
                bundle1.putString("address",getAddress());
                bundle1.putString("device_background_im","");

                startActivity(AddDeviceActivity.class, bundle1);
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_AREA:
                if (RESULT_OK == resultCode) {
                    area_id = data.getStringExtra("areaId");
                    LogUtils.e(TAG, "areaid" + area_id);
                    tvArea.setText(data.getStringExtra("areaName"));
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
