package com.webapp.hw.andre.model;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class SemesterSR {
    private long semesterID;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Calendar startDate;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Calendar endDate;
    private String semesterName;
    private String semester;


    public long getSemesterID() {
        return semesterID;
    }
    public void setSemesterID(long semesterID) {
        this.semesterID = semesterID;
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
    public String getSemesterName() {
        return semesterName;
    }
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

}
