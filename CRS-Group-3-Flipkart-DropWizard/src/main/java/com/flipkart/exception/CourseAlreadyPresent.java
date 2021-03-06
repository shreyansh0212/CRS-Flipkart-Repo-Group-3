package com.flipkart.exception;

/**
 * Exception to check if course is already present
 */
public class CourseAlreadyPresent extends Exception{
    private String  courseID;
    public CourseAlreadyPresent(String courseID){
        this.courseID = courseID;
    }
    public String getMessage(){
        return "Course with courseID: " + courseID + " is already in the course catalog!";
    }
}
