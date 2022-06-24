package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;

import java.util.List;

public interface StudentInterface {

    public void registerCourses(String userID);
    public void addCourse(String userID) throws CourseAlreadyPresent;
    public void dropCourse(String userID) throws CourseNotPresentException;
    public void viewEnrolledCourses(String userID);
    public void payFees(String userID);
    void viewGradeCard(String userID);

    void newRegistration(String studentID, String password, String name, String batch, String address);

    void showCourses();
}
