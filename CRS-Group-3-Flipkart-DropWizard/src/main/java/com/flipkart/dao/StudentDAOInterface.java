package com.flipkart.dao;

import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Payment;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

/**
 * operations for the student dao interface
 */
public interface StudentDAOInterface {
    public String getUsername(String userID);
    public void preferenceUpdate(String userID, List<String> preference) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException, com.flipkart.exception.SeatNotAvailableException, CourseLimitExceededException;
    public void addCourse(String userID, String courseID) throws CourseAlreadyRegistered, CourseNotPresentException, com.flipkart.exception.SeatNotAvailableException, CourseLimitExceededException;
    public void dropCourse(String courseID, String userID);
    public List<EnrolledStudent> viewEnrolledCourses(String userID);
    public List<StudentGrade> viewGrades(String userID);
    public boolean getRegistrationStatus(String userID);

    public boolean feePaymentStatus(String userID) throws UserNotFoundException;

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt) throws UserNotFoundException;

    void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist, UserNotAdded;

    Boolean getApprovalStatus(String userID) throws UserNotFoundException;


    int checkCourseAvailability(String studentId, String courseCode) throws SQLException, CourseNotPresentException;

    boolean seatAvailable(String courseCode) throws SQLException;

    Payment payFees(String userID, float amount, String mode) throws UserNotFoundException, FeesAlreadyPaid, StudentNotRegistered, SQLException;
}
