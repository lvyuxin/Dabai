package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/13.
 */

public class CustromScenceBean {
    public static class  StateBean{
        private int type;
        private String name;

        public StateBean(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
