package com.flipkart.dao;

import com.flipkart.exception.GradeNotAddedException;
import javafx.util.Pair;

import java.util.List;

public interface ProfessorDAOInterface {
    public String getUsername(String userID);
    public boolean addGrade(String grade,String courseID,String studentID) throws GradeNotAddedException;
    public List<Pair<String, String>> viewEnrolledStudents(String professorID);
    public List<String> getCourses(String professorID);
}
