package com.flipkart.exception;


/**
 * Exception where grade is not added
 */
public class GradeNotAddedException extends Exception{
    private String studentID;
    private String courseID;
    public GradeNotAddedException(String studentID,String courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }
    public String getMessage() {
        return "Grade not Added for StudentID: " + studentID + " for the course with courseID: "+ courseID;
    }
}
