package com.suomate.dabaiserver.activity.Device;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.widget.TitleBar;

import butterknife.BindView;

public class PanelBindDeviceActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    private int type;
    private RecyclerView recycler;

    @Override
    protected int bindLayout() {
        return R.layout.activity_panel_bind_device2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        switch (type) {
            case 1://开关设备的ui

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
        }
    }


}
