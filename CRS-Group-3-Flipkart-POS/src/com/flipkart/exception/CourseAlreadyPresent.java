package com.flipkart.exception;

public class CourseAlreadyPresent extends Exception{
    private String  msg = "Selected course is already present in registration";
    public String getMsg(){
        return msg;
    }
}
