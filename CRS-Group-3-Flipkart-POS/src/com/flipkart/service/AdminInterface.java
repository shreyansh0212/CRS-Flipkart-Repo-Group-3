package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;
import com.flipkart.exception.UserNotFoundException;

public interface AdminInterface {
    public void loginMsg(String userID);

    public void viewCourseRegistration(String studentId);
    public void approveCourseRegistration(String studentId) throws UserNotFoundException;
    public void approveStudent(String studentid);
    public void addProfessor(Professor professor) throws UserAlreadyExist;
    public void generateReport();
    public void addCourse(Course course) throws CourseAlreadyPresent;
    public void dropCourse(String courseID) throws CourseNotPresentException;
    public void showCourses();

    public void PendingRequests();

    public void NonRegisteredstudentlist();
}
