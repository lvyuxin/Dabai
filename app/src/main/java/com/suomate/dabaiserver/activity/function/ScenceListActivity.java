package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.ScenceListAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.RequestInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.ChooseOperationDialog;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScenceListActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private List<RequestInfoBean.ScenceBean> list = new ArrayList<>();
    private ScenceListAdapter adapter;
    private int curPage = 1;
    private ChooseOperationDialog chooseOperationDialog;
    private int curPosition;
    public static final int what_fresh = 1, what_loadmore = 2, what_delete = 3, what_send_order = 4;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScenceListAdapter(R.layout.item_scence_list, list);
        mRecyclerView.setAdapter(adapter);
        chooseOperationDialog = new ChooseOperationDialog(ScenceListActivity.this, R.style.basedialog_style);
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                startActivity(AddSceneActivity.class, null);
            }
        });
        bindEvent();
        bindFreshEvent();
        requestData(1);
    }

    private void bindFreshEvent() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mRefreshLayout.finishRefresh(3000/*,false*/);//传入false表示刷新失败
                curPage = 1;
                requestData(what_fresh);

            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mRefreshLayout.finishLoadMore(3000/*,false*/);//传入false表示加载失败
                curPage++;
                requestData(what_loadmore);
                mRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }

    private void requestData(int what) {
        //场景列表
        AbstractRequest request1 = buildRequest(UrlUtils.SCENCE_LIST, Content.LIST_TYPE, RequestMethod.GET, RequestInfoBean.ScenceBean.class);
        request1.add("guid", getGuid());
        request1.add("page", curPage);
        executeNetwork(what, holdonMsg, request1);
    }

    private void bindEvent() {
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                curPosition=position;
                chooseOperationDialog.show();
                return false;
            }
        });
        chooseOperationDialog.setCallBackIml(new CallBackIml() {
            @Override
            public void confirmHandle() {
                super.confirmHandle();
                //场景列表

            }
        });
        chooseOperationDialog.setCallBackIml(new CallBackIml(){
            @Override
            public void callBack(int position) {
                super.callBack(position);
                switch (position) {
                    case 1:
                        Bundle bundle=new Bundle();
                        bundle.putInt("sceneId",list.get(curPosition).getScene_id());
                        startActivity(ScenceDetailActivity.class,bundle);
                        break;
                    case 2://删除场景
                        AbstractRequest request1 = buildRequest(UrlUtils.DELETE_SCENCE, Content.STRING_TYPE, RequestMethod.POST, null);
                        request1.add("sceneId", list.get(curPosition).getScene_id());
                        executeNetwork(what_delete, holdonMsg, request1);
                        break;

                }

            }
        });

        //点击一键启动，走接口
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_scence_start:
                        startSenceOrder(position, what_send_order);
                        break;
                }
            }
        });
    }

    private void startSenceOrder(int position, int what) {
        AbstractRequest request = buildRequest(UrlUtils.SEND_ORDER, Content.STRING_TYPE, RequestMethod.GET, null);
        String content = "SET;00000001;" + "{254.16.0." + list.get(position).getNumber() + "};" + "$0D$0A";
        request.add("guid", getGuid() + "");
        request.add("extend", "0");
        request.add("name", list.get(position).getScene_name());
        request.add("content", content);
        LogUtils.d(TAG, content);
        executeNetwork(what, holdonMsg, request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case what_fresh:
                list.clear();
                list.addAll((List<RequestInfoBean.ScenceBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;
            case what_loadmore:
                list.addAll((List<RequestInfoBean.ScenceBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;
            case what_delete:
                list.remove(curPosition);
                showToast("删除场景成功！");
                adapter.notifyDataSetChanged();
                break;
            case what_send_order:
                break;
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_list;
    }

}
