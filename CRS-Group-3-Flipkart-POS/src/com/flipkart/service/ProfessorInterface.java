package com.flipkart.service;

import javafx.util.Pair;

import java.util.List;

public interface ProfessorInterface {
    public void loginMsg(String userID);
    public void addGrade(String professorID);
    public List<Pair<String, String>> viewEnrolledStudents(String professorID);
    public List<String> getCourses(String professorID);
}
