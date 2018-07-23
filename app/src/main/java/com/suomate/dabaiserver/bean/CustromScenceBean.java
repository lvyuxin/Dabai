package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/13.
 */

public class CustromScenceBean {
    public static class  StateBean{
        private int val;
        private String name;
        private int  jsonType;
        private int deviceId;

        public StateBean(int val, String name) {
            this.val = val;
            this.name = name;
        }

        public StateBean(int val, String name, int jsonType, int deviceId) {
            this.val = val;
            this.name = name;
            this.jsonType=jsonType;
            this.deviceId=deviceId;
        }
        public int getJsonType() {
            return jsonType;
        }
        public int getDeviceId() {
            return deviceId;
        }
        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
