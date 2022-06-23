package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorInterface {
    public void addGrade(String courseID);
    public void viewEnrolledStudents(String courseID);
    public void getCourses(String userID);
}
