package com.flipkart.service;

import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.ProfessorDAOOperation;
import javafx.util.Pair;

import java.util.*;

public class ProfessorImplementation implements ProfessorInterface{
    /**
     *
     */
    ProfessorDAOInterface professorDAOInterface = new ProfessorDAOOperation();
    @Override
    public void addGrade(String professorID) {
        List< Pair<String,String>> enrolledStudents = viewEnrolledStudents(professorID);
        Scanner scanner = new Scanner(System.in);
        for(Pair<String,String> enrolled:enrolledStudents) {
            System.out.println("CourseID: " + enrolled.getKey() + ", StudentID: " + enrolled.getValue() +  " Grade: ");
            String grade = scanner.next();
            professorDAOInterface.addGrade(grade,enrolled.getKey(),enrolled.getValue());
        }
    }

    /**
     *
     */
    @Override
    public List<Pair<String, String>> viewEnrolledStudents(String professorID) {
        return professorDAOInterface.viewEnrolledStudents(professorID);
    }

    /**
     *
     */
    @Override
    public List<String> getCourses(String professorID) {
        return professorDAOInterface.getCourses(professorID);
    }
}
