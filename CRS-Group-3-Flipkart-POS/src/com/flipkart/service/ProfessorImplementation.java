package com.flipkart.service;

import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.ProfessorDAOOperation;
import com.flipkart.exception.GradeNotAddedException;
import javafx.util.Pair;

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
     * Add Grade by Professor
     * @param professorID
     * @throws GradeNotAddedException
     */
    @Override
    public void addGrade(String professorID) throws GradeNotAddedException {
        List< Pair<String,String>> enrolledStudents = viewEnrolledStudents(professorID);
        Scanner scanner = new Scanner(System.in);
        for(Pair<String,String> enrolled:enrolledStudents) {
            System.out.println("CourseID: " + enrolled.getKey() + ", StudentID: " + enrolled.getValue() +  " Grade: ");
            String grade = scanner.next();
            try {
                professorDAOInterface.addGrade(grade,enrolled.getKey(),enrolled.getValue());
            } catch (GradeNotAddedException e) {
                throw e;
            }
        }
    }

    /**
     * View Enrolled Students in Courses taught by Professor
     * @param professorID
     * @return
     */
    @Override
    public List<Pair<String, String>> viewEnrolledStudents(String professorID) {
        return professorDAOInterface.viewEnrolledStudents(professorID);
    }

    /**
     * Show Courses taught by Professor
     * @param professorID
     * @return
     */
    @Override
    public List<String> getCourses(String professorID) {
        return professorDAOInterface.getCourses(professorID);
    }
}
