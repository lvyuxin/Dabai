package com.suomate.dabaiserver.activity.configsetting;

import android.os.Bundle;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;

public class DeviceSelectListActivity extends BaseActivity {


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_device_select_list;
    }


}
