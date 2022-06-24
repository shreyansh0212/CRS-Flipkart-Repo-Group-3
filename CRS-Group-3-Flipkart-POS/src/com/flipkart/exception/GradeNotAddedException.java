package com.flipkart.exception;

public class GradeNotAddedException extends Exception{
    private String studentID;
    public GradeNotAddedException(String studentID) {
        this.studentID = studentID;
    }
    public String getMessage() {
        return "Grade not Added for StudentID: " + studentID;
    }
}
