package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.SelectTimeAdapter;
import com.suomate.dabaiserver.bean.TimeBean;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxi on 2018/7/12.
 */

public class LinkWeekDialog extends BaseDialog {
    private RecyclerView recyclerView;
    private SelectTimeAdapter adapter;
    private List<TimeBean> list;
    private TextView tvCancle,tvSure;
    private String strWeek="1,2,3,4,5,6,0";

    public LinkWeekDialog(@NonNull Context context, int themeResId, boolean isShowBottom) {
        super(context,themeResId,isShowBottom);
    }
    @Override
    protected int bindLayout() {
        return R.layout.dialog_link_week;
    }

    @Override
    protected void initViews() {
        recyclerView=findViewById(R.id.recycler);
        tvCancle=findViewById(R.id.dialog_cancel);
        tvSure=findViewById(R.id.ldialog_sure);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        setData();
    }
    private void setData() {
        list = new ArrayList<>();
        list.add(new TimeBean("每天", true, "1,2,3,4,5,6,0"));
        list.add(new TimeBean("星期一", true, "1"));
        list.add(new TimeBean("星期二", true, "2"));
        list.add(new TimeBean("星期三", true, "3"));
        list.add(new TimeBean("星期四", true,"4"));
        list.add(new TimeBean("星期五", true,"5"));
        list.add(new TimeBean("星期六", true,"6"));
        list.add(new TimeBean("星期天", true,"0"));
        adapter=new SelectTimeAdapter(R.layout.item_time_select,list);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void bindEvent() {
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedWeek();
                dismiss();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isSelect = list.get(position).isSelect();
                list.get(position).setSelect(!isSelect);
                if(position == 0){
                    changeAllState(list.get(position).isSelect());
                }else{
                    int count = 0;
                    for (int i = 1; i < list.size(); i++) {
                        if (list.get(i).isSelect()) {
                            count = +1;
                        }
                    }
                    if (count <7) {
                        list.get(0).setSelect(false);
                    }else{
                        list.get(0).setSelect(true);
                    }
                }
                adapter.notifyDataSetChanged();
            }

        });

    }
    private void changeAllState(boolean state){
        for(int i=0;i<list.size();i++){
            list.get(i).setSelect(state);
        }
    }

    private void getSelectedWeek(){
        if (list.get(0).isSelect()) {
            strWeek = list.get(0).getType();
        } else {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).isSelect()) {
                    strWeek=strWeek+list.get(i).getType()+",";
                }
            }
            strWeek= strWeek.substring(0, strWeek.lastIndexOf(","));
        }
//        LogUtils.e("fancystrWeek:"+strWeek);
        EventBus.getDefault().post(strWeek);
    }

}
