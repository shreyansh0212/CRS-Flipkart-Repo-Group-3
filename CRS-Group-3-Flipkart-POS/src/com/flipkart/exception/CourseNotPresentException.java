package com.flipkart.exception;

public class CourseNotPresentException extends Exception{
    private String  msg = "Selected course is not present";
    public String getMsg(){
        return msg;
    }
}
