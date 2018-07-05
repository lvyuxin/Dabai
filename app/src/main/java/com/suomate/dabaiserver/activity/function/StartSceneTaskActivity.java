package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.StartSceneTaskAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.RequestInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.CommonMethod;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StartSceneTaskActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tb)
    TitleBar tb;
    private List<RequestInfoBean.ExecuteDevice> list = new ArrayList<>();
    private List<RequestInfoBean.DeviceBean> devices = new ArrayList<>();
    private List<RequestInfoBean.ScenceBean> scences = new ArrayList<>();
    private List<RequestInfoBean.ScenceBean> scenceTimes = new ArrayList<>();
    private StartSceneTaskAdapter adapter;
    Button btn;
    @Override
    protected int bindLayout() {
        return R.layout.activity_start_scene_task;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StartSceneTaskAdapter(R.layout.item_scence_task, list);
        recycler.setAdapter(adapter);

        requestData();
        bindEvent();
        View view = LayoutInflater.from(this).inflate(R.layout.item_scence_task,null);
         btn = view.findViewById(R.id.btn_check);
    }

    private void bindEvent() {

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                showToast("dianle1");
                list.get(position).setSelect(!list.get(position).isSelect());
                LogUtils.e("状态："+ !list.get(position).isSelect());
                adapter.notifyDataSetChanged();
            }
        });

        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {

            }
        });
    }

    private void requestData() {
//        //获取所有设备信息
        AbstractRequest request = buildRequest(UrlUtils.getDeviceList, Content.LIST_TYPE, RequestMethod.POST, RequestInfoBean.DeviceBean.class);
        request.add("guid", getGuid());
        executeNetwork(1, holdonMsg, request);
        //场景列表
        AbstractRequest request1 = buildRequest(UrlUtils.SCENCE_LIST, Content.LIST_TYPE, RequestMethod.GET, RequestInfoBean.ScenceBean.class);
        request1.add("guid", getGuid());
        executeNetwork(2, holdonMsg, request1);
        //定时场景列表
        AbstractRequest request2 = buildRequest(UrlUtils.SCENCE_TIME_LIST, Content.LIST_TYPE, RequestMethod.GET, RequestInfoBean.ScenceBean.class);
        request2.add("guid", getGuid());
        executeNetwork(3, holdonMsg, request2);

    }


    /**
     * 254.251.1.id.  val:0000port01按下，02释放，03长按 port进制转化
     *
     * @param what
     * @param result
     * @param <T>
     */
    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                devices.addAll((List<RequestInfoBean.DeviceBean>) result.getData());
                String address;
                RequestInfoBean.DeviceBean deviceBean;
                String val;
                for (int i = 0; i < devices.size(); i++) {
                    //智能面板和专业面板 address需要进行处理的
                    deviceBean = devices.get(i);
                    if (Content.CONTROL_TYPE.IO_PANEL.equals(deviceBean.getType()) || Content.CONTROL_TYPE.INTELLIGENT_PANEL.equals(deviceBean.getType())) {
                        address = "254.251.1." + deviceBean.getDevice_id();
                        val = "0000" + CommonMethod.getHexStringValue(deviceBean.getPort()) + "01";

                        list.add(new RequestInfoBean.ExecuteDevice(deviceBean.getArea_name() + "/" + deviceBean.getDevice_name(), deviceBean.getDevice_id() + "",
                                val, 0, deviceBean.getJson_type() + "", address));
                    } else {
                        list.add(new RequestInfoBean.ExecuteDevice(deviceBean.getArea_name() + "/" + deviceBean.getDevice_name(), deviceBean.getDevice_id() + "",
                                deviceBean.getVal(), 0, deviceBean.getJson_type() + "", ""));
                    }
                }
                break;
            case 2:
                scences.addAll((List<RequestInfoBean.ScenceBean>) result.getData());
                RequestInfoBean.ScenceBean scenceBean;
                for (int i = 0; i < scences.size(); i++) {
                     scenceBean = scences.get(i);
                    list.add(new RequestInfoBean.ExecuteDevice(scenceBean.getArea_name()+"/"+scenceBean.getScene_name(), scenceBean.getScene_id() + "",
                            scenceBean.getVal(), 0, scenceBean.getJson_type() + "", ""));
                }
                break;
            case 3:
                scenceTimes.addAll((List<RequestInfoBean.ScenceBean>) result.getData());
                RequestInfoBean.ScenceBean scenceBean1;
                for (int i = 0; i < scenceTimes.size(); i++) {
                    scenceBean1 = scenceTimes.get(i);
                    list.add(new RequestInfoBean.ExecuteDevice(scenceBean1.getArea_name()+"/"+scenceBean1.getScene_name(), scenceBean1.getScene_id() + "",
                            scenceBean1.getVal(), 0, scenceBean1.getJson_type() + "", ""));
                }
                break;

        }
        adapter.notifyDataSetChanged();

    }
}
