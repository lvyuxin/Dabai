package com.suomate.dabaiserver.bean;

/**
 * Created by lenovo on 2018/6/26.
 */

public class ConfigSingleDeviceBean {
    private int  port;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ConfigSingleDeviceBean(int port) {
        this.port = port;
    }


}
