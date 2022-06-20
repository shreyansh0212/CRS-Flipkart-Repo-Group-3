package com.flipkart.bean;

import java.util.Date;
import java.util.List;

public class Professor extends User{
    private Date dateOfJoining;
    private String department;
    private String designation;
    private List<String> offeredCourses;

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<String> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<String> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }



}
