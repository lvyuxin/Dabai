package com.suomate.dabaiserver.bean;

import java.io.Serializable;

/**
 * Created by fanxi on 2018/7/16.
 */

public class CommonBean {
//    	"device_or_scene_id": "62",
//                "val": "01000002",
//                "delay": 3,
//                "type": "1",
//                "address": ""
    public static class StartTaskDialogBean {
        private String title;
        private String control_type;
        private String deviceId;
        private int json_type;
        private int port;

        //自定义属性
        private int value1;//17/18/19
        private int value2;
        private int value3;
        private int value4;
        private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getValue1() {
            return value1;
        }

        public void setValue1(int value1) {
            this.value1 = value1;
        }

        public int getValue2() {
            return value2;
        }

        public void setValue2(int value2) {
            this.value2 = value2;
        }

        public int getValue3() {
            return value3;
        }

        public void setValue3(int value3) {
            this.value3 = value3;
        }

        public int getValue4() {
            return value4;
        }

        public void setValue4(int value4) {
            this.value4 = value4;
        }

//    public StartTaskDialogBean(String title, String control_type, String deviceId, int json_type, int port) {
//        this.title = title;
//        this.control_type = control_type;
//        this.deviceId = deviceId;
//        this.json_type = json_type;
//        this.port = port;
//    }

    public StartTaskDialogBean(String title, String control_type, String deviceId, int json_type, int port, String detail) {
        this.title = title;
        this.control_type = control_type;
        this.deviceId = deviceId;
        this.json_type = json_type;
        this.port = port;
        this.detail = detail;
    }

    public int getJson_type() {
            return json_type;
        }

        public void setJson_type(int json_type) {
            this.json_type = json_type;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getControl_type() {
            return control_type;
        }

        public void setControl_type(String control_type) {
            this.control_type = control_type;
        }
    }

    public static class PanelNumberBean {
        /**
         * name : 巴巴1
         * panel_number : 1
         */

        private String name;
        private int panel_number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPanel_number() {
            return panel_number;
        }

        public void setPanel_number(int panel_number) {
            this.panel_number = panel_number;
        }
    }

    public static class ExecuteDeviceBean  implements Serializable{

        /**
         * device_or_scene_id : 62
         * val : 01000002
         * delay : 3
         * type : 1
         * address :
         */

        private String device_or_scene_id;
        private String val;
        private int delay;
        private String type;
        private String address;
        //自定义展示
        private String name;
        private String detail;

        public ExecuteDeviceBean(String device_or_scene_id, String val, int delay, String type, String address,String name,String detail) {
            this.device_or_scene_id = device_or_scene_id;
            this.val = val;
            this.delay = delay;
            this.type = type;
            this.address = address;
            this.name=name;
            this.detail=detail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDevice_or_scene_id() {
            return device_or_scene_id;
        }

        public void setDevice_or_scene_id(String device_or_scene_id) {
            this.device_or_scene_id = device_or_scene_id;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
    /**
     * 面板绑定类型自定义的实体类
     */
    public static class  BindTypeBean{
        private String  name;
        private int type;

        public BindTypeBean(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
