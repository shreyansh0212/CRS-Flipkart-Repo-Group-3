package com.flipkart.service;

import com.flipkart.exception.GradeNotAddedException;
import javafx.util.Pair;

import java.util.List;

/**
 * Service Interface for Professor
 */
public interface ProfessorInterface {
    /**
     * Print Login Message
     * @param userID
     */
    public void loginMsg(String userID);

    /**
     * Add Grade by Professor
     * @param professorID
     * @throws GradeNotAddedException
     */
    public void addGrade(String professorID) throws GradeNotAddedException;

    /**
     * View Enrolled Students in Courses taught by Professor
     * @param professorID
     * @return
     */
    public List<Pair<String, String>> viewEnrolledStudents(String professorID);

    /**
     * Show Courses taught by Professor
     * @param professorID
     * @return
     */
    public List<String> getCourses(String professorID);
}
