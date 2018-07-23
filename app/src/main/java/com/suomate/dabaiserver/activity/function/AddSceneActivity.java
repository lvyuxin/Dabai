package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.configsetting.DeviceIconSelectActivity;
import com.suomate.dabaiserver.adapter.function.DelayTimeAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.bean.CommonBean;
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
import com.suomate.dabaiserver.widget.dialog.TaskDelayTimeDialog;
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
    @BindView(R.id.iv_task_add)
    ImageView ivTaskAdd;
    @BindView(R.id.iv_small_condition_add)
    ImageView ivSmallConditionAdd;
    @BindView(R.id.recycler)
    RecyclerView taskRecycler;
    @BindView(R.id.rl_task)
    RelativeLayout rlTask;
    private String area_id;
    public static final int REQUEST_AREA = 90, REQUEST_TASK = 91, REQUEST_CONDITION = 92, REQUEST_CODE_ICON = 93;
    private List<CommonBean.ExecuteDeviceBean> excuteDeviceList = new ArrayList<>();
    private int intstantType, timeType, linkType;
    private SelectAreaDialog selectAreaDialog;
    private List<AreaSelectListBean.DataBean> areaList = new ArrayList<>();
    private String iconType;
    private TimeLaunchBean timeLaunchBean;
    private TimeBean.LinkTimeBean linkTimeBean;
    List<TimeBean.LinkLanchDevice> linkLanchDeviceList = new ArrayList<>();
    private DelayTimeAdapter taskAdapter;
    private int taskDelayPostion;
    private TaskDelayTimeDialog taskDelayTimeDialog;
    private String strApp, strMsg, strEmail, timeTouch, executeDevice,linkTouch,linkTouchTime;


    @Override
    protected int bindLayout() {
        return R.layout.activity_add_scene;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setWindowStatusBarColor(R.color.title_color);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        taskRecycler.setLayoutManager(linearLayoutManager);
        taskAdapter = new DelayTimeAdapter(R.layout.item_delay_time, excuteDeviceList);
        taskRecycler.setAdapter(taskAdapter);
        taskDelayTimeDialog = new TaskDelayTimeDialog(AddSceneActivity.this, R.style.detail_dialog_style, true);
        taskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                taskDelayPostion = position;
                switch (view.getId()) {
                    case R.id.iv_task_delete:
                        excuteDeviceList.remove(position);
                        setRlTaskVisible();
                        break;
                    case R.id.delay_time:
                        taskDelayTimeDialog.show();
                        break;
                }
                adapter.notifyDataSetChanged();
            }
        });

        taskDelayTimeDialog.setCallBackIml(new CallBackIml() {
            @Override
            public void callBack(int position) {
                super.callBack(position);
//                LogUtils.e("fancyposition"+position);
                excuteDeviceList.get(taskDelayPostion).setDelay(position);
                taskAdapter.notifyDataSetChanged();
            }
        });

    }

    private void setRlTaskVisible() {
        if (excuteDeviceList.size() > 0) {
            rlTask.setVisibility(View.GONE);
            ivTaskAdd.setVisibility(View.VISIBLE);
        } else {
            rlTask.setVisibility(View.VISIBLE);
            ivTaskAdd.setVisibility(View.GONE);
        }
    }


    private void requestAreaData() {
        AbstractRequest request = buildRequest(UrlUtils.AREA_LIST, Content.LIST_TYPE, RequestMethod.GET, AreaSelectListBean.DataBean.class);
        request.add("guid", getGuid());
        executeNetwork(2, holdonMsg, request);
    }

    @OnClick({R.id.rl_icon, R.id.rl_area, R.id.iv_add_condition, R.id.btn_finish,
            R.id.iv_task_add1, R.id.iv_delete_instance, R.id.iv_delete_time, R.id.iv_delete_link, R.id.iv_small_condition_add,
            R.id.iv_task_add})
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
            case R.id.iv_task_add:
                startActivityForResult(ScenceStartTaskActivity.class, null, REQUEST_TASK);
                break;
            case R.id.rl_condition_time:
                break;
            case R.id.btn_finish://完成
                if (intstantType == 0 && timeType == 0 && linkType == 0) {
                    showToast("请选择启动方式");
                    return;
                }
                sumitFinish();
                break;
        }
    }

    private void sumitFinish() {
        if (intstantType != 0) {
            requestInstant();
        }
        if (timeType != 0) {
            requestTime();
        }
        if (linkType!=0) {
            requestLink();
        }


    }

    /**
     * 立即启动请求方式
     */
    private void requestInstant() {
        AbstractRequest request = buildRequest(UrlUtils.ADD_SCENCE, Content.STRING_TYPE, RequestMethod.POST, null);
        if (TextUtils.isEmpty(iconType)) {
            showToast("请选择图标");
            return;
        }
        if (TextUtils.isEmpty(area_id)) {
            showToast("请为场景命名");
            return;
        }
        if (TextUtils.isEmpty(area_id)) {
            showToast("请选择区域");
            return;
        }
        if (TextUtils.isEmpty(executeDevice)) {
            showToast("请选择启动任务");
            return;
        }
        request.add("guid", getGuid());
        request.add("area_id", area_id);
        request.add("icon", iconType);
        request.add("sceneName", etSceneName.getText().toString());
        request.add("message", strMsg);
        request.add("app", strApp);
        request.add("email", strEmail);
        request.add("executeDevice", executeDevice);
        request.add("clickTouch", "1");
        executeNetwork(3, request);


    }

    /**
     * 定时启动请求方式
     */
    private void requestTime() {
        AbstractRequest request = buildRequest(UrlUtils.ADD_TIME_SCENCE, Content.STRING_TYPE, RequestMethod.POST, null);
        if (TextUtils.isEmpty(iconType)) {
            showToast("请选择图标");
            return;
        }
        if (TextUtils.isEmpty(etSceneName.getText().toString())) {
            showToast("请为场景命名");
            return;
        }
        if (TextUtils.isEmpty(area_id)) {
            showToast("请选择区域");
            return;
        }
        if (TextUtils.isEmpty(executeDevice)) {
            showToast("请选择启动任务");
            return;
        }
        request.add("guid", getGuid());
        request.add("area_id", area_id);
        request.add("icon", iconType);
        request.add("sceneName", etSceneName.getText().toString());
        request.add("message", strMsg);
        request.add("app", strApp);
        request.add("email", strEmail);
        request.add("executeDevice", executeDevice);
        request.add("clickTouch", "1");
        request.add("timeTouch", timeTouch);
        executeNetwork(4, request);


    }


    /**
     * 联动启动请求方式
     */
    private void requestLink() {
        AbstractRequest request = buildRequest(UrlUtils.addLinkage, Content.STRING_TYPE, RequestMethod.POST, null);
        if (TextUtils.isEmpty(iconType)) {
            showToast("请选择图标");
            return;
        }
        if (TextUtils.isEmpty(etSceneName.getText().toString())) {
            showToast("请为场景命名");
            return;
        }
        if (TextUtils.isEmpty(area_id)) {
            showToast("请选择区域");
            return;
        }
        if (TextUtils.isEmpty(executeDevice)) {
            showToast("请选择启动任务");
            return;
        }
        request.add("guid", getGuid());
        request.add("area_id", area_id);
        request.add("icon", iconType);
        request.add("sceneName", etSceneName.getText().toString());
        request.add("message", strMsg);
        request.add("app", strApp);
        request.add("email", strEmail);
        request.add("executeDevice", executeDevice);
        request.add("linkTouch",linkTouch );
        request.add("linkTouchTime", linkTouchTime);
        executeNetwork(5, request);


    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 3:
                showToast("立即启动场景添加成功！");
                finish();
                break;
            case 4:
                showToast("定时场景添加成功！");
                finish();
                break;
            case  5:
                showToast("联动场景添加成功！");
                finish();
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
                    strApp = data.getStringExtra("app");
                    strMsg = data.getStringExtra("msg");
                    strEmail = data.getStringExtra("email");
                    excuteDeviceList.addAll((List<CommonBean.ExecuteDeviceBean>) data.getSerializableExtra("selectList"));
                    LogUtils.e("fancycyselectList", JSON.toJSONString(excuteDeviceList));
                    executeDevice = JSON.toJSONString(excuteDeviceList);
//                    JSONArray array = new JSONArray();
//                    JSONObject object;
//                    CommonBean.ExecuteDeviceBean deviceBean;
//                    for(int i=0;i<excuteDeviceList.size();i++){
//                        object = new JSONObject();
//                        deviceBean = excuteDeviceList.get(i);
//                        object.put("device_or_scene_id",deviceBean.getDevice_or_scene_id());
//                        object.put("val",deviceBean.getVal());
//                        object.put("delay",deviceBean.getDelay());
//                        object.put("type",deviceBean.getType());
//                        object.put("address",deviceBean.getAddress());
//                        array.add(object);
//                    }
                    setRlTaskVisible();
                    taskAdapter.notifyDataSetChanged();
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
            timeTouch = JSON.toJSONString(timeLaunchBean);
            LogUtils.e("fancylaunchBean" + timeTouch);
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
            linkTouch=JSON.toJSONString(linkLanchDeviceList);
            linkTouchTime= JSON.toJSONString(linkTimeBean);

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
        timeTouch = JSON.toJSONString(launchBean);
        LogUtils.e("fancylaunchBean" + timeTouch);
    }

//    @Subscribe
//    public void onEventMainThread1(int time) {
//        LogUtils.e("fancytime", time + "");
//        excuteDeviceList.get(taskDelayPostion).setDelay(time);
//        taskAdapter.notifyDataSetChanged();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
