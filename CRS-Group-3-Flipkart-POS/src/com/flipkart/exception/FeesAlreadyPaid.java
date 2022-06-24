package com.flipkart.exception;

public class FeesAlreadyPaid extends Exception {
    private String userID;
    public FeesAlreadyPaid(String userID) {
        this.userID = userID;
    }
    public String getMessage() {
        return "Fees already Paid for StudentID: " + userID;
    }
}
