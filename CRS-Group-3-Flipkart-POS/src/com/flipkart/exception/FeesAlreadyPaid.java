package com.flipkart.exception;


/**
 * Exception for the case where fee is already paid
 */
public class FeesAlreadyPaid extends Exception {
    private String userID;
    public FeesAlreadyPaid(String userID) {
        this.userID = userID;
    }
    public String getMessage() {
        return "Fees already Paid for StudentID: " + userID;
    }
}
