package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.SelectTimeAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.TimeBean;
import com.suomate.dabaiserver.bean.TimeLaunchBean;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.widget.TitleBar;
import com.suomate.dabaiserver.widget.dialog.ChooseTimeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class ScenceStartConditionActivity extends BaseActivity {
    private Intent intent;
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.number_pickerview_left)
    NumberPickerView pickerViewLeft;
    @BindView(R.id.number_pickerview_right)
    NumberPickerView pickerViewRight;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.link_time_rb)
    RadioButton rbTime;

    private SelectTimeAdapter adapter;
    private String hour,minute1;
    private int modeType;
    private int chooseMin,chooseHour;
    private List<TimeBean> weeksList = new ArrayList<>();
    private String strWeek = "",launchStr="";
    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_start_mudle;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        intent = getIntent();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SelectTimeAdapter(R.layout.item_time_select, weeksList);
        recyclerView.setAdapter(adapter);
        setData();
        bindEvent();
        setPickerShadowData();
        setPickerShadowDataRight();
    }

    @OnClick({R.id.tv_instant_start, R.id.tv_time_start,R.id.btn_cancle,R.id.btn_sure,R.id.link_time_rb,R.id.link_week_rb})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_instant_start:
                intent.putExtra("type", 1);
                modeType = 1;
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_time_start:
                modeType = 2;
                new ChooseTimeDialog(this,R.style.basedialog_style,true).show();
                break;
            case R.id.btn_cancle:
                break;
            case R.id.btn_sure:
                break;
            case R.id.link_time_rb://联动时间

                break;
            case R.id.link_week_rb://周
                break;



//            case R.id.tv_linkage_start:
//                modeType = 3;
//                break;
//            case R.id.tv_positon_start:
//                modeType = 4;
//                break;
        }
    }

    private void setData() {
        weeksList.add(new TimeBean("每天", true, "1,2,3,4,5,6,0"));
        weeksList.add(new TimeBean("星期一", true, "1"));
        weeksList.add(new TimeBean("星期二", true, "2"));
        weeksList.add(new TimeBean("星期三", true, "3"));
        weeksList.add(new TimeBean("星期四", true,"4"));
        weeksList.add(new TimeBean("星期五", true,"5"));
        weeksList.add(new TimeBean("星期六", true,"6"));
        weeksList.add(new TimeBean("星期天", true,"0"));
        adapter.notifyDataSetChanged();
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
                LogUtils.d(TAG,"chooseMin:"+chooseMin);
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



    private void bindEvent() {
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                getIntent().putExtra("type",modeType);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isSelect = weeksList.get(position).isSelect();
                weeksList.get(position).setSelect(!isSelect);
                if(position == 0){
                    changeAllState(weeksList.get(position).isSelect());
                }else{
                    int count = 0;
                    for (int i = 1; i < weeksList.size(); i++) {
                        if (weeksList.get(i).isSelect()) {
                            count = +1;
                        }
                    }
                    if (count <7) {
                        weeksList.get(0).setSelect(false);
                    }else{
                        weeksList.get(0).setSelect(true);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void changeAllState(boolean state){
        for(int i=0;i<weeksList.size();i++){
            weeksList.get(i).setSelect(state);
        }
    }




    private void getSelectedWeek(){
        if (weeksList.get(0).isSelect()) {
            strWeek = weeksList.get(0).getType();
        } else {
            for (int i = 1; i < weeksList.size(); i++) {
                if (weeksList.get(i).isSelect()) {
                    strWeek=strWeek+weeksList.get(i).getType()+",";
                }
            }
            strWeek= strWeek.substring(0, strWeek.lastIndexOf(","));
        }
        TimeLaunchBean bean = new TimeLaunchBean();
        bean.setHour(chooseHour+"");
        bean.setMinute(chooseMin+"");
        bean.setWeek(strWeek);

        launchStr = JSON.toJSONString(bean);
//        EventBus.getDefault().post(bean);
    }
}
