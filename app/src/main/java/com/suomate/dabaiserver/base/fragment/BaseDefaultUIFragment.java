package com.suomate.dabaiserver.base.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.utils.config.AppConfig;
import com.suomate.dabaiserver.widget.EasyStatusView;

/**
 * UI默认实现
 */

public class BaseDefaultUIFragment extends BaseUIFragment {
    /**
     * 状态控制view
     */
    protected EasyStatusView mStatusView;
    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;

    @Override
    protected int bindLayout() {
        return R.layout.default_override;
    }

    @Override
    protected void initViews() {
        initStatusView();
    }


    private void initStatusView() {
        mStatusView = fv(R.id.statusView);
    }

    protected String getGuid() {
        if (TextUtils.isEmpty(AppConfig.getInstance().getString("guid", null))) {
            return "123456975";
        } else {
            return AppConfig.getInstance().getString("guid", null);
        }

    }

    protected void stopRefreshAndLoadMore() {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
            mRefreshLayout.setLoadmoreFinished(false);
        }
        if (mRefreshLayout != null && mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadmore();
        }
    }

    /**
     * 没有更多了
     */
    protected void loadMoreOver() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadmore();
            mRefreshLayout.setLoadmoreFinished(true);
        }
    }

    protected void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
