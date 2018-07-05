package com.suomate.dabaiserver.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.area.AreaDeviceListActivity;
import com.suomate.dabaiserver.adapter.AreaSelectListAdapter;
import com.suomate.dabaiserver.base.fragment.BaseFragment;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private AreaSelectListAdapter adapter;
    private List<AreaSelectListBean.DataBean> list = new ArrayList<>();
    @Override
    protected int bindLayout() {
        return R.layout.fragment_area;
    }

    @Override
    protected void initViews() {
        super.initViews();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AreaSelectListAdapter(R.layout.item_test, list);
        recycler.setAdapter(adapter);
        requestData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("areaId",list.get(position).getArea_id()+"");
                startActivity(AreaDeviceListActivity.class,bundle);
            }
        });
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.AREA_LIST, Content.LIST_TYPE, RequestMethod.GET, AreaSelectListBean.DataBean.class);
        request.add("guid",getGuid());
        executeNetwork(1, holdonMsg, request);
    }
    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                result.getData();
                list.addAll((List<AreaSelectListBean.DataBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;
        }
    }

}
