package com.webapp.hw.andre.model;


public class Discipline {
    private String discAbreviation;
    private String disciplineName;
    private long deptID;
    private long schoolID;


    public String getDiscAbreviation() {
        return discAbreviation;
    }
    public void setDiscAbreviation(String discAbreviation) {
        this.discAbreviation = discAbreviation;
    }
    public String getDisciplineName() {
        return disciplineName;
    }
    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
    public long getDeptID() {
        return deptID;
    }
    public void setDeptID(long deptID) {
        this.deptID = deptID;
    }
    public long getSchoolID() {
        return schoolID;
    }
    public void setSchoolID(long schoolID) {
        this.schoolID = schoolID;
    }



}
