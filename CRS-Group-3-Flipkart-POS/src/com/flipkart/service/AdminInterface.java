package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;

public interface AdminInterface {
    public void loginMsg(String userID);
    public void approveStudent(String studentid);
    public void addProfessor(Professor professor) throws UserAlreadyExist;
    public void generateReport();
    public void addCourse(Course course) throws CourseAlreadyPresent;
    public void dropCourse(String courseID) throws CourseNotPresentException;
    public void showCourses();

    public void PendingRequests();

    public void NonRegisteredstudentlist();
}
