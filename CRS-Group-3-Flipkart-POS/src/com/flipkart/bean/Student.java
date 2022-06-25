package com.flipkart.bean;

import java.util.HashMap;
import java.util.List;
import com.flipkart.bean.StudentGrade;

/**
 * Student Bean Class
 */
public class Student extends User{
    private String branch;
    private String batch;
    private boolean feePaymentStatus;
    private List<String> coursePreferences;

    private boolean isRegistered;

    /**
     * Parameterized Constructor
     * @param userID
     * @param name
     * @param role
     * @param password
     * @param branch
     * @param batch
     * @param feePaymentStatus
     * @param coursePreferences
     * @param coursesRegistered
     * @param isRegistered
     */
	public Student(String userID, String name, String role, String password, String branch, String batch,
			boolean feePaymentStatus, List<String> coursePreferences, List<StudentGrade>coursesRegistered, boolean isRegistered) {
		super(userID, name, role, password);
		this.branch = branch;
		this.batch = batch;
		this.feePaymentStatus = feePaymentStatus;
		this.coursePreferences = coursePreferences;
		this.isRegistered = isRegistered;
	}

    /**
     * Get Branch
     * @return
     */
	public String getBranch() {
        return branch;
    }

    /**
     * Set Branch
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Get Batch
     * @return
     */
    public String getBatch() {
        return batch;
    }

    /**
     * Set Batch
     * @param batch
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * Check Payment Status
     * @return
     */
    public boolean isFeePaymentStatus() {
        return feePaymentStatus;
    }

    /**
     * Set Payment Status
     * @param feePaymentStatus
     */
    public void setFeePaymentStatus(boolean feePaymentStatus) {
        this.feePaymentStatus = feePaymentStatus;
    }

    /**
     * Get Course Preference
     * @return
     */
    public List<String> getCoursePreferences() {
        return coursePreferences;
    }

    /**
     * Set Course Preference
     * @param coursePreferences
     */
    public void setCoursePreferences(List<String> coursePreferences) {
        this.coursePreferences = coursePreferences;
    }

    /**
     * Check Registration Status
     * @return
     */
    public boolean isRegistered() {
        return isRegistered;
    }

    /**
     * Set Registration Status
     * @param registered
     */

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

}
