package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.FunctionDeviceListBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/4.
 */

public class FunctionDeviceAdapter extends BaseQuickAdapter<FunctionDeviceListBean,BaseViewHolder> {
    public FunctionDeviceAdapter(int layoutResId, @Nullable List<FunctionDeviceListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FunctionDeviceListBean item) {
        helper.setText(R.id.area_device_name,item.getDevice_name());
        helper.addOnClickListener(R.id.switch_btn);

    }
}
