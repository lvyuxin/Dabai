package com.suomate.dabaiserver.bean;

import java.io.Serializable;

/**
 * Created by fanxi on 2018/7/8.
 */

public class TimeLaunchBean implements Serializable {

    /**
     * hour : 12
     * minute : 22
     * week : 1,2,3
     */

    private String hour;
    private String minute;
    private String week;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
