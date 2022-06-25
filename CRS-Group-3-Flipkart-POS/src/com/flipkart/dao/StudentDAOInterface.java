package com.flipkart.dao;

import com.flipkart.exception.*;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

/**
 * operations for the student dao interface
 */
public interface StudentDAOInterface {
    public String getUsername(String userID);

    public void preferenceUpdate(String userID, List<String> preference) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException, com.flipkart.exception.SeatNotAvailableException, CourseLimitExceededException;
    public void addCourse(String userID, String courseID) throws CourseAlreadyRegistered, CourseNotPresentException, com.flipkart.exception.SeatNotAvailableException, CourseLimitExceededException;
    public void dropCourse(String userID, String courseID);

    public List<String> viewEnrolledCourses(String userID);
    public List<Pair<String, String>> viewGrades(String userID);

    public boolean isRegistered(String userID);

    public boolean isFeePaymentStatus(String userID) throws UserNotFoundException;

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt) throws UserNotFoundException;

    void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist, UserNotAdded;

    Boolean checkApprovalStatus(String userID) throws UserNotFoundException;
    public Boolean checkCourseAvailability(String courseID) throws CourseNotPresentException, com.flipkart.exception.SeatNotAvailableException;
}
