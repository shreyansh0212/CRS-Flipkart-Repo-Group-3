package com.flipkart.exception;

/**
 * Exception to check whether course is already registered
 */
public class CourseAlreadyRegistered extends Exception{
    private String  courseID;
    private String studentID;
    public CourseAlreadyRegistered(String courseID,String studentID){
        this.courseID = courseID;
        this.studentID = studentID;
    }

    public CourseAlreadyRegistered(String courseCode) {
    }

    public String getMessage(){
        return "Course with courseID: " + courseID + " is already registered for student with studentID: " + studentID;
    }
}
