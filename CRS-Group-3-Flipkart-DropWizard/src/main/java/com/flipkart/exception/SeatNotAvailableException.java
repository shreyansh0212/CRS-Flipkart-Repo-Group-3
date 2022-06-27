package com.flipkart.exception;

/**
 * Exception if the seat is not available
 */
public class SeatNotAvailableException extends Exception{
    private String courseID;
    public SeatNotAvailableException(String courseID) {
        this.courseID = courseID;
    }
    public String getMessage () {
        return "Seat not Available in CourseID: " + courseID;
    }
}
