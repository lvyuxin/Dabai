package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.configsetting.DeviceIconSelectActivity;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.bean.RequestInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.bean.TimeBean;
import com.suomate.dabaiserver.bean.TimeLaunchBean;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.utils.DeviceUtils;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSceneActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.scene_name_et)
    EditText etSceneName;
    @BindView(R.id.area_tv)
    TextView tvArea;
    @BindView(R.id.tv_instant)
    TextView tvInstant;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_time_detail)
    TextView tvTimeDetail;
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_link_detail)
    TextView tvLinkDetail;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_icon)
    TextView tvIcon;
    @BindView(R.id.rl_icon)
    RelativeLayout rlIcon;
    @BindView(R.id.tv_nominate)
    TextView tvNominate;
    //    @BindView(R.id.tv_area)
//    TextView tvArea;
    @BindView(R.id.rl_condition_instance)
    RelativeLayout rlConditionInstance;
    @BindView(R.id.rl_condition_time)
    RelativeLayout rlConditionTime;
    @BindView(R.id.rl_condition_link)
    RelativeLayout rlConditionLink;
    @BindView(R.id.condition_time_detail)
    TextView tvConditionTimeDetail;
    @BindView(R.id.condition_link_detail)
    TextView tvConditionLinkDetail;
    @BindView(R.id.rl_condition)
    RelativeLayout rlCondition;
    @BindView(R.id.ll_add_condition)
    LinearLayout llAddConditon;
    @BindView(R.id.iv_small_link_add)
    ImageView ivSmallLinkAdd;
    @BindView(R.id.iv_small_condition_add)
    ImageView ivSmallConditionAdd;

    private String area_id;
    public static final int REQUEST_AREA = 90, REQUEST_TASK = 91, REQUEST_CONDITION = 92, REQUEST_CODE_ICON = 93;
    private List<RequestInfoBean.ExecuteSelectDevice> selectList = new ArrayList<>();
    private String executeDevice;
    private int intstantType, timeType, linkType;
    private String launchStr;
    private SelectAreaDialog selectAreaDialog;
    private List<AreaSelectListBean.DataBean> areaList = new ArrayList<>();
    private String iconType;
    private TimeLaunchBean timeLaunchBean;
    private TimeBean.LinkTimeBean linkTimeBean;
    List<TimeBean.LinkLanchDevice> linkLanchDeviceList = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_scene;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setWindowStatusBarColor(R.color.title_color);
    }


    private void requestInstant() {
        RequestInfoBean.PhoneMessageBean phoneMessageBean = new RequestInfoBean.PhoneMessageBean("12345678901", "你好");
        RequestInfoBean.EmailMessageBean emailMessageBean = new RequestInfoBean.EmailMessageBean("12345678901@qq.com", "你好qq");

        AbstractRequest request = null;

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
//        if (modeType == 2) { //定时启动
//            executeNetwork(2, holdonMsg, request);
//        } else {
//            executeNetwork(1, holdonMsg, request);
//        }

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
                        tvArea.setText(name + " ▼");
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

    @OnClick({R.id.rl_icon, R.id.rl_area, R.id.iv_add_condition, R.id.btn_finish,
            R.id.iv_task_add1, R.id.iv_delete_instance, R.id.iv_delete_time, R.id.iv_delete_link, R.id.iv_small_condition_add,
            R.id.iv_small_link_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_icon:
                startActivityForResult(DeviceIconSelectActivity.class, null, REQUEST_CODE_ICON);
                overridePendingTransition(R.anim.dialog_enter, 0);
                break;
            case R.id.rl_area:
                requestAreaData();
                break;
            case R.id.iv_add_condition://添加条件
                startActivityForResult(ScenceStartConditionActivity.class, null, REQUEST_CONDITION);
                break;
            case R.id.iv_task_add1://执行任务的添加
                startActivityForResult(ScenceStartTaskActivity.class, null, REQUEST_TASK);
                break;
            case R.id.iv_delete_instance:
                rlConditionInstance.setVisibility(View.GONE);
                intstantType = 0;
                setConditionIvVisible();
                break;
            case R.id.iv_delete_time:
                rlConditionTime.setVisibility(View.GONE);
                timeType = 0;
                setConditionIvVisible();
                break;
            case R.id.iv_delete_link:
                linkType = 0;
                rlConditionLink.setVisibility(View.GONE);
                setConditionIvVisible();
                break;
            case R.id.iv_small_condition_add:
                startActivityForResult(ScenceStartConditionActivity.class, null, REQUEST_CONDITION);
                break;
            case R.id.iv_small_link_add:
                startActivityForResult(ScenceStartTaskActivity.class, null, REQUEST_TASK);
                break;
            case R.id.rl_condition_time:
                break;
            case R.id.btn_finish://完成
                sumitFinish();
                break;
        }
    }

    private void sumitFinish() {
        if (intstantType != 0) {

        } else {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AREA:
                if (resultCode == RESULT_OK) {
                    area_id = data.getStringExtra("areaId");
                }
                break;
            case REQUEST_TASK:
                if (resultCode == RESULT_OK) {
                    selectList = (List<RequestInfoBean.ExecuteSelectDevice>) data.getSerializableExtra("selectList");
                    executeDevice = JSON.toJSONString(selectList);
                    LogUtils.e(TAG, selectList.size() + executeDevice);
                }

                break;
            case REQUEST_CONDITION://添加条件
                if (resultCode == RESULT_OK) {
                    setConditionResultOk(data);

                }
                break;
            case REQUEST_CODE_ICON:
                if (resultCode == RESULT_OK) {
                    data.getStringExtra("iconType");
                    iconType = data.getStringExtra("iconType");
                    DeviceUtils.setIcon(ivIcon, iconType);
                }
                break;
        }
    }

    private void setConditionResultOk(Intent data) {
        Bundle bundle = data.getExtras();
        intstantType = bundle.getInt("intstantType", 0);
        timeType = bundle.getInt("timeType", 0);
        linkType = bundle.getInt("linkType", 0);
        setConditionIvVisible();
        if (intstantType != 0) {
            rlConditionInstance.setVisibility(View.VISIBLE);
        } else {
            rlConditionInstance.setVisibility(View.GONE);
        }
        if (timeType != 0) {
            timeLaunchBean = (TimeLaunchBean) bundle.getSerializable("timeLaunchBean");
            rlConditionTime.setVisibility(View.VISIBLE);
            tvConditionTimeDetail.setText(timeLaunchBean.getHour() + ":" + timeLaunchBean.getMinute() + "," + DeviceUtils.getWeek(timeLaunchBean.getWeek()));
        } else {
            rlConditionTime.setVisibility(View.GONE);
        }

        if (linkType != 0) {
            rlConditionLink.setVisibility(View.VISIBLE);
            String info = "";
            linkTimeBean = (TimeBean.LinkTimeBean) bundle.getSerializable("linkTimeBean");
            linkLanchDeviceList.addAll((List<TimeBean.LinkLanchDevice>) bundle.getSerializable("linkLanchDeviceList"));
            for (int i = 0; i < linkLanchDeviceList.size(); i++) {
                if (linkLanchDeviceList.get(i).getNumber() == 0) {
                    info = info + linkLanchDeviceList.get(i).getName() + ",";
                    if (linkLanchDeviceList.get(i).getVal() == Content.TYPE.TRIGGER) {
                        info = info + "触发,";
                    } else if (linkLanchDeviceList.get(i).getVal() == Content.TYPE.NOT_TRIGGER) {
                        info = info + "不触发,";
                    }
                } else {//面板
                    info = info + linkLanchDeviceList.get(i).getName() + ",";
                    if (linkLanchDeviceList.get(i).getVal() == Content.TYPE.TRIGGER) {
                        info = info + "按下,";
                    } else if (linkLanchDeviceList.get(i).getVal() == Content.TYPE.NOT_TRIGGER) {
                        info = info + "释放,";
                    } else if (linkLanchDeviceList.get(i).getVal() == Content.TYPE.LONG_PRESS) {
                        info = info + "长按,";
                    }
                }
            }
            LogUtils.e("fancyinfo:" + info);
            tvConditionLinkDetail.setText(DeviceUtils.getTime(linkTimeBean.getShour()) + ":" + DeviceUtils.getTime(linkTimeBean.getShour()) + "-" + DeviceUtils.getTime(linkTimeBean.getFhour()) + ":"
                    + DeviceUtils.getTime(linkTimeBean.getSminute()) + ","
                    + DeviceUtils.getWeek(linkTimeBean.getWeek()) + "," + info);
        } else {
            rlConditionLink.setVisibility(View.GONE);
        }
    }

    private void setConditionIvVisible() {
        if (intstantType == 0 && timeType == 0 && linkType == 0) {
            rlCondition.setVisibility(View.VISIBLE);
            llAddConditon.setVisibility(View.GONE);
            ivSmallConditionAdd.setVisibility(View.GONE);
        } else {
            rlCondition.setVisibility(View.GONE);
            llAddConditon.setVisibility(View.VISIBLE);
            ivSmallConditionAdd.setVisibility(View.VISIBLE);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
