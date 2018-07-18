package com.suomate.dabaiserver.adapter.function;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.FunctionClassifyBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/17.
 */

public class FunctionListAdapter extends BaseQuickAdapter<FunctionClassifyBean, BaseViewHolder> {
    private Context context;

    public FunctionListAdapter(int layoutResId, @Nullable List<FunctionClassifyBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FunctionClassifyBean item) {
        ImageView ivBg=helper.getView(R.id.iv_function_bg);
        if (TextUtils.isEmpty(item.getClassify_background_img())) {
            helper.setImageResource(R.id.iv_function_bg, R.mipmap.moren_img);
        } else {
            Glide.with(context).load(item.getClassify_background_img()).into(ivBg);
        }
        helper.setText(R.id.tv_name,item.getClassify_name());
    }
}
