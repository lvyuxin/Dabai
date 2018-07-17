package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/16.
 */

public class CommonBean {
    public  static  class StartTaskDialogBean{
        private String title;
        private String control_type;

        public StartTaskDialogBean(String title, String control_type) {
            this.title = title;
            this.control_type = control_type;
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

}
