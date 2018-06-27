package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ReadJson;
import java.util.List;

/**
 * Created by lenovo on 2018/6/25.
 */

public class DeviceListAdapter extends BaseQuickAdapter<ReadJson.AlldeviceBean.DataBean,BaseViewHolder> {
    public DeviceListAdapter(int layoutResId, @Nullable List<ReadJson.AlldeviceBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReadJson.AlldeviceBean.DataBean item) {
        helper.setText(R.id.device_name,item.getName());
        if (item.getId()!=0) {
            helper.setText(R.id.tv_is_online,"在线");
        }else{
            helper.setText(R.id.tv_is_online,"不在线");
        }
    }
}
