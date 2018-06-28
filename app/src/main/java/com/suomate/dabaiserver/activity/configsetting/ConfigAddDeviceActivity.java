package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.widget.TitleBar;

import butterknife.BindView;

public class ConfigAddDeviceActivity extends BaseActivity {

    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.tv_name)
    TextView tvName;
    private String title, id, serial;
    public static final int REQUEST_CODE_AREA = 101;
    public static final int REQUEST_CODE_CLASSIFY = 102;
    public static final int REQUEST_CODE_NOMINATE = 103;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        getData();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_config_add_device;
    }
//
//    @OnClick({R.id.rl_area, R.id.rl_classification, R.id.rl_niminate, R.id.rl_icon_select, R.id.rl_icon_select})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_area:
//                startActivityForResult(AreaSelectListActivity.class, null, REQUEST_CODE_AREA);
//                break;
//            case R.id.rl_classification:
//                startActivityForResult(ClassifyDeviceListActivity.class, null, REQUEST_CODE_CLASSIFY);
//                break;
//            case R.id.rl_niminate:
//                Bundle bundle = new Bundle();
//                bundle.putInt("type", ContentConfig.TYPE.NOMINATE);
//                startActivityForResult(EditNameActivity.class, bundle, REQUEST_CODE_NOMINATE);
//                break;
//            case R.id.rl_icon_select:
//                break;
//        }
//
//    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        id = bundle.getString("id");
        serial = bundle.getString("serial");
        tb.setTextTitle(title);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_AREA:
                if (RESULT_OK == resultCode) {
                    data.getStringExtra("areaId");
                    tvArea.setText(data.getStringExtra("areaName"));
                }
                break;
            case REQUEST_CODE_CLASSIFY:
                if (RESULT_OK == resultCode) {
                    data.getStringExtra("classifyId");
                    tvClassify.setText(data.getStringExtra("classifyName"));
                }
                break;
            case REQUEST_CODE_NOMINATE:
                if (RESULT_OK == resultCode) {
                    tvName.setText(data.getStringExtra("name"));
                }
                break;

        }

    }
}
