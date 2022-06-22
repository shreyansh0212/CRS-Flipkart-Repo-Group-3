package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.List;

public interface StudentInterface {

    public void registerCourses(String userID);
    public void addCourse(String userID);
    public void dropCourse(String userID);
    public void viewEnrolledCourses(String userID);
    public void payFees(String userID);
    void viewGradeCard(String userID);

    void showCourses();
}
