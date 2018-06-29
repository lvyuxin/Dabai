package com.suomate.dabaiserver.utils.net;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.config.AppConfig;
import com.suomate.dabaiserver.utils.config.ContentConfig;
import com.suomate.dabaiserver.utils.LogUtils;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by 李平 on 2017/8/11.
 */

public abstract class AbstractRequest<T> extends RestRequest<Result<T>> {
    private String TAG = "response";

    public AbstractRequest(String url) {
        super(url);
    }

    public AbstractRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }


    // 这个方法由继承的子类去实现，解析成我们真正想要的数据类型。
    protected abstract T getResult(String data);

    @Override
    public Result<T> parseResponse(Headers headers, byte[] body) throws Exception {
        int responseCode = headers.getResponseCode(); // 响应码。
        LogUtils.e("getResponseCode",responseCode+"");
        // 响应码等于200，Http层成功。
        if (responseCode == 200) {
            // 响应码等于200，Http层成功。
            if (body == null || body.length == 0) {
                // 服务器包体为空。
                return new Result<>(ContentConfig.REQUEST_BODY_NULL, null, "body为空");
            } else {
                // 这里可以统一打印所有请求的数据哦：
                String bodyString = StringRequest.parseResponseString(headers, body);
                Log.e(TAG, "fancyresult: " + bodyString);
                JSONObject bodyObject;
                try {
                    bodyObject = JSON.parseObject(bodyString);
                } catch (Exception e) {
                    return new Result<>(-4515, null, bodyString);
                }

                String msg = bodyObject.getString("message");
                int code = bodyObject.getIntValue("code");
                String data = bodyObject.getString("data");
                if (bodyObject.getString("message") == null) {
                    T result = getResult(bodyString);
                    return new Result<>(code, result, msg);
                } else {
                    if (code == ContentConfig.REQUEST_SCUCESS) {
                        try {
                            T result = getResult(data);
                            return new Result<>(code, result, msg);
                        } catch (Exception e) {
                            return new Result<>(ContentConfig.PARSE_WRONG, null, "实体类解析出现问题，请检查实体类!");
                        }
                    } else if (code == ContentConfig.PARAMETER_INCOMPLETE || code == ContentConfig.REQUEST_FAILD || code == ContentConfig.EXSISTED) {
                        return new Result<>(code, null, msg);
                    } else {
                        return new Result<>(code, null, "没有和服务端约定的状态");
                    }
                }
            }
        } else {
            return new Result<>(ContentConfig.SERVER_WRONG, null, "服务器异常");
        }
    }

    private void updateStatus(JSONObject infoObj) {
        if (infoObj != null) {
            String sessionId = infoObj.getString("sessionId");
            String storeId = AppConfig.getInstance().getString("session_id", null);
            if (sessionId != null) {
                if (!sessionId.equals(storeId)) {
                    AppConfig.getInstance().putString("session_id", sessionId);
                }
            }
        }
    }

}
