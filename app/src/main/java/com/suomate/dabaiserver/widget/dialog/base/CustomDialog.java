package com.suomate.dabaiserver.widget.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.suomate.dabaiserver.base.activity.BaseUIActivity;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.utils.net.CallServer;
import com.suomate.dabaiserver.utils.net.EntityListRequest;
import com.suomate.dabaiserver.utils.net.EntityRequest;
import com.suomate.dabaiserver.utils.net.HttpResponseListener;
import com.suomate.dabaiserver.utils.net.StringRequest;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by fanxi on 2018/7/17.
 */

public abstract  class CustomDialog  extends Dialog{

    protected String holdonMsg = "请稍等";
    protected Context mContext;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.mContext=context;
//        initViews();
//        bindEvent();
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
//        initViews();
//        bindEvent();
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext=context;
//        initViews();
//        bindEvent();
    }

    /**
     * 绑定视图id    (setContentView());
     *
     * @return 视图id
     */
    protected abstract int bindLayout();
    /**
     * 控件的初始化
     */

    protected abstract void initViews();

    protected abstract void bindEvent();

    protected <T> void executeNetwork(int what, String message, AbstractRequest<T> request) {
        CallServer.getInstance().addRequest(what, request, new ImpHttpResponseListener<T>(((BaseUIActivity) getOwnerActivity()), message));
    }

    protected <T> void executeNetwork(int what, AbstractRequest<T> request) {
        executeNetwork(what, null, request);
    }

    protected <T> AbstractRequest buildRequest(String url, int requestType, RequestMethod method, Class<T> clazz) {
        AbstractRequest request = null;
        if (requestType == Content.ENTITY_TYPE) {
            request = new EntityRequest<>(url, method, clazz);
        } else if (requestType == Content.LIST_TYPE) {
            request = new EntityListRequest<>(url, method, clazz);
        } else if (requestType == Content.STRING_TYPE) {
            request = new StringRequest(url, method);
        }
        if (hasCache()) {
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }
        request.addHeader("guid", "123456975");
        return request;
    }
    private class ImpHttpResponseListener<T> extends HttpResponseListener<T> {

        public ImpHttpResponseListener(BaseUIActivity context, String message) {
            super(context, message);
        }

        @Override
        public void onHttpStart(int what) {

        }

        @Override
        public void onHttpSucceed(int what, Response<Result<T>> response) {
            Result<T> tResult = response.get();
            int code = tResult.getCode();
            switch (code) {
                case Content.REQUEST_SCUCESS://请求成功
                    mHandle200(what, tResult);
                    break;
                case 0:

                    mHandle200(what, tResult);
                    break;
                case Content.REQUEST_FAILD://请求失败
                    showToast(tResult.getMessage());
                    break;
                case Content.PARAMETER_INCOMPLETE://参数不全
                    showToast(tResult.getMessage());
                    break;
                case Content.EXSISTED://已经存在
                    showToast(tResult.getMessage());
                    break;
                case Content.SERVER_WRONG://服务器异常
                    showToast("code:" + code + "\n" + "msg :" + tResult.getMessage());
                    mHandleFailed(what);
                    break;
                case Content.REQUEST_BODY_NULL://请求包体为空
                    showToast("code:" + code + "\n" + "msg :" + tResult.getMessage());
                    break;
                case Content.NOT_JSON_DATA://服务端给的数据不是json数据
                    showToast("code:" + code + "\n" + "msg :" + tResult.getMessage());
                    break;
                case Content.PARSE_WRONG://解析异常
                    showToast(tResult.getMessage());
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onHttpFailed(int what, Response<Result<T>> response) {
            Exception e = response.getException();
            if (e instanceof NetworkError) {

                mHandleNoNetwork(what);
            }
        }

        @Override
        public void onHttpFinish(int what) {
        }
    }


    /**
     * 吐司
     *
     * @param content 内容
     */
    public void showToast(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
    /**
     * 设置是否有缓存
     *
     * @return true有缓存 false无缓存
     */
    protected boolean hasCache() {
        return false;
    }

    protected abstract <T> void mHandle200(int what, Result<T> result);

    protected abstract void mHandleReLogin(int what, Result result);
    protected abstract void mHandle403(int what, Result result);
    protected abstract void mHandleFailed(int what);
    protected abstract void mHandleNoNetwork(int what);

}
