package com.suomate.dabaiserver.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.function.AddSceneActivity;
import com.suomate.dabaiserver.activity.function.FuntionDeviceListActivity;
import com.suomate.dabaiserver.adapter.classifyListAdapter;
import com.suomate.dabaiserver.base.fragment.BaseFragment;
import com.suomate.dabaiserver.bean.ClassifyListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FunctionFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tb)
    TitleBar tb;
    private classifyListAdapter adapter;
    private List<ClassifyListBean.DataBean> list = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.fragment_function;
    }

    @Override
    protected void initViews() {
        super.initViews();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new classifyListAdapter(R.layout.item_test, list);
        recycler.setAdapter(adapter);
        requestData();
        bindEvent();
    }


    private void bindEvent() {

        //添加场景的入口
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                startActivity(AddSceneActivity.class,null);

            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("classify_id",list.get(position).getClassify_id()+"");
                bundle.putString("name",list.get(position).getClassify_name());
                startActivity(FuntionDeviceListActivity.class,bundle);
            }
        });

    }
    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.CLASSIFY_LIST, Content.LIST_TYPE, RequestMethod.GET, ClassifyListBean.DataBean.class);
        request.add("guid",getGuid());
        executeNetwork(1, holdonMsg, request);
    }
    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                result.getData();
                list.addAll((List<ClassifyListBean.DataBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
