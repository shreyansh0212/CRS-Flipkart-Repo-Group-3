package com.flipkart.exception;

/**
 * Exception if the student is not approved
 */
public class StudentNotApproved extends Exception{
    private String userID;

    public StudentNotApproved(String userID){
        this.userID = userID;
    }

    public String getMessage(){
        return "User with userID: " + userID + " is not approved by the Admin";
    }
}
