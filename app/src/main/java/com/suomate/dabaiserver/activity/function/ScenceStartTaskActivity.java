package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.ScenceStartTaskAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.bean.ScenceStartTaskBean;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScenceStartTaskActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private ScenceStartTaskAdapter adapter;
    private List<ScenceStartTaskBean> list=new ArrayList<>();
    private int allCount,selectCount;
    private CheckBox checkAll;

    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_start_task;
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        super.initViews(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ScenceStartTaskAdapter(R.layout.item_scence_task,list,this);

        initRecycler();
        bindEvent();
        requestTaskData();
    }

    private void bindEvent() {

//        //判断全选装态
//        adapter.setCallBackIml(new CallBackIml(){
//            @Override
//            public void confirmHandle() {
//                super.confirmHandle();
//                for (int i = 0; i < list.size(); i++) {
//                    allCount+= list.get(i).getDeviceOrSceneInfo().size();
//                    for (int i1 = 0; i1 < list.get(i).getDeviceOrSceneInfo().size(); i1++) {
//                        if (list.get(i).getDeviceOrSceneInfo().get(i1).isSelect()) {
//                            selectCount+=1;
//                        }
//                    }
//                }
//                LogUtils.e("fancyallCount:"+allCount);
//                LogUtils.e("fancyallCount1:"+selectCount);
//                if (allCount==selectCount) {
//                     checkAll.setChecked(true);
//                }else{
//                    checkAll.setChecked(false);
//                }
//                allCount=0;
//                selectCount=0;
//            }
//        });

    }

    private void initRecycler() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_scence_task, null);
        View footer = LayoutInflater.from(this).inflate(R.layout.item_scence_task_footer, null);
         checkAll = header.findViewById(R.id.head_check);
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (ScenceStartTaskBean scenceStartTaskBean : list) {
                        for (ScenceStartTaskBean.DeviceOrSceneInfoBean deviceOrSceneInfoBean : scenceStartTaskBean.getDeviceOrSceneInfo()) {
                            deviceOrSceneInfoBean.setSelect(true);
                        }
                    }

                }else {
                    for (ScenceStartTaskBean scenceStartTaskBean : list) {
                        for (ScenceStartTaskBean.DeviceOrSceneInfoBean deviceOrSceneInfoBean : scenceStartTaskBean.getDeviceOrSceneInfo()) {
                            deviceOrSceneInfoBean.setSelect(false);
                        }
                    }

                }
                adapter.notifyDataSetChanged();
            }
        });

        adapter.addHeaderView(header);
        adapter.addFooterView(footer);
        recyclerView.setAdapter(adapter);
    }

    private void requestTaskData() {
        AbstractRequest request = buildRequest(UrlUtils.getSceneDeviceAccessClassify, Content.LIST_TYPE, RequestMethod.GET, ScenceStartTaskBean.class);
        request.add("guid",getGuid());
        executeNetwork(1,request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                list.addAll((List<ScenceStartTaskBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;

        }

    }



}
