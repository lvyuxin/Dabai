package com.suomate.dabaiserver.bean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/26.
 */

public class AreaSelectListBean {

    /**
     * code : 10200
     * message : 成功
     * data : [{"area_id":6,"area_name":"123456975","guid":123456975,"area_background_img":null,"area_icon":null},{"area_id":7,"area_name":"测试区域","guid":123456975,"area_background_img":null,"area_icon":null}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * area_id : 6
         * area_name : 123456975
         * guid : 123456975
         * area_background_img : null
         * area_icon : null
         */

        private int area_id;
        private String area_name;
        private int guid;
        private Object area_background_img;
        private Object area_icon;

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getGuid() {
            return guid;
        }

        public void setGuid(int guid) {
            this.guid = guid;
        }

        public Object getArea_background_img() {
            return area_background_img;
        }

        public void setArea_background_img(Object area_background_img) {
            this.area_background_img = area_background_img;
        }

        public Object getArea_icon() {
            return area_icon;
        }

        public void setArea_icon(Object area_icon) {
            this.area_icon = area_icon;
        }
    }
}
