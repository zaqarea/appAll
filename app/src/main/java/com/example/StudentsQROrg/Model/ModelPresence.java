package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelPresence implements Serializable {

    private String id;
    private String studentId;
    private String curseId;
    private String lectureId;
    private String date;
    
    private String studentName;
    private String presenceTime;
    private String isActive;
    private String contentQR;
    private String curseName;
    private String lectureTitle;

    public ModelPresence(String studentId, String curseId, String lectureId, String studentName, String presenceTime, String isActive, String contentQR, String curseName, String lectureTitle) {
        this.lectureTitle = lectureTitle;
        this.curseName = curseName;
        this.studentId = studentId;
        this.curseId = curseId;
        this.lectureId = lectureId;
        this.studentName = studentName;
        this.presenceTime = presenceTime;
        this.isActive = isActive;
        this.contentQR = contentQR;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getCurseName() {
        return curseName;
    }

    public void setCurseName(String curseName) {
        this.curseName = curseName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCurseId() {
        return curseId;
    }

    public void setCurseId(String curseId) {
        this.curseId = curseId;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPresenceTime() {
        return presenceTime;
    }

    public void setPresenceTime(String presenceTime) {
        this.presenceTime = presenceTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getContentQR() {
        return contentQR;
    }

    public void setContentQR(String contentQR) {
        this.contentQR = contentQR;
    }
}
