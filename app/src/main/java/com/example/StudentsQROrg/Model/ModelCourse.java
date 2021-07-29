package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelCourse implements Serializable {

    private String courseId;
    private String courseDateStart;
    private String courseDateEnd;
    private String courseName;
    private String coachName;
    private String isActive;
    private String numberHours;


    public ModelCourse(String courseId, String courseDateStart, String courseDateEnd, String courseName, String coachName, String isActive, String numberHours) {
        this.numberHours =numberHours;
        this.courseId = courseId;
        this.courseDateStart = courseDateStart;
        this.courseDateEnd = courseDateEnd;
        this.courseName = courseName;
        this.coachName = coachName;
        //this.courseContent = courseContent;
        this.isActive = isActive;
    }

    public String getNumberHours() {
        return numberHours;
    }

    public void setNumberHours(String numberHours) {
        this.numberHours = numberHours;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseDateStart() {
        return courseDateStart;
    }

    public void setCourseDateStart(String courseDateStart) {
        this.courseDateStart = courseDateStart;
    }

    public String getCourseDateEnd() {
        return courseDateEnd;
    }

    public void setCourseDateEnd(String courseDateEnd) {
        this.courseDateEnd = courseDateEnd;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
