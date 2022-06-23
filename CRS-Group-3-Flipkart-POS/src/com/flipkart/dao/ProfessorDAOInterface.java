package com.flipkart.dao;

import javafx.util.Pair;

import java.util.List;

public interface ProfessorDAOInterface {
    public boolean addGrade(String grade,String courseID,String studentID);
    public List<Pair<String, String>> viewEnrolledStudents(String professorID);
    public List<String> getCourses(String professorID);
}
