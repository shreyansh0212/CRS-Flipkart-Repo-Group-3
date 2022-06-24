package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.service.ProfessorImplementation;
import com.flipkart.service.ProfessorInterface;

import java.util.Scanner;

/**
 *
 *  Class for professor Interface
 *
 */

public class ProfessorCRSMenu {
    ProfessorInterface professorImpl;
    Scanner scanner;

    /**
     *
     * Default Constructor
     *
     */
    public ProfessorCRSMenu() {
        scanner = new Scanner(System.in);
        professorImpl = new ProfessorImplementation();
    }

    /**
     *
     * Parameterized Constructor
     * @param userID
     */
    public void showMenu(String userID) {
        professorImpl.loginMsg(userID);
        int input=0;
        do {
            createMenu();
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    getCourses(userID);
                    break;

                case 2:
                    viewEnrolledStudents(userID);
                    break;

                case 3:
                    addGrade(userID);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Input");
            }
        }
        while(input!=4);
    }

    /**
     *
     * Method to create Admin Menu
     *
     */

    public void createMenu() {
        System.out.println("---------- Welcome Professor ----------");
        System.out.println("Please select your function:");
        System.out.println("1. Get Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. Add Grade");
        System.out.println("4. Exit");
        System.out.println("Enter Your Choice: ");
    }

    /**
     *  Method to get listed courses for professor
     * @param professorID
     */

    public void getCourses(String professorID) {
        System.out.println("------- Listed Courses --------");
        professorImpl.getCourses(professorID);
    }

    /**
     * Method to add grades of students
     * @param professorID
     */

    public void addGrade(String professorID) {
        System.out.println("------ Add Grades -------");
        professorImpl.addGrade(professorID);
    }

    /**
     *  Method to get the list of students for Class.
     * @param professorID
     */

    public void viewEnrolledStudents(String professorID) {
        System.out.println("------- View Enrolled Students -------");
        professorImpl.viewEnrolledStudents(professorID);
    }
}
