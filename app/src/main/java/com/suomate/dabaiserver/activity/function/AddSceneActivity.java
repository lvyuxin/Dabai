package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.configsetting.AreaSelectListActivity;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.RequestInfoBean;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import butterknife.BindView;
import butterknife.OnClick;

public class AddSceneActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.scene_name_et)
    EditText etSceneName;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.et_start_modle)
    EditText etStartModle;
    @BindView(R.id.et_start_task)
    EditText etStartTask;

    private String area_id;
    public static final int REQUEST_AREA = 90,REQUEST_TASK=91;

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_scene;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                requestData();
            }
        });
        bindEvent();

    }

    private void bindEvent() {
        etArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(AreaSelectListActivity.class, null, REQUEST_AREA);

            }
        });
    }

    private void requestData() {
        RequestInfoBean.PhoneMessageBean phoneMessageBean = new RequestInfoBean.PhoneMessageBean("12345678901", "你好");
        RequestInfoBean.EmailMessageBean emailMessageBean = new RequestInfoBean.EmailMessageBean("12345678901@qq.com", "你好qq");
        AbstractRequest request = buildRequest(UrlUtils.ADD_SCENCE, Content.LIST_TYPE, RequestMethod.POST, null);
        request.add("guid", getGuid());
        request.add("scene_img", "");
        request.add("area_id", area_id);
        request.add("icon", "");
        request.add("sceneName", etSceneName.getText().toString());
        request.add("message", phoneMessageBean.toString());
        request.add("app", "111");
        request.add("email", emailMessageBean.toString());
        request.add("executeDevice", "2");
        request.add("clickTouch", "1");
        executeNetwork(1, holdonMsg, request);
    }

    @OnClick({R.id.rl_start_model, R.id.rl_start_task, R.id.rl_area, R.id.tv_start1, R.id.tv_start2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_area:
                startActivityForResult(AreaSelectListActivity.class, null, REQUEST_AREA);
                break;
            case R.id.rl_start_model:
                break;
            case R.id.rl_start_task:
                startActivityForResult(StartSceneTaskActivity.class,null,REQUEST_TASK);
                break;
            case R.id.tv_start1:
                etStartModle.setText("一键启动");
                break;
            case R.id.tv_start2:
                etStartModle.setText("定时启动");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AREA:
                if (resultCode == RESULT_OK) {
                    area_id = data.getStringExtra("areaId");
                    etArea.setText(data.getStringExtra("areaName"));
                }
                break;
        }

    }
}
