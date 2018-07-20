package com.suomate.dabaiserver.bean;

import java.io.Serializable;

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

    public static class LinkTimeBean implements Serializable{
        private String  shour, sminute, fhour, fminute,week;

        public LinkTimeBean() {
        }

        public LinkTimeBean(String shour, String sminute, String fhour, String fminute) {
            this.shour = shour;
            this.sminute = sminute;
            this.fhour = fhour;
            this.fminute = fminute;
        }

        public LinkTimeBean(String week) {
            this.week = week;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
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
    public static class LinkLanchDevice implements Serializable{



        private int device_id;
        private int val;
        private int number;
        //自定义的name
        private String name;

        public LinkLanchDevice(int device_id, int val, int number, String name) {
            this.device_id = device_id;
            this.val = val;
            this.number = number;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getDevice_id() {
            return device_id;
        }

        public void setDevice_id(int device_id) {
            this.device_id = device_id;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }


    }
}
