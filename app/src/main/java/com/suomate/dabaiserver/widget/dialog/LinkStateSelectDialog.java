package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CustromScenceBean;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.widget.dialog.base.BaseDialog1;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by fanxi on 2018/7/13.
 */

public class LinkStateSelectDialog extends BaseDialog1 {
    private NumberPickerView pickerView;
    private List<CustromScenceBean.StateBean> stateList;
    private String[] values;
    private CustromScenceBean.StateBean stateBean;
    private int curPosition;
    private int type;

    public LinkStateSelectDialog(@NonNull Context context, int themeResId, boolean isShowBottom, String title, int type) {

        super(context, themeResId, isShowBottom, title);
        this.type = type;
        setData();
    }

    private void setData() {
        stateList = new ArrayList<>();
        switch (type) {
            case Content.TYPE.TYPE_HUMAN_FEELING:
                stateList.add(new CustromScenceBean.StateBean(1, "无人"));
                stateList.add(new CustromScenceBean.StateBean(2, "有人"));
                break;
            case Content.TYPE.TYPE_GAS:
                stateList.add(new CustromScenceBean.StateBean(1, "无燃气泄漏"));
                stateList.add(new CustromScenceBean.StateBean(2, "有燃气泄漏"));
                break;
            case Content.TYPE.TYPE_SMOKE:
                stateList.add(new CustromScenceBean.StateBean(1, "无烟雾报警"));
                stateList.add(new CustromScenceBean.StateBean(2, "有烟雾报警"));
                break;
            case Content.TYPE.TYPE_IO_PANEL:
                stateList.add(new CustromScenceBean.StateBean(1, "按下"));
                stateList.add(new CustromScenceBean.StateBean(2, "释放"));
                stateList.add(new CustromScenceBean.StateBean(3, "长按"));
                break;
            case Content.TYPE.TYPE_INTELLIGENT_PANEL:
                stateList.add(new CustromScenceBean.StateBean(1, "按下"));
                stateList.add(new CustromScenceBean.StateBean(2, "释放"));
                stateList.add(new CustromScenceBean.StateBean(3, "长按"));
                break;
        }
        if (stateList.size() > 0) {
            values = new String[stateList.size()];
            for (int i = 0; i < stateList.size(); i++) {
                values[i] = stateList.get(i).getName() + "";
            }
        }
        setPickerShadowData();
    }


    @Override
    protected int bindLayout() {
        return R.layout.dialog_link_state;
    }

    @Override
    protected void initViews() {
        pickerView = findViewById(R.id.picker);
    }


    private void setPickerShadowData() {
        pickerView.setDisplayedValues(values);
        pickerView.setMinValue(0);
        pickerView.setMaxValue(values.length - 1);
        pickerView.setValue(0);   //默认边框宽度
        pickerView.setOnValueChangeListenerInScrolling(new NumberPickerView.OnValueChangeListenerInScrolling() {
            @Override
            public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {
                curPosition = oldVal;
            }
        });
    }


    @Override
    protected void bindEvent() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(stateList.get(curPosition));
                dismiss();
            }
        });
    }
}
