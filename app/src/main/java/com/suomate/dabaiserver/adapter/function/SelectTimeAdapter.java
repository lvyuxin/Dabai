package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.TimeBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/6.
 */

public class SelectTimeAdapter extends BaseQuickAdapter<TimeBean,BaseViewHolder> {
    public SelectTimeAdapter(int layoutResId, @Nullable List<TimeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeBean item) {
        helper.setText(R.id.tv_time,item.getName());
        CheckBox checkBox = helper.getView(R.id.check_time);
        checkBox.setChecked(item.isSelect());
    }
}
