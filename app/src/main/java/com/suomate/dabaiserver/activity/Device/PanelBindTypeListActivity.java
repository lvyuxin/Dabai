package com.suomate.dabaiserver.activity.Device;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.area.PanelBindTypeAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.CommonBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PanelBindTypeListActivity extends BaseActivity {
    @BindView(R.id.recyler)
    RecyclerView recycler;
    private PanelBindTypeAdapter adapter;
    private List<CommonBean.BindTypeBean> list = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.activity_panel_bind_device;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        list.add(new CommonBean.BindTypeBean("开关", 1));
        list.add(new CommonBean.BindTypeBean("调光", 2));
        list.add(new CommonBean.BindTypeBean("窗帘", 3));
        list.add(new CommonBean.BindTypeBean("场景", 4));
        list.add(new CommonBean.BindTypeBean("地暖", 5));
        list.add(new CommonBean.BindTypeBean("新风", 6));
        list.add(new CommonBean.BindTypeBean("空调", 7));
        list.add(new CommonBean.BindTypeBean("清楚", 8));
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PanelBindTypeAdapter(R.layout.item_bind_type, list);
        recycler.setAdapter(adapter);
        bindEvent();
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", list.get(position).getType());
                startActivity(PanelBindDeviceActivity.class,bundle);
            }
        });
    }

}
