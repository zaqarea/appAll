package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelStudents implements Serializable {

    private String studentId;
    private String lectureId;
    private String studentName;
    private String studentCurrentTime;
    private String email;
    private String password;
    private String isActive;
    private String contentQR;
    private String curseId;

    public ModelStudents(){

    }

    public ModelStudents(String contentQR, String studentCurrentTime, String email, String password) {
        this.email = email;
        this.password = password;
        this.studentCurrentTime = studentCurrentTime;
        this.contentQR = contentQR;
    }

    public ModelStudents(String studentId, String lectureId, String studentName, String studentCurrentTime, String isActive) {
        this.studentId = studentId;
        this.lectureId = lectureId;
        this.studentName = studentName;
        this.studentCurrentTime = studentCurrentTime;
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContentQR() {
        return contentQR;
    }

    public void setContentQR(String contentQR) {
        this.contentQR = contentQR;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getStudentCurrentTime() {
        return studentCurrentTime;
    }

    public void setStudentCurrentTime(String studentCurrentTime) {
        this.studentCurrentTime = studentCurrentTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
