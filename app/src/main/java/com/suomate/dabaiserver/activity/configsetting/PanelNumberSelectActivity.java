package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.utils.config.ContentStr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PanelNumberSelectActivity extends BaseActivity {
    @BindView(R.id.reycler)
    RecyclerView recyclerView;
    private BaseQuickAdapter adapter;
    private List<String> list = new ArrayList<>();
    private int panel_number;
    Intent intent;
    private int   type;
    private String control_type;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_test, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item, item);
            }
        };
       intent= getIntent();
        Bundle bundle = intent.getExtras();
         type = bundle.getInt("type");
        if (type == 1) {//io面板
            control_type=ContentStr.Control_type.panel;
            list.add(ContentStr.Control_type_name.io_panel1);
            list.add(ContentStr.Control_type_name.io_panel2);
            list.add(ContentStr.Control_type_name.io_panel3);
            list.add(ContentStr.Control_type_name.io_panel4);
            list.add(ContentStr.Control_type_name.io_panel5);
            list.add(ContentStr.Control_type_name.io_panel6);
        } else {
            control_type=ContentStr.Control_type.intelligentPanel;
            list.add(ContentStr.Control_type_name.intelligent_panel1);
            list.add(ContentStr.Control_type_name.intelligent_panel2);
            list.add(ContentStr.Control_type_name.intelligent_panel3);
            list.add(ContentStr.Control_type_name.intelligent_panel4);
            list.add(ContentStr.Control_type_name.intelligent_panel5);
            list.add(ContentStr.Control_type_name.intelligent_panel6);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        bindEvent();
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                panel_number = position + 1;
                intent.putExtra("panel_number",panel_number);
                intent.putExtra("control_type",control_type);
                intent.putExtra("control_type_name",list.get(position));
                setResult(RESULT_OK,intent);
                showToast("选择成功！");
                finish();
            }
        });

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_panel_number_select;
    }


}
