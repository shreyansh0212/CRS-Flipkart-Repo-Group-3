package com.flipkart.bean;

import java.util.HashMap;
import java.util.List;
import com.flipkart.bean.StudentGrade;

public class Student extends User{
    private String branch;
    private String batch;
    private boolean feePaymentStatus;
    private List<String> coursePreferences;

    private HashMap<String,String> coursesRegistered;
    private boolean isRegistered;

    /**
	 * @param userID
	 * @param name
	 * @param role
	 * @param password
	 * @param branch
	 * @param batch
	 * @param feePaymentStatus
	 * @param coursePreferences
	 * @param isRegistered
     * @param coursesRegistered - key: courseID, value: Grade
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

	public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isFeePaymentStatus() {
        return feePaymentStatus;
    }

    public void setFeePaymentStatus(boolean feePaymentStatus) {
        this.feePaymentStatus = feePaymentStatus;
    }

    public List<String> getCoursePreferences() {
        return coursePreferences;
    }

    public void setCoursePreferences(List<String> coursePreferences) {
        this.coursePreferences = coursePreferences;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public HashMap<String, String> getCoursesRegistered() {
        return coursesRegistered;
    }

    public void setCoursesRegistered(HashMap<String, String> coursesRegistered) {
        this.coursesRegistered = coursesRegistered;
    }
}
