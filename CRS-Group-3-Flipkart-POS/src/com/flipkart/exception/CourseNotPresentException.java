package com.flipkart.exception;

/**
 * Exception for the case where course is not present
 */
public class CourseNotPresentException extends Exception{
    private String  courseID;
    public CourseNotPresentException(String courseID){
        this.courseID = courseID;
    }
    public String getMessage(){
        return "Course with courseID: " + courseID + " is NOT in the course catalog!";
    }
}
