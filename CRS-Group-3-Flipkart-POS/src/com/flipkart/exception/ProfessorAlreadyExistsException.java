package com.flipkart.exception;

public class ProfessorAlreadyExistsException extends Exception{
    private String userID;
    public ProfessorAlreadyExistsException(String userID){
        this.userID = userID;
    }
    public String getMessage(){
        return "Professor with ID: " + userID + " already exists";
    }
}
