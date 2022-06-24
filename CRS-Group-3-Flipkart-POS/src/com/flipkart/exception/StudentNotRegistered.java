package com.flipkart.exception;

public class StudentNotRegistered extends Exception{
    private String userID;

    public StudentNotRegistered(String userID){
        this.userID = userID;
    }

    public String getMessage(){
        return "Student Registration Not Done for StudentID: " + userID;
    }
}
