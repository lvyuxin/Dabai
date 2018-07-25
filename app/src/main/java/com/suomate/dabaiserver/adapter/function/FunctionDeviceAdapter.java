package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.FunctionDeviceListBean;
import com.suomate.dabaiserver.utils.DeviceUtils;

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
        if (!TextUtils.isEmpty(item.getDevice_name())) {
            helper.setText(R.id.area_device_name,item.getDevice_name());
        }
        ImageView ivIcon=helper.getView(R.id.area_device_iv);
        helper.addOnClickListener(R.id.check_switch);
        DeviceUtils.setIcon(ivIcon,item.getDevice_icon());

    }
}
