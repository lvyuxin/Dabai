package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;

import butterknife.OnClick;

public class DeviceIconSelectActivity extends BaseActivity {
    private String sportLight, dimming, lightwith, normallight, colorfullight, hanglight;
    private int type;
    private int imgSrc;
    Intent intent;

    @Override
    protected int bindLayout() {
        return R.layout.activity_device_icon_select;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        intent = getIntent();
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
            case R.id.iv_sport_light:
                type = 1;
                imgSrc = R.mipmap.icon_shedeng;

                break;
            case R.id.iv_dimming:
                imgSrc = R.mipmap.icon_tiaoguang;
                type = 1;
                break;
            case R.id.iv_normal_light:
                imgSrc = R.mipmap.icon_normal_light;

                type = 1;
                break;
            case R.id.iv_colorful_light:
                imgSrc = R.mipmap.icon_caideng;

                type = 1;
                break;
            case R.id.iv_hanging_light:
                imgSrc = R.mipmap.icon_diaodeng;

                type = 1;
                break;
            case R.id.iv_curtain:
                imgSrc = R.mipmap.icon_chuanglian;

                type = 1;
                break;
            case R.id.iv_double_curtain:
                imgSrc = R.mipmap.icon_double_chuanglian;

                type = 1;
                break;
            case R.id.iv_single_curtain:
                type = 1;
                imgSrc = R.mipmap.icon_single_chuagnlian;

                break;
            case R.id.iv_sensor1:
                imgSrc = R.mipmap.icon_sensor;

                type = 1;
                break;
            case R.id.iv_gas:
                imgSrc = R.mipmap.icon_gas;

                type = 1;
                break;
            case R.id.iv_sensor2:
                imgSrc = R.mipmap.icon_sensor2;

                type = 1;
                break;
            case R.id.iv_smoke_alarm:
                imgSrc = R.mipmap.icon_smoke_alarm;

                type = 1;
                break;
//环境
            case R.id.iv_co2:
                imgSrc = R.mipmap.icon_co2;

                type = 1;
                break;
            case R.id.iv_humidity:
                imgSrc = R.mipmap.icon_humidity;

                type = 1;
                break;
            case R.id.iv_pm25:
                imgSrc = R.mipmap.icon_pm25;

                type = 1;
                break;
            case R.id.iv_tempture:
                imgSrc = R.mipmap.icon_tempture;

                type = 1;
                break;
            case R.id.iv_voc:
                imgSrc = R.mipmap.icon_voc;

                type = 1;
                break;
            //面板
            case R.id.iv_panel1:
                imgSrc = R.mipmap.icon_panel1;

                type = 1;
                break;
            case R.id.iv_panel2:
                imgSrc = R.mipmap.icon_panel2;

                type = 1;
                break;
            case R.id.iv_panel3:
                imgSrc = R.mipmap.icon_panel3;

                type = 1;
                break;
            case R.id.iv_panel4:
                imgSrc = R.mipmap.icon_panel4;

                break;
            case R.id.iv_panel5:
                imgSrc = R.mipmap.icon_panel5;

                break;
            case R.id.iv_panel6:
                imgSrc = R.mipmap.icon_panel6;
                break;
            //其他
            case R.id.iv_ground_warm:
                imgSrc = R.mipmap.icon_ground_wram;
                break;
            case R.id.iv_fresh_air:
                imgSrc = R.mipmap.icon_fresh_air;
                break;
            case R.id.iv_eletricity:
                imgSrc = R.mipmap.icon_electricity;
                break;
            case R.id.iv_monitor:
                imgSrc = R.mipmap.icon_monitor;
                break;
            case R.id.iv_air_condition:
                imgSrc = R.mipmap.icon_air_condition;
                break;
        }
        intent.putExtra("imgSrc", imgSrc);
        setResult(RESULT_OK, intent);
        finish();
    }
}
