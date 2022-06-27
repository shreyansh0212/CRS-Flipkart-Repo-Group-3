package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.GradeNotAddedException;

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
    public void addGrade(String courseID,String studentID,String grade) throws GradeNotAddedException;

    /**
     * View Enrolled Students in Courses taught by Professor
     *
     * @param professorID
     * @return
     */
    public List<EnrolledStudent> viewEnrolledStudents(String professorID);

    /**
     * Show Courses taught by Professor
     *
     * @param professorID
     * @return
     */
    public List<Course> getCourses(String professorID);
}
