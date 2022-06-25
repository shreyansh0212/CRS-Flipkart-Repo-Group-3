package com.flipkart.service;

import com.flipkart.exception.GradeNotAddedException;
import javafx.util.Pair;

import java.util.List;

/**
 * Service Interface for Professor
 */
public interface ProfessorInterface {
    public void loginMsg(String userID);
    public void addGrade(String professorID) throws GradeNotAddedException;
    public List<Pair<String, String>> viewEnrolledStudents(String professorID);
    public List<String> getCourses(String professorID);
}
