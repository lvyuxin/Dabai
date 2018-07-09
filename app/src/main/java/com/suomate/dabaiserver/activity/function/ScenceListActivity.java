package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.ScenceListAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.RequestInfoBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScenceListActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<RequestInfoBean.ScenceBean> list = new ArrayList<>();
    private ScenceListAdapter adapter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ScenceListAdapter(R.layout.item_scence_list,list);
        recycler.setAdapter(adapter);
        bindEvent();
        requestData();
    }

    private void requestData() {
        //场景列表
        AbstractRequest request1 = buildRequest(UrlUtils.SCENCE_LIST, Content.LIST_TYPE, RequestMethod.GET, RequestInfoBean.ScenceBean.class);
        request1.add("guid", getGuid());
        executeNetwork(1, holdonMsg, request1);
    }

    private void bindEvent() {
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                startActivity(AddSceneActivity.class, null);
            }
        });

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //场景列表
                AbstractRequest request1 = buildRequest(UrlUtils.DELETE_SCENCE, Content.STRING_TYPE, RequestMethod.POST,null);
                request1.add("sceneId", list.get(position).getScene_id());
                executeNetwork(1, holdonMsg, request1);
                return false;
            }
        });
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                list.addAll((List<RequestInfoBean.ScenceBean>)result.getData());
                adapter.notifyDataSetChanged();
                break;
            case 2:
                showToast("删除场景成功！");
                break;
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_list;
    }

}
