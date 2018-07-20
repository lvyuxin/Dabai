package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.IoBean;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;

import java.util.List;

/**
 * Created by fanxi on 2018/7/12.
 */

public class IoDeviceAdapter extends BaseQuickAdapter<IoBean, BaseViewHolder> {

    public IoDeviceAdapter(int layoutResId, @Nullable List<IoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IoBean item) {
        helper.setText(R.id.tv_name, item.getDevice_name());
        CheckBox checkBox = helper.getView(R.id.check);
        TextView tvState = helper.getView(R.id.tv_state);
//        checkBox.setChecked(item.isSelect());
        helper.addOnClickListener(R.id.check);////装态判断  触发17，不触发18 ,长按19
        if (item.getControl_type().equals(ContentStr.Control_type.humanFeeling)) {
            if (item.getIotype() == Content.TYPE.NOT_TRIGGER) {
                tvState.setText("无人");
            } else if (item.getIotype() == Content.TYPE.TRIGGER) {
                tvState.setText("有人");

            }

        } else if (item.getControl_type().equals(ContentStr.Control_type.smokeFeeling)) {//烟感
            if (item.getIotype() == Content.TYPE.NOT_TRIGGER) {
                tvState.setText("无燃气泄漏");
            } else if (item.getIotype() ==  Content.TYPE.TRIGGER) {
                tvState.setText("有燃气泄漏");

            }
        } else if (item.getControl_type().equals(ContentStr.Control_type.gas)) {//燃气报警
            if (item.getIotype() == Content.TYPE.NOT_TRIGGER) {
                tvState.setText("无烟雾报警");
            } else if (item.getIotype() ==  Content.TYPE.TRIGGER) {
                tvState.setText("有烟雾报警");

            }
        } else if (item.getControl_type().equals(ContentStr.Control_type.panel) || item.getControl_type().equals(ContentStr.Control_type.intelligentPanel)) {
            if (item.getIotype() == Content.TYPE.NOT_TRIGGER) {
                tvState.setText("按下");
            } else if (item.getIotype() ==  Content.TYPE.TRIGGER) {
                tvState.setText("释放");
            } else if (item.getIotype() ==  Content.TYPE.LONG_PRESS) {
                tvState.setText("长按");
            }
//            else if (item.getIotype() == 4) {
//                tvState.setText("无");
//            }
        }

    }
}
