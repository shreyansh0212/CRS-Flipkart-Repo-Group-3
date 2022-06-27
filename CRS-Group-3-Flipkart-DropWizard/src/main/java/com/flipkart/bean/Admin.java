package com.flipkart.bean;

import java.util.Date;

/**
 * Admin Bean Class
 */

public class Admin extends User{
    private Date dateOfJoining;

	/**
	 * Parameterized Constructor
	 * @param userID
	 * @param name
	 * @param role
	 * @param password
	 * @param dateOfJoining
	 */
	public Admin(String userID, String name, String role, String password, Date dateOfJoining) {
		super(userID, name, role, password);
		this.dateOfJoining = dateOfJoining;
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
}
