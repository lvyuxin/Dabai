package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/6.
 */

public class TimeBean {
    private String name="";
    private boolean isSelect;
    private String type;

    public TimeBean(String name, boolean isSelect, String type) {
        this.name = name;
        this.isSelect = isSelect;
        this.type = type;
    }

    public TimeBean(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
