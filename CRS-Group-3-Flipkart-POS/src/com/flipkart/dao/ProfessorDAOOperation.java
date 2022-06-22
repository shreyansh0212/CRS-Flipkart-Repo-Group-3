package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.List;

public class ProfessorDAOOperation implements ProfessorDAOInterface{
    /**
     * @param courseID
     */
    @Override
    public void addGrade(String courseID) {

    }

    /**
     * @param courseID
     * @return
     */
    @Override
    public List<Student> viewEnrolledStudents(String courseID) {
        return null;
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public List<String> getCourses(String userID) {
        return null;
    }
}
