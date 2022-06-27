package com.flipkart.exception;

/**
 * exception if User Not added
 */
public class UserNotAdded extends Exception{
    private String userID;
    public UserNotAdded(String userID) {
        this.userID = userID;
    }
    public String getMessage() {
        return "User Not Added for UserID: " + userID;
    }
}
