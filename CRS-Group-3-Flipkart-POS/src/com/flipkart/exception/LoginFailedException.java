package com.flipkart.exception;

public class LoginFailedException extends RuntimeException{
    private String userID;
    public LoginFailedException(String userID){
        this.userID = userID;
    }
    public String getMsg(){
        return "Login failed";
    }
}
