package com.flipkart.bean;

import java.util.Date;

public class StudentRegistration {
    private Date dateofRegistration;
    private int semesterNumber;
    
	/**
	 * @param dateofRegistration
	 * @param semesterNumber
	 */
	public StudentRegistration(Date dateofRegistration, int semesterNumber) {
		super();
		this.dateofRegistration = dateofRegistration;
		this.semesterNumber = semesterNumber;
	}
	/**
	 * @return ateofRegistration
	 */
	public Date getDateofRegistration() {
		return dateofRegistration;
	}
	/**
	 * @param dateofRegistration
	 */
	public void setDateofRegistration(Date dateofRegistration) {
		this.dateofRegistration = dateofRegistration;
	}
	/**
	 * @return semesterNumber
	 */
	public int getSemesterNumber() {
		return semesterNumber;
	}
	/**
	 * @param semesterNumber
	 */
	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}
    
    
}
