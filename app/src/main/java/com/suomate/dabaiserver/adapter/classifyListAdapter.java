package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ClassifyListBean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/27.
 */

public class classifyListAdapter extends BaseQuickAdapter<ClassifyListBean.DataBean, BaseViewHolder> {
    public classifyListAdapter(int layoutResId, @Nullable List<ClassifyListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyListBean.DataBean item) {
        helper.setText(R.id.tv_item, item.getClassify_name());
    }
}
