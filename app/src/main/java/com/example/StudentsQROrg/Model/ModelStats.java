package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelStats implements Serializable {

    private String id;
    private String exsitDate;
    private String entranceDate;
    private String isExsist;
    private String lateHoursOfDay;
    private String day;

    private String lateHoursOfMonth;
    private String absenceDaysOfMonth;
    private String presenceDaysOfMonth;

    public ModelStats(String id, String exsitDate, String entranceDate, String isExsist, String lateHoursOfDay, String dsdsd) {
        this.id = id;
        this.exsitDate = exsitDate;
        this.entranceDate = entranceDate;
        this.isExsist = isExsist;
        this.lateHoursOfDay = lateHoursOfDay;
    }

    public ModelStats(String lateHoursOfMonth, String absenceDaysOfMonth, String presenceDaysOfMonth) {
        this.lateHoursOfMonth = lateHoursOfMonth;
        this.absenceDaysOfMonth = absenceDaysOfMonth;
        this.presenceDaysOfMonth = presenceDaysOfMonth;
    }

    public ModelStats(String exsitDate, String entranceDate, String isExsist, String lateHoursOfDay, String day) {
        this.day = day;
        this.lateHoursOfDay = lateHoursOfDay;
        this.exsitDate = exsitDate;
        this.entranceDate = entranceDate;
        this.isExsist = isExsist;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExsitDate() {
        return exsitDate;
    }

    public void setExsitDate(String exsitDate) {
        this.exsitDate = exsitDate;
    }

    public String getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(String entranceDate) {
        this.entranceDate = entranceDate;
    }

    public String getIsExsist() {
        return isExsist;
    }

    public void setIsExsist(String isExsist) {
        this.isExsist = isExsist;
    }

    public String getLateHoursOfDay() {
        return lateHoursOfDay;
    }

    public void setLateHoursOfDay(String lateHoursOfDay) {
        this.lateHoursOfDay = lateHoursOfDay;
    }

    public String getLateHoursOfMonth() {
        return lateHoursOfMonth;
    }

    public void setLateHoursOfMonth(String lateHoursOfMonth) {
        this.lateHoursOfMonth = lateHoursOfMonth;
    }

    public String getAbsenceDaysOfMonth() {
        return absenceDaysOfMonth;
    }

    public void setAbsenceDaysOfMonth(String absenceDaysOfMonth) {
        this.absenceDaysOfMonth = absenceDaysOfMonth;
    }

    public String getPresenceDaysOfMonth() {
        return presenceDaysOfMonth;
    }

    public void setPresenceDaysOfMonth(String presenceDaysOfMonth) {
        this.presenceDaysOfMonth = presenceDaysOfMonth;
    }
}
