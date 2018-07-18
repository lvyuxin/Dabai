package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/16.
 */

public class CommonBean {
    public static class StartTaskDialogBean {
        private String title;
        private String control_type;
        private String deviceId;
        private int json_type;

        public StartTaskDialogBean(String title, String control_type, String deviceId, int json_type) {
            this.title = title;
            this.control_type = control_type;
            this.deviceId = deviceId;
            this.json_type = json_type;
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
}
