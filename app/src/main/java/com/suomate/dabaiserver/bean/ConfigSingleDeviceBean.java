package com.suomate.dabaiserver.bean;

/**
 * Created by lenovo on 2018/6/26.
 */

public class ConfigSingleDeviceBean {
    private int  port;
    private String name;
    private DeviceInfoBean deviceInfoBean;
    private String iconType;

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public DeviceInfoBean getDeviceInfoBean() {
        return deviceInfoBean;
    }

    public void setDeviceInfoBean(DeviceInfoBean deviceInfoBean) {
        this.deviceInfoBean = deviceInfoBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
