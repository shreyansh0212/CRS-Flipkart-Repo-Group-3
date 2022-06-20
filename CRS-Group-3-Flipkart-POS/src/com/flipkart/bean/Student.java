package com.flipkart.bean;

import java.util.List;

public class Student extends User{
    private String branch;
    private String batch;
    private boolean feePaymentStatus;
    private List<String> coursePreferences;
    private boolean isRegistered;

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

}
