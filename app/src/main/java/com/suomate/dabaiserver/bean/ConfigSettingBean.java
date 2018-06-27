package com.suomate.dabaiserver.bean;

/**
 * Created by lenovo on 2018/6/21.
 */

public class ConfigSettingBean {
    private String name;
    private int deviceCount;

    public ConfigSettingBean(String name, int deviceCount) {
        this.name = name;
        this.deviceCount = deviceCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }
}
