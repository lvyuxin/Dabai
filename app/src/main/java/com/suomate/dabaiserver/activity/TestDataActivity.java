package com.suomate.dabaiserver.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.DeviceListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class TestDataActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private List<DeviceListBean> list;
    private BaseQuickAdapter adapter;
    private ArrayList<String> datas = new ArrayList<>();
    private int curPage=1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_data);
//        ButterKnife.bind(this);
////        tvShow = findViewById(R.id.tv_show);
//    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_test_data;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
//        recyclerView=findViewById(R.id.recycler);
//        refreshLayout=findViewById(R.id.refreshLayout);
//        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AbstractRequest request = buildRequest(UrlUtils.getDeviceList, 1, RequestMethod.GET, DeviceListBean.class);
        request.add("guid", "123456975");
        request.add("page",curPage);
        executeNetwork(1, "请稍后", request);
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_test, datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item, item);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("点击了第" + position + "项");
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                AbstractRequest request = buildRequest(UrlUtils.getDeviceList, 1, RequestMethod.GET, DeviceListBean.class);
                request.add("guid", "123456975");
                request.add("page",curPage);
                executeNetwork(1, "请稍后", request);
                refreshlayout.finishRefresh(3000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(3000/*,false*/);//传入false表示加载失败
                AbstractRequest request = buildRequest(UrlUtils.getDeviceList, 1, RequestMethod.GET, DeviceListBean.class);
                request.add("guid", "123456975");
                request.add("page",curPage);
                executeNetwork(curPage, "请稍后", request);
                curPage+=1;
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

            }
        });

    }


    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        Log.d("fancyresult2", result.toString());
        list = (List<DeviceListBean>) result.getData();
        for (int i = 0; i < list.size(); i++) {
            datas.add(list.get(i).getDevice_name());
        }
        adapter.notifyDataSetChanged();
    }

}
