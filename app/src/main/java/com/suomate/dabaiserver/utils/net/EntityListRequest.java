package com.suomate.dabaiserver.utils.net;

import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.List;

/**
 * Created by lenovo on 2018/6/20.
 */

public class EntityListRequest<T> extends AbstractRequest<List<T>>{
    private Class<T> clazz;
    public EntityListRequest(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url,requestMethod);
        this.clazz=clazz;
    }

    @Override
    protected List<T> getResult(String data) {
        return JSON.parseArray(data,clazz);
    }
}
