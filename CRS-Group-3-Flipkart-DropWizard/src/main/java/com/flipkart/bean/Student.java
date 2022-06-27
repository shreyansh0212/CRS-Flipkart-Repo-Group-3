package com.flipkart.bean;

import java.util.List;

/**
 * Student Bean Class
 */
public class Student extends User{
    private String address;
    private String batch;
    private boolean feePaymentStatus;
    private boolean isRegistered;

    /**
     * Parameterized Constructor
     * @param userID
     * @param name
     * @param role
     * @param password
     * @param address
     * @param batch
     * @param feePaymentStatus
     * @param isRegistered
     */
	public Student(String userID, String name, String role, String password, String address, String batch,
                   boolean feePaymentStatus, boolean isRegistered) {
		super(userID, name, role, password);
		this.address = address;
		this.batch = batch;
		this.feePaymentStatus = feePaymentStatus;
		this.isRegistered = isRegistered;
	}

    public Student(){

    }

    /**
     * Get Branch
     * @return
     */
	public String getAddress() {
        return address;
    }

    /**
     * Set Branch
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
