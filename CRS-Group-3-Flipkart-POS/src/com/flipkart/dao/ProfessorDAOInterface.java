package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAOInterface {
    public void addGrade(String courseID);
    public List<Student> viewEnrolledStudents(String courseID);
    public List<String> getCourses(String userID);
}
