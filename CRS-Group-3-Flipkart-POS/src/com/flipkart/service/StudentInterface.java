package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface StudentInterface {

    public void registerCourses(String userID) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException;
    public void addCourse(String userID) throws CourseAlreadyRegistered, CourseNotPresentException;
    public void dropCourse(String userID) throws CourseNotPresentException;
    public void viewEnrolledCourses(String userID);
    public void payFees(String userID);
    void viewGradeCard(String userID);

    void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist;

    void showCourses();

    Boolean checkApprovalStatus(String userID) throws UserNotFoundException, StudentNotApproved;
}
