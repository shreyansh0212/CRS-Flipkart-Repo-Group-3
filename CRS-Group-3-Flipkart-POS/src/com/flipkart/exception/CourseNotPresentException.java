package com.flipkart.exception;

public class CourseNotPresentException extends Exception{
    private String  courseID;
    public CourseNotPresentException(String courseID){
        this.courseID = courseID;
    }
    public String getMessage(){
        return "Course with courseID: " + courseID + " is NOT in the course catalog!";
    }
}
