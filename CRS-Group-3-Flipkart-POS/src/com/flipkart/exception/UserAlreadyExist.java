package com.flipkart.exception;

public class UserAlreadyExist extends Exception{
    private String userID;
     public UserAlreadyExist(String userID){
         this.userID = userID;
     }
     public String getMessage(){
         return "User with userID: " + userID + " already exists!";
     }
}
