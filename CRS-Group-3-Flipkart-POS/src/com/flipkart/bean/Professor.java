package com.flipkart.bean;

import java.util.Date;
import java.util.List;

public class Professor extends User{
    private Date dateOfJoining;
    private String department;
    private String designation;
    private List<String> offeredCourses;

    /**
	 * @param userID
	 * @param name
	 * @param role
	 * @param password
	 * @param dateOfJoining
	 * @param department
	 * @param designation
	 * @param offeredCourses
	 */

	public Professor(String userID, String name, String role, String password, Date dateOfJoining, String department,
                     String designation, List<String> offeredCourses) {
		super(userID, name, role, password);
		this.dateOfJoining = dateOfJoining;
		this.department = department;
		this.designation = designation;
		this.offeredCourses = offeredCourses;
	}

    public Professor() {
        super();
    }

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
