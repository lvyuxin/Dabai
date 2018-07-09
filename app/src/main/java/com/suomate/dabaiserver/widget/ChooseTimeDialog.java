package com.suomate.dabaiserver.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.SelectTimeAdapter;
import com.suomate.dabaiserver.bean.TimeBean;
import com.suomate.dabaiserver.bean.TimeLaunchBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/8.
 */

public class ChooseTimeDialog extends BaseDialog{
    private NumberPickerView pickerViewLeft,pickerViewRight;
    private RecyclerView recyclerView;
    private SelectTimeAdapter adapter;
    private List<TimeBean> list = new ArrayList<>();
    private Button btnConfirm;
    private String strWeek = "";
    private int chooseHour,chooseMin;

    public ChooseTimeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_choosetime);
        init();
        setRecyclerView();
        setData();
    }

    private void init() {
        pickerViewLeft = findViewById(R.id.number_pickerview_left);
        pickerViewRight = findViewById(R.id.number_pickerview_right);
        recyclerView = findViewById(R.id.time_recycler);
        btnConfirm = findViewById(R.id.btn_confirm);
        setPickerShadowData();
        setPickerShadowDataRight();
        setConfirm();
    }

    private void setRecyclerView(){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new SelectTimeAdapter(R.layout.item_time_select, list);
        recyclerView.setAdapter(adapter);
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

    private void setPickerShadowData() {
        String[] showNums = getShowNums(24);
        pickerViewLeft.setDisplayedValues(showNums);
        pickerViewLeft.setMinValue(0);
        pickerViewLeft.setMaxValue(showNums.length - 1);
        pickerViewLeft.setValue(0);   //默认边框宽度
        pickerViewLeft.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                chooseHour = oldVal;
            }
        });
    }

    private void setPickerShadowDataRight() {
        String[] showNums = getShowNums(60);
        pickerViewRight.setDisplayedValues(showNums);
        pickerViewRight.setMinValue(0);
        pickerViewRight.setMaxValue(showNums.length - 1);
        pickerViewRight.setValue(0);   //默认边框宽度
        pickerViewRight.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                chooseMin = oldVal;
            }
        });
    }

    private String[] getShowNums(int maxValue){
        String[] nums = new String[maxValue];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + "";
            if(nums[i].length() == 1){
                nums[i] = "0" + nums[i];
            }
        }
        return nums;
    }

    private void setData() {
        list.add(new TimeBean("每天", true, "1,2,3,4,5,6,0"));
        list.add(new TimeBean("星期一", true, "1"));
        list.add(new TimeBean("星期二", true, "2"));
        list.add(new TimeBean("星期三", true, "3"));
        list.add(new TimeBean("星期四", true,"4"));
        list.add(new TimeBean("星期五", true,"5"));
        list.add(new TimeBean("星期六", true,"6"));
        list.add(new TimeBean("星期天", true,"0"));
        adapter.notifyDataSetChanged();
    }

    private void setConfirm(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedWeek();
                dismiss();
            }
        });
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

        TimeLaunchBean bean = new TimeLaunchBean();
        bean.setHour(chooseHour+"");
        bean.setMinute(chooseMin+"");
        bean.setWeek(strWeek);
        EventBus.getDefault().post(bean);
    }
}
