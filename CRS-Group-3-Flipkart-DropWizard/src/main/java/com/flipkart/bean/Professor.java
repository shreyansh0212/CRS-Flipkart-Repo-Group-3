package com.flipkart.bean;

import java.util.Date;
import java.util.List;

/**
 * Professor Bean Class
 */
public class Professor extends User{
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String department;

    /**
     * Parameterized Constructor
     * @param userID
     * @param name
     * @param role
     * @param password
     * @param department
     */

	public Professor(String userID, String name, String role, String password, String department) {
		super(userID, name, role, password);
		this.department = department;
	}

    /**
     * Default Constructor
     */
    public Professor() {
        super();
    }



}
