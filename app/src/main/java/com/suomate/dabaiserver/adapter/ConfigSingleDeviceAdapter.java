package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ConfigSingleDeviceBean;
import com.suomate.dabaiserver.utils.DeviceUtils;

import java.util.List;
/**
 * Created by lenovo on 2018/6/26.
 */
public class ConfigSingleDeviceAdapter extends BaseQuickAdapter<ConfigSingleDeviceBean, BaseViewHolder> {
    public ConfigSingleDeviceAdapter(int layoutResId, @Nullable List<ConfigSingleDeviceBean> data) {
            super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConfigSingleDeviceBean item) {
        helper.setText(R.id.port_tv, item.getPort()+"");
        TextView tvAdd=helper.getView(R.id.add);
        ImageView ivIcon=helper.getView(R.id.iv_icon);
        DeviceUtils.setIcon(ivIcon,item.getIconType());
//        if (TextUtils.isEmpty(item.getIconType())) {
//           helper.setImageResource(R.id.iv_icon,R.mipmap.icon_moren);
//        }else{
//
        if (TextUtils.isEmpty(item.getName())) {
            tvAdd.setVisibility(View.VISIBLE);
        }else{
            tvAdd.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_occupyed,item.getName());
    }
}
