package com.suomate.dabaiserver.adapter.function;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.IoBean;
import com.suomate.dabaiserver.utils.CallBackIml;
import com.suomate.dabaiserver.widget.dialog.LinkStateSelectDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxi on 2018/7/12.
 */

public class IoDeviceAdapter extends BaseQuickAdapter<IoBean, BaseViewHolder> {
    private Context context;
    private ItemIoDeviceAdapter adapter;

    private CallBackIml callBackIml;
    private LinkStateSelectDialog linkStateSelectDialog;


    public CallBackIml getCallBackIml() {
        return callBackIml;
    }

    public void setCallBackIml(CallBackIml callBackIml) {
        this.callBackIml = callBackIml;
    }

    public IoDeviceAdapter(int layoutResId, @Nullable List<IoBean> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final IoBean item) {
        helper.setText(R.id.tv_classify_name,item.getClassify_name());
        RecyclerView recyclerView = helper.getView(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
         final List<IoBean.DeviceOrSceneInfoBean> itemList=new ArrayList<>();
        itemList.addAll(item.getDeviceOrSceneInfo());
        adapter=new ItemIoDeviceAdapter(R.layout.item_io_layout,itemList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {//触发17，不触发18 ,长按19
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String controlType = itemList.get(position).getControl_type();
//                int contentType = 0;
//                switch (controlType) {
//                    case ContentStr.Control_type.humanFeeling:
//                        contentType = Content.TYPE.TYPE_HUMAN_FEELING;
//                        break;
//                    case ContentStr.Control_type.gas:
//                        contentType = Content.TYPE.TYPE_GAS;
//                        break;
//                    case ContentStr.Control_type.smokeFeeling:
//                        contentType = Content.TYPE.TYPE_SMOKE;
//                        break;
//                    case ContentStr.Control_type.panel:
//                        contentType = Content.TYPE.TYPE_IO_PANEL;
//                        break;
//                    case ContentStr.Control_type.intelligentPanel:
//                        contentType = Content.TYPE.TYPE_INTELLIGENT_PANEL;
//                        break;
//                }
                linkStateSelectDialog = new LinkStateSelectDialog(context, R.style.detail_dialog_style, true, itemList.get(position));
                linkStateSelectDialog.show();
            }

        });


        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.check:
                        itemList.get(position).setSelect(!itemList.get(position).isSelect());
//                        callBackIml.callBack(itemList.get(i);
                        break;
                }
            }
        });


    }
}
