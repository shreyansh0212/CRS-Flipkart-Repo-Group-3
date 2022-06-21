package com.flipkart.bean;

import java.util.Date;

public class Admin extends User{
    private Date dateOfJoining;

    /**
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

	public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}
