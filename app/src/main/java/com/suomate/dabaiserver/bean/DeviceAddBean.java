package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/4.
 */

public class DeviceAddBean {
    private String name;
    private String type;

    public DeviceAddBean(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
