package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/6.
 */

public class TimeBean {
    private String name = "";
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

    public static class LinkTimeBean {
        private String  shour, sminute, fhour, fminute;
        public LinkTimeBean(String shour, String sminute, String fhour, String fminute) {
            this.shour = shour;
            this.sminute = sminute;
            this.fhour = fhour;
            this.fminute = fminute;
        }

        public String getShour() {
            return shour;
        }

        public void setShour(String shour) {
            this.shour = shour;
        }

        public String getSminute() {
            return sminute;
        }

        public void setSminute(String sminute) {
            this.sminute = sminute;
        }

        public String getFhour() {
            return fhour;
        }

        public void setFhour(String fhour) {
            this.fhour = fhour;
        }

        public String getFminute() {
            return fminute;
        }

        public void setFminute(String fminute) {
            this.fminute = fminute;
        }
    }
}
