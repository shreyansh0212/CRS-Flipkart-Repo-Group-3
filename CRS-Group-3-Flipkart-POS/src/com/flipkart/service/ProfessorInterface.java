package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorInterface {
    public void addGrade(String courseID);
    public List<Student> viewEnrolledStudents(String courseID);
    public List<String> getCourses(String userID);
}
