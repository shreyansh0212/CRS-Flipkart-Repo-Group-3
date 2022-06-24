package com.flipkart.dao;

import com.flipkart.exception.CourseAlreadyRegistered;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;
import com.flipkart.exception.UserNotFoundException;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAOInterface {
    public String getUsername(String userID);

    public void preferenceUpdate(String userID, List<String> preference) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException;
    public void addCourse(String userID, String courseID) throws CourseAlreadyRegistered, CourseNotPresentException;
    public void dropCourse(String userID, String courseID);

    public List<String> viewEnrolledCourses(String userID);
    public List<Pair<String, String>> viewGrades(String userID);

    public boolean isRegistered(String userID);

    public boolean isFeePaymentStatus(String userID);

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt);

    void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist;

    Boolean checkApprovalStatus(String userID) throws UserNotFoundException;
    public Boolean checkCourseAvailability(String courseID) throws CourseNotPresentException;
}
