package com.suomate.dabaiserver.base.activity;


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
 * 网络基类
 */
public abstract class BaseNetActivity extends BaseDefaultUIActivity {
    protected  String holdonMsg ="请稍等";


    protected <T> void executeNetwork(int what, String message, AbstractRequest<T> request) {
        CallServer.getInstance().addRequest(what, request, new ImpHttpResponseListener<T>(this, message));
    }

    protected <T> void executeNetwork(int what, AbstractRequest<T> request) {
        executeNetwork(what, null, request);
    }

    protected <T> AbstractRequest buildRequest(String url,int  requestType, RequestMethod method, Class<T> clazz) {
        AbstractRequest request=null;
        if (requestType== Content.ENTITY_TYPE) {
            request = new EntityRequest<>(url, method, clazz);
        }else if(requestType== Content.LIST_TYPE){
            request = new EntityListRequest<>(url, method,clazz);
        }else if(requestType== Content.STRING_TYPE){
            request = new StringRequest(url, method);
        }
        if (hasCache()) {
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        }
        request.addHeader("guid", "123456975");
        return request;
    }

    /**
     * 设置是否有缓存
     *
     * @return true有缓存 false无缓存
     */
    protected boolean hasCache() {
        return false;
    }
    private class ImpHttpResponseListener<T> extends HttpResponseListener<T> {
        public ImpHttpResponseListener(BaseUIActivity context, String message) {
            super(context, message);
        }

        @Override
        public void onHttpStart(int what) {
            if (mStatusView != null && mRefreshLayout == null) {
                mStatusView.loading();
            }
        }

        @Override
        public void onHttpSucceed(int what, Response<Result<T>> response) {
            Result<T> tResult = response.get();
            int code = tResult.getCode();
            switch (code) {
                case Content.REQUEST_SCUCESS://请求成功
                    if (mStatusView != null) {
                        mStatusView.content();
                    }
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
                    if (mStatusView != null) {
                        mStatusView.error();
                    }
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
                if (mStatusView != null) {
                    mStatusView.noNet();
                }
                mHandleNoNetwork(what);
            }
        }

        @Override
        public void onHttpFinish(int what) {
            stopRefreshAndLoadMore();
        }
    }

    protected abstract <T> void mHandle200(int what, Result<T> result);

    protected abstract void mHandleReLogin(int what, Result result);
    protected abstract void mHandleFailed(int what);
    protected abstract void mHandleNoNetwork(int what);

}
