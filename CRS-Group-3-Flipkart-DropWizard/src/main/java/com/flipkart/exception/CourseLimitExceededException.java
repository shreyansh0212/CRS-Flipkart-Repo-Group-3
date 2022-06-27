package com.flipkart.exception;
/**
 * Exception to check whether course Limit is exceeded
 */
public class CourseLimitExceededException extends Exception{
    private int registerCoursesCount;
    public CourseLimitExceededException(int registerCoursesCount) {
        this.registerCoursesCount = registerCoursesCount;
    }
    public String getMessage () {
        return "Already Enrolled in " + registerCoursesCount + " Courses!";
    }
}
