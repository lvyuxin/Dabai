package com.suomate.dabaiserver.activity.area;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.AreaDeviceAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaDeviceEntity;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.ContentConfig;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AreaDeviceListActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int curPage = 1;
    private List<AreaDeviceEntity> list = new ArrayList<>();
    private AreaDeviceAdapter adapter;
    private String areaId;
    private int deletePostion;
    @Override
    protected int bindLayout() {
        return R.layout.activity_area_device_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AreaDeviceAdapter(R.layout.item_area_device, list);
        recyclerView.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        areaId = bundle.getString("areaId");
        requestData(1);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                requestData(1);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                requestData(2);
                curPage += 1;
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        bindEvent();
    }

    private void bindEvent() {
        //长点击删除
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                requestDelete(position);
                return false;
            }
        });

    }

    private void requestDelete(int position) {
        AbstractRequest request = buildRequest(UrlUtils.AREA_DEVICE_DELETE, ContentConfig.LIST_TYPE, RequestMethod.POST, null);
        request.add("device_id", list.get(position).getDevice_id()+"");
        executeNetwork(3, holdonMsg, request);
        deletePostion=position;
    }

    private void requestData(int what) {
        AbstractRequest request = buildRequest(UrlUtils.AREA_DEVICE, ContentConfig.LIST_TYPE, RequestMethod.POST, AreaDeviceEntity.class);
        request.add("guid", getGuid());
        request.add("area_id", areaId);
        request.add("page", curPage);
        executeNetwork(what, holdonMsg, request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                list.clear();
                list.addAll((List<AreaDeviceEntity>) result.getData());
                break;
            case 2:
                list.addAll((List<AreaDeviceEntity>) result.getData());
                break;
            case 3:
                showToast("删除"+list.get(deletePostion).getDevice_name()+"成功！");
                list.remove(deletePostion);
                adapter.notifyDataSetChanged();
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
