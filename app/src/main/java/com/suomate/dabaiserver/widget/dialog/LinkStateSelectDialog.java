package com.suomate.dabaiserver.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CustromScenceBean;
import com.suomate.dabaiserver.bean.IoBean;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;
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
    private IoBean.DeviceOrSceneInfoBean deviceOrSceneInfoBean;

    public LinkStateSelectDialog(@NonNull Context context, int themeResId, boolean isShowBottom, Object object) {
        super(context, themeResId, isShowBottom, object);
        deviceOrSceneInfoBean= (IoBean.DeviceOrSceneInfoBean) object;
        pickerView = findViewById(R.id.picker);
        setData();
    }


    private void setData() {
        stateList = new ArrayList<>();
        switch (deviceOrSceneInfoBean.getControl_type()) {
            case ContentStr.Control_type.humanFeeling:
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.NOT_TRIGGER, "无人",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.TRIGGER, "有人",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                break;
            case ContentStr.Control_type.gas:
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.NOT_TRIGGER, "无燃气泄漏",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.TRIGGER, "有燃气泄漏",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                break;
            case ContentStr.Control_type.smokeFeeling:
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.NOT_TRIGGER, "无烟雾报警",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.TRIGGER, "有烟雾报警",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                break;
            case ContentStr.Control_type.panel:
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.NOT_TRIGGER, "按下",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.TRIGGER, "释放",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.LONG_PRESS, "长按",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                break;
            case ContentStr.Control_type.intelligentPanel:
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.NOT_TRIGGER, "按下",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.TRIGGER, "释放",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
                stateList.add(new CustromScenceBean.StateBean(Content.TYPE.LONG_PRESS, "长按",deviceOrSceneInfoBean.getJson_type(),deviceOrSceneInfoBean.getDevice_or_scene_id()));
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
