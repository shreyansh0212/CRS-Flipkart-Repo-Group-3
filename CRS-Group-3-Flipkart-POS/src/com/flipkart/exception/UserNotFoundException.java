package com.flipkart.exception;

public class UserNotFoundException extends Exception{
    private String userID;

    public UserNotFoundException(String userID){
        this.userID = userID;
    }
    public String getMessage(){
        return "User with userID: " + userID + " not found!";
    }
}
