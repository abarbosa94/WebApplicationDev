package com.webapp.hw.andre.model;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseSearch {
    private Long crsId;
    private String deg;
    private String discipline;
    private String courseNum;
    private String courseSec;
    private String courseCode;
    private String meetingDays;
    private String startTime;
    private String stopTime;
    private String ampm;
    private Long seats;
    private String building;
    private String room;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Calendar startDate;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Calendar endDate;
    private String name;
    private String semester;
    private String comments;
    public Long getCrsId() {
        return crsId;
    }
    public void setCrsId(Long crsId) {
        this.crsId = crsId;
    }
    public String getDeg() {
        return deg;
    }
    public void setDeg(String deg) {
        this.deg = deg;
    }
    public String getDiscipline() {
        return discipline;
    }
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
    public String getCourseNum() {
        return courseNum;
    }
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }
    public String getCourseSec() {
        return courseSec;
    }
    public void setCourseSec(String courseSec) {
        this.courseSec = courseSec;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCouseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getMeetingDays() {
        return meetingDays;
    }
    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }
    public String getAmpm() {
        return ampm;
    }
    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }
    public Long getSeats() {
        return seats;
    }
    public void setSeats(Long seats) {
        this.seats = seats;
    }
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getStopTime() {
        return stopTime;
    }
    public void setStopTime(String time) {
        this.stopTime = time;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }



}
