package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.AreaSelectListBean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/26.
 */

public class AreaSelectListAdapter extends BaseQuickAdapter<AreaSelectListBean.DataBean,BaseViewHolder> {
    public AreaSelectListAdapter(int layoutResId, @Nullable List<AreaSelectListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaSelectListBean.DataBean item) {
        helper.setText(R.id.tv_item,item.getArea_name());

    }
}
