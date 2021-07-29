package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelStats implements Serializable {

    private String id;
    private String dateMonth;
    private String day;
    private String delay;
    private String entryTime;
    private String outTime;

    public ModelStats(String id, String dateMonth, String day, String delay, String entryTime, String outTime) {
        this.id = id;
        this.dateMonth = dateMonth;
        this.day = day;
        this.delay = delay;
        this.entryTime = entryTime;
        this.outTime = outTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
