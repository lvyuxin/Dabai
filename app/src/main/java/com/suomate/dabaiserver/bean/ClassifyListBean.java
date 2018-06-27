package com.suomate.dabaiserver.bean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/27.
 */

public class ClassifyListBean {

    /**
     * code : 10200
     * message : 成功
     * data : [{"classify_id":6,"classify_name":"开关测试一","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":7,"classify_name":"地暖","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":8,"classify_name":"窗帘","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":9,"classify_name":"新风","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":10,"classify_name":"人感","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":11,"classify_name":"烟感","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":12,"classify_name":"调光","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":13,"classify_name":"IO面板","guid":123456975,"classify_icon":null,"classify_background_img":null},{"classify_id":15,"classify_name":"场景","guid":123456975,"classify_icon":null,"classify_background_img":null}]
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
         * classify_id : 6
         * classify_name : 开关测试一
         * guid : 123456975
         * classify_icon : null
         * classify_background_img : null
         */

        private int classify_id;
        private String classify_name;
        private int guid;
        private Object classify_icon;
        private Object classify_background_img;

        public int getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(int classify_id) {
            this.classify_id = classify_id;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public int getGuid() {
            return guid;
        }

        public void setGuid(int guid) {
            this.guid = guid;
        }

        public Object getClassify_icon() {
            return classify_icon;
        }

        public void setClassify_icon(Object classify_icon) {
            this.classify_icon = classify_icon;
        }

        public Object getClassify_background_img() {
            return classify_background_img;
        }

        public void setClassify_background_img(Object classify_background_img) {
            this.classify_background_img = classify_background_img;
        }
    }
}
