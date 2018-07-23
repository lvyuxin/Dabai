package com.suomate.dabaiserver.adapter.function;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CommonBean;
import com.suomate.dabaiserver.bean.ScenceStartTaskBean;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.widget.dialog.StartTaskDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxi on 2018/7/16.
 */

public class ScenceStartTaskAdapter extends BaseQuickAdapter<ScenceStartTaskBean, BaseViewHolder> {
    public static ItemStartTaskAdapter adapter;
    private Context context;
    private CallBackIml callBackIml;
    private StartTaskDialog dialog;
    private String deviceName;

    public void setCallBackIml(CallBackIml callBackIml) {
        this.callBackIml = callBackIml;
    }

    public ScenceStartTaskAdapter(int layoutResId, @Nullable List<ScenceStartTaskBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenceStartTaskBean item) {
        helper.setText(R.id.scence_device_name, item.getClassify_name());
        helper.setText(R.id.scence_device_percent, item.getPercent());
        final List<ScenceStartTaskBean.DeviceOrSceneInfoBean> deviceOrSceneInfoList = new ArrayList<>();
        ScenceStartTaskBean.DeviceOrSceneInfoBean bean;
        for (int i = 0; i < item.getDeviceOrSceneInfo().size(); i++) {
            bean = item.getDeviceOrSceneInfo().get(i);
            if (bean.getControl_type().trim().equals(ContentStr.Control_type.fictitiousDevice)) {
                deviceOrSceneInfoList.add(new ScenceStartTaskBean.DeviceOrSceneInfoBean(bean.getDevice_or_scene_id(), bean.getControl_type(), bean.getJson_type(),
                        bean.getDevice_or_scene_name(), bean.getDetail(), bean.getPort(), bean.getAddress()));
//                LogUtils.e("fancycydetail:"+bean.getDetail());
            } else {
//                LogUtils.e("fancycydetail:"+bean.getDetail());
                deviceOrSceneInfoList.add(new ScenceStartTaskBean.DeviceOrSceneInfoBean(bean.getDevice_or_scene_id(), bean.getControl_type(), bean.getJson_type(),
                        bean.getArea_name() + ContentStr.Symbol.dot + bean.getDevice_or_scene_name(), bean.getDetail(), bean.getPort(), bean.getAddress()));
            }
        }

        adapter = new ItemStartTaskAdapter(R.layout.item_item_scence_task, deviceOrSceneInfoList);
        RecyclerView recycler = helper.getView(R.id.item_recylcer);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_device_check:
                        deviceOrSceneInfoList.get(position).setSelect(!deviceOrSceneInfoList.get(position).isSelect());
                        callBackIml.callBack(deviceOrSceneInfoList.get(position));
//                        callBackIml.callBack(position,deviceOrSceneInfoList.get(position).isSelect());
                        LogUtils.e("fancysetSelect:" + deviceOrSceneInfoList.get(position).isSelect());
                        break;
                }
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dialog = new StartTaskDialog(context, R.style.detail_dialog_style, true,
                        new CommonBean.StartTaskDialogBean(deviceOrSceneInfoList.get(position).getName(), deviceOrSceneInfoList.get(position).getControl_type().trim(),
                                deviceOrSceneInfoList.get(position).getDevice_or_scene_id() + "", deviceOrSceneInfoList.get(position).getJson_type(), deviceOrSceneInfoList.get(position).getPort(), deviceOrSceneInfoList.get(position).getDetail()));
                dialog.show();
            }
        });
    }
}
