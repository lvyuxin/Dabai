package com.suomate.dabaiserver.activity.Device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaDeviceEntity;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class PanelActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.panel_btn1)
    Button panelBtn1;
    @BindView(R.id.panel_btn2)
    Button panelBtn2;
    @BindView(R.id.panel_btn3)
    Button panelBtn3;
    @BindView(R.id.panel_3btn_left)
    Button panel3btnLeft;
    @BindView(R.id.panel_3btn_right)
    Button panel3btnRight;
    @BindView(R.id.rl4)
    RelativeLayout rl4;
    @BindView(R.id.panel_4btn_left)
    Button panel4btnLeft;
    @BindView(R.id.panel_4btn_right)
    Button panel4btnRight;
    @BindView(R.id.panel_bottom_btn)
    Button btnBottom;
    @BindView(R.id.rl5)
    RelativeLayout rl5;

    @Override
    protected int bindLayout() {
        return R.layout.activity_panel_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        Intent intent = getIntent();
        AreaDeviceEntity areaDeviceEntity = (AreaDeviceEntity) intent.getSerializableExtra("areaDeviceEntity");
        tb.setTextTitle(areaDeviceEntity.getArea_name() + ContentStr.Symbol.dot + areaDeviceEntity.getDevice_name());
        setPanelView(areaDeviceEntity.getPanel_number());
//        areaDeviceEntity.getControl_type();
    }

    public void setPanelView(int panelNumber) {
        switch (panelNumber) {
            case 1:
                panelBtn1.setVisibility(View.VISIBLE);
                break;
            case 2:
                panelBtn1.setVisibility(View.VISIBLE);
                panelBtn2.setVisibility(View.VISIBLE);
                break;
            case 3:
                panelBtn1.setVisibility(View.VISIBLE);
                panelBtn2.setVisibility(View.VISIBLE);
                panelBtn3.setVisibility(View.VISIBLE);
                break;
            case 4:
                panelBtn1.setVisibility(View.VISIBLE);
                panelBtn2.setVisibility(View.VISIBLE);
                rl4.setVisibility(View.VISIBLE);
                break;
            case 5:
                rl4.setVisibility(View.VISIBLE);
                rl5.setVisibility(View.VISIBLE);
                btnBottom.setVisibility(View.VISIBLE);
                break;
            case 6:
                panelBtn1.setVisibility(View.VISIBLE);
                panelBtn2.setVisibility(View.VISIBLE);
                rl4.setVisibility(View.VISIBLE);
                rl5.setVisibility(View.VISIBLE);
                break;

        }
    }

    @OnClick({R.id.panel_btn1, R.id.panel_btn2, R.id.panel_btn3, R.id.panel_3btn_left, R.id.panel_3btn_right
            , R.id.panel_4btn_left, R.id.panel_4btn_right, R.id.panel_bottom_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.panel_btn1:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_btn2:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_btn3:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_3btn_left:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_3btn_right:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_4btn_left:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_4btn_right:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
            case R.id.panel_bottom_btn:
                startActivity(PanelBindTypeListActivity.class,null);
                break;
        }
    }


}
