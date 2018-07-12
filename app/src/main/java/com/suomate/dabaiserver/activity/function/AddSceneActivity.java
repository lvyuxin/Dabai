package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.bean.RequestInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.bean.TimeLaunchBean;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.suomate.dabaiserver.widget.dialog.SelectAreaDialog;
import com.yanzhenjie.nohttp.RequestMethod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddSceneActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.scene_name_et)
    EditText etSceneName;
    @BindView(R.id.area_tv)
    TextView tvArea;

//    @BindView(R.id.et_area)
//    EditText etArea;
//    @BindView(R.id.et_start_modle)
//    EditText etStartModle;
//    @BindView(R.id.et_start_task)
//    EditText etStartTask;
    private String area_id;
    public static final int REQUEST_AREA = 90, REQUEST_TASK = 91, REQUEST_MODLE = 92;
    private List<RequestInfoBean.ExecuteSelectDevice> selectList = new ArrayList<>();
    private String executeDevice;
    private int modeType;
    private String launchStr;
    private SelectAreaDialog selectAreaDialog;
    private List<AreaSelectListBean.DataBean> areaList = new ArrayList<>();
    @Override
    protected int bindLayout() {
        return R.layout.activity_add_scene;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setWindowStatusBarColor(R.color.title_color);
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                if (modeType == 1 || modeType == 2) {
                    requestData();
                }
            }
        });
        bindEvent();
    }

    private void bindEvent() {
//        etArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(AreaSelectListActivity.class, null, REQUEST_AREA);
//            }
//        });
    }

    private void requestData() {
        RequestInfoBean.PhoneMessageBean phoneMessageBean = new RequestInfoBean.PhoneMessageBean("12345678901", "你好");
        RequestInfoBean.EmailMessageBean emailMessageBean = new RequestInfoBean.EmailMessageBean("12345678901@qq.com", "你好qq");
        AbstractRequest request;
        if (modeType == 2) { //定时启动
            request = buildRequest(UrlUtils.ADD_TIME_SCENCE, Content.LIST_TYPE, RequestMethod.POST, null);
            request.add("timeTouch", launchStr);
        } else {
            request = buildRequest(UrlUtils.ADD_SCENCE, Content.LIST_TYPE, RequestMethod.POST, null);
        }

        request.add("guid", getGuid());
        request.add("scene_img", "灯1");
        request.add("area_id", area_id);
        request.add("icon", "11");
        request.add("sceneName", etSceneName.getText().toString());
        request.add("message", "你好");
        request.add("app", "111");
        request.add("email", "397670543@qq.com");
        request.add("executeDevice", executeDevice);
        request.add("clickTouch", "1");
        if (modeType == 2) { //定时启动
            executeNetwork(2, holdonMsg, request);
        } else {
            executeNetwork(1, holdonMsg, request);
        }

    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                showToast("添加场景成功！");
                break;
            case 2://区域请求
                areaList.addAll((List<AreaSelectListBean.DataBean>) result.getData());
                selectAreaDialog = new SelectAreaDialog(this, R.style.basedialog_style, true, areaList);
                selectAreaDialog.setCallBackIml(new CallBackIml() {
                    @Override
                    public void callBack(String id, String name) {
                        super.callBack(id, name);
                        area_id = id;
                        tvArea.setText(name+ " ▼");
                    }
                });
                selectAreaDialog.show();
                break;
        }
    }
    private void requestAreaData() {
        AbstractRequest request = buildRequest(UrlUtils.AREA_LIST, Content.LIST_TYPE, RequestMethod.GET, AreaSelectListBean.DataBean.class);
        request.add("guid", getGuid());
        executeNetwork(2, holdonMsg, request);
    }

    @OnClick({R.id.rl_icon,R.id.rl_area,R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_icon:
                showToast("点了图片！");
                break;
            case R.id.rl_area:
                requestAreaData();
                break;
            case R.id.iv_add://添加条件
                startActivityForResult(ScenceStartConditionActivity.class, null, REQUEST_MODLE);
                break;

//            case R.id.rl_start_model:
//                break;
//            case R.id.rl_start_task:
//                startActivityForResult(StartSceneTaskActivity.class, null, REQUEST_TASK);
//                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AREA:
                if (resultCode == RESULT_OK) {
                    area_id = data.getStringExtra("areaId");
//                    etArea.setText(data.getStringExtra("areaName"));
                }
                break;
            case REQUEST_TASK:
                if (resultCode == RESULT_OK) {
                    selectList = (List<RequestInfoBean.ExecuteSelectDevice>) data.getSerializableExtra("selectList");
                    executeDevice = JSON.toJSONString(selectList);
//                    etStartTask.setText(selectList.size() + "");
                    LogUtils.e(TAG, selectList.size() + executeDevice);
                }

                break;
            case REQUEST_MODLE:
                if (resultCode == RESULT_OK) {
                    modeType = data.getIntExtra("type", 0);
                }
                break;
        }

    }

    @Subscribe
    public void onEventMainThread(TimeLaunchBean launchBean) {
        launchStr = JSON.toJSONString(launchBean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
