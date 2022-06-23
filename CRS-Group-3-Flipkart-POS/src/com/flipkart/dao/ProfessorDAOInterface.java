package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAOInterface {
    public void addGrade(String courseID, String userID, String grade);
    public List<String> viewEnrolledStudents(String courseID);
    public List<String> getCourses(String userID);
}
