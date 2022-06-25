package com.flipkart.bean;

import java.util.Date;
import java.util.List;

/**
 * Professor Bean Class
 */
public class Professor extends User{
    private Date dateOfJoining;
    private String department;
    private String designation;
    private List<String> offeredCourses;

    /**
     * Parameterized Constructor
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

    /**
     * Default Constructor
     */
    public Professor() {
        super();
    }

    /**
     * Get DOJ
     * @return
     */
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * Set DOJ
     * @param dateOfJoining
     */
    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     * Get Department
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Set Department
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Get Designation
     * @return
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Set Designation
     * @param designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Get Offered Courses by Professor
     * @return
     */
    public List<String> getOfferedCourses() {
        return offeredCourses;
    }

    /**
     * Set offered courses by Professor
     * @param offeredCourses
     */
    public void setOfferedCourses(List<String> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }



}
