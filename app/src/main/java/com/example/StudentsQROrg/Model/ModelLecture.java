package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelLecture implements Serializable {

    private String lectureId;
    private String LectureTitle;
    private String courseId;
    private String courseName;
    private String startTime;
    private String endTime;
    private String presenceTime;
    private String hourse;

    public ModelLecture() {

    }

    public ModelLecture(String courseId, String lectureId, String lectureTitle, String startTime, String endTime, String courseName, String hourse) {
        this.hourse = hourse;
        this.courseName = courseName;
        this.courseId = courseId;
        this.lectureId = lectureId;
        LectureTitle = lectureTitle;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ModelLecture(String lectureId, String lectureTitle, String courseId, String courseName, String startTime, String endTime, String presenceTime, String hourse) {
        this.lectureId = lectureId;
        LectureTitle = lectureTitle;
        this.courseId = courseId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.presenceTime = presenceTime;
        this.hourse = hourse;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureTitle() {
        return LectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        LectureTitle = lectureTitle;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPresenceTime() {
        return presenceTime;
    }

    public void setPresenceTime(String presenceTime) {
        this.presenceTime = presenceTime;
    }

    public String getHourse() {
        return hourse;
    }

    public void setHourse(String hourse) {
        this.hourse = hourse;
    }
}
