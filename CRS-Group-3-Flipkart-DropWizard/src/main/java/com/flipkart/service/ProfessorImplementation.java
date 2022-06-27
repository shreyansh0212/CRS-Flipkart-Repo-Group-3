package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.ProfessorDAOOperation;
import com.flipkart.exception.GradeNotAddedException;

import java.time.LocalDateTime;
import java.util.*;

public class ProfessorImplementation implements ProfessorInterface{

    ProfessorDAOInterface professorDAOInterface = new ProfessorDAOOperation();

    /**
     * Print Login Message
     * @param userID
     */
    @Override
    public void loginMsg(String userID){
        String username = professorDAOInterface.getUsername(userID);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Professor - " + username + "(" + userID + ") has logged in at time " + localDateTime);
    }


    /**
     * View Enrolled Students in Courses taught by Professor
     *
     * @param professorID
     * @return
     */
    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String professorID) {
        return professorDAOInterface.viewEnrolledStudents(professorID);
    }
    @Override
    public void addGrade(String courseID,String studentID,String grade) throws GradeNotAddedException {

            try {
                professorDAOInterface.addGrade(grade,courseID,studentID);
            } catch (GradeNotAddedException e) {
                throw e;
            }
    }


    /**
     * Show Courses taught by Professor
     *
     * @param professorID
     * @return
     */
    @Override
    public List<Course> getCourses(String professorID) {

        return professorDAOInterface.getCourses(professorID);
    }
}
