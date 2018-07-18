package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceIconSelectActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    private String sportLight, dimming, lightwith, normallight, colorfullight, hanglight;
    private String iconType;
    private int imgSrc;
    Intent intent;

    @Override
    protected int bindLayout() {
        return R.layout.activity_device_icon_select;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        super.initViews(savedInstanceState);
        intent = getIntent();
        bindEvent();
    }

    private void bindEvent() {
        tb.setOnTitleBackListener(new TitleBar.TitleBackListener() {
            @Override
            public void ontitleBack() {
                finish();
            }
        });
    }

    @OnClick({R.id.iv_sport_light, R.id.iv_dimming, R.id.iv_lightwith, R.id.iv_normal_light, R.id.iv_colorful_light, R.id.iv_hanging_light,
            R.id.iv_curtain, R.id.iv_double_curtain, R.id.iv_single_curtain,
            R.id.iv_sensor1, R.id.iv_gas, R.id.iv_sensor2, R.id.iv_smoke_alarm,
            R.id.iv_co2, R.id.iv_humidity, R.id.iv_pm25, R.id.iv_tempture, R.id.iv_voc,
            R.id.iv_panel1, R.id.iv_panel2, R.id.iv_panel3, R.id.iv_panel4, R.id.iv_panel5, R.id.iv_panel6,
            R.id.iv_ground_warm, R.id.iv_fresh_air, R.id.iv_eletricity, R.id.iv_monitor, R.id.iv_air_condition
    })
    public void onClick(View view) {
//        showToast("点了");
        switch (view.getId()) {
            //1、照明 :ic_shoot_light(射灯)、ic_dimming_light(调光)、
            // ic_lamp_with(灯带)、ic_switch_light、ic_normal_dimming_light(调光)、ic_droplight(吊灯)
            case R.id.iv_sport_light:
                iconType = ContentStr.IconType.ic_shoot_light;
                break;
            case R.id.iv_dimming:
                iconType = ContentStr.IconType.ic_dimming_light;
                break;
            case R.id.iv_normal_light:
                iconType = ContentStr.IconType.ic_switch_light;
                break;
            case R.id.iv_colorful_light:
                iconType = ContentStr.IconType.ic_normal_dimming_light;
                break;
            case R.id.iv_hanging_light:
                iconType = ContentStr.IconType.ic_droplight;
                break;
            case R.id.iv_lightwith:
                iconType = ContentStr.IconType.ic_lamp_with;
                break;
            //2、窗帘：ic_normal_curtain、ic_single_curtian,ic_double_curtains
            case R.id.iv_curtain:
                iconType = ContentStr.IconType.ic_normal_curtain;
                break;
            case R.id.iv_double_curtain:
                iconType = ContentStr.IconType.ic_double_curtains;
                break;
            case R.id.iv_single_curtain:
                iconType = ContentStr.IconType.ic_double_curtains;
                break;
            //3、安防 ic_sensor ,ic_gas_alarm(燃气报警)、ic_sensor_normal、ic_smoke_alarm(烟雾报警)
            case R.id.iv_sensor1:
                iconType = ContentStr.IconType.ic_sensor;
                break;
            case R.id.iv_gas:
                iconType = ContentStr.IconType.ic_gas_alarm;
                break;
            case R.id.iv_sensor2:
                iconType = ContentStr.IconType.ic_sensor_normal;
                break;
            case R.id.iv_smoke_alarm:
                iconType = ContentStr.IconType.ic_smoke_alarm;
                break;
            //4、环境 ic_co2,ic_humidity（湿度）,ic_pm25,ic_tempture,ic_voc
            case R.id.iv_co2:
                iconType = ContentStr.IconType.ic_co2;
                break;
            case R.id.iv_humidity:
                iconType = ContentStr.IconType.ic_humidity;
                break;
            case R.id.iv_pm25:
                iconType = ContentStr.IconType.ic_pm25;
                break;
            case R.id.iv_tempture:
                iconType = ContentStr.IconType.ic_tempture;
                break;
            case R.id.iv_voc:
                iconType = ContentStr.IconType.ic_voc;
                break;
            //面板        //5、面板 ic_panel_key1,ic_panel_key2,ic_panel_key3,ic_panel_key4,ic_panel_key5,ic_panel_key6

            case R.id.iv_panel1:
                iconType = ContentStr.IconType.ic_panel_key1;
                break;
            case R.id.iv_panel2:
                iconType = ContentStr.IconType.ic_panel_key2;
                break;
            case R.id.iv_panel3:
                iconType = ContentStr.IconType.ic_panel_key3;
                break;
            case R.id.iv_panel4:
                iconType = ContentStr.IconType.ic_panel_key4;
                break;
            case R.id.iv_panel5:
                iconType = ContentStr.IconType.ic_panel_key5;
                break;
            case R.id.iv_panel6:
                iconType = ContentStr.IconType.ic_panel_key6;
                break;
            //6、地暖 ic_floor_heating
            case R.id.iv_ground_warm:
                iconType = ContentStr.IconType.ic_floor_heating;
                break;
            case R.id.iv_fresh_air:
                iconType = ContentStr.IconType.ic_fresh_air;
                break;
            case R.id.iv_eletricity:
                iconType = ContentStr.IconType.ic_electricity;
                break;
            case R.id.iv_monitor:
                iconType = ContentStr.IconType.ic_monitor;
                break;
            case R.id.iv_air_condition:
                iconType = ContentStr.IconType.ic_air_condition;
                break;
        }
        intent.putExtra("iconType", iconType);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.dialog_exit);
    }
}
