package com.suomate.dabaiserver.activity.configsetting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.utils.CommonMethod;

import butterknife.BindView;
import butterknife.OnClick;

public class IntelligentHostActivity extends BaseActivity {
    @BindView(R.id.btn_tick_host)
    Button btnTickHost;
    @BindView(R.id.ip_tv)
    TextView ipTv;
    @BindView(R.id.subnet_mask_tv)
    TextView subnetMaskTv;
    @BindView(R.id.gateway_tv)
    TextView gatewayTv;
    @BindView(R.id.host_time_tv)
    TextView hostTimeTv;
    @BindView(R.id.system_tv)
    TextView systemTv;
    @BindView(R.id.btn_check)
    Button btnCheck;

    @Override
    protected int bindLayout() {
        return R.layout.activity_intelligent_host;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        String ipinfor = getIntent().getExtras().getString("ipinfor");
//        String[] splits = ipinfor.split(",");
        //ip地址
        ipTv.setText(ipinfor.substring(ipinfor.indexOf("ip=") + 3, CommonMethod.getFromIndex(ipinfor, ",", 2)));
        subnetMaskTv.setText(ipinfor.substring(ipinfor.indexOf("mask=") + 5, CommonMethod.getFromIndex(ipinfor, ",", 3)));
        gatewayTv.setText(ipinfor.substring(ipinfor.indexOf("route=") + 6, CommonMethod.getFromIndex(ipinfor, ",", 4)));
        gatewayTv.setText(ipinfor.substring(ipinfor.indexOf("route=") + 6, CommonMethod.getFromIndex(ipinfor, ",", 4)));
        hostTimeTv.setText(CommonMethod.getCurDateTime());
        systemTv.setText(CommonMethod.getCurDateTime());
    }

    @OnClick({R.id.btn_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
                showToast("校准成功！");
                finish();
                break;
        }

    }
}
