package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.service.ProfessorImplementation;
import com.flipkart.service.ProfessorInterface;

import java.util.Scanner;

import static com.flipkart.application.CRSApplication.professorDB;

public class ProfessorCRSMenu {
    ProfessorInterface professorImpl;
    public ProfessorCRSMenu() {
        professorImpl = new ProfessorImplementation();
    }
    public void showMenu(String userID) {
        Scanner in = new Scanner(System.in);
        int input=0;
        do {
            createMenu();
            input = in.nextInt();

            switch (input) {
                case 1:
                    professorImpl.getCourses(userID);
                    break;

                case 2:
                    System.out.println("Enter CourseID to view Students: ");
                    String courseID = in.next();
                    professorImpl.viewEnrolledStudents(courseID);
                    break;

                case 3:
                    System.out.println("Enter CourseID to add Grades: ");
                    courseID = in.next();
                    professorImpl.addGrade(courseID);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Input");
            }

        }
        while(input!=4);

    }

    public void createMenu() {
        System.out.println("---------- Welcome Professor ----------");
        System.out.println("Please select your function:");
        System.out.println("1. Get Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. Add Grade");
        System.out.println("4. Exit");
        System.out.println("Enter Your Choice: ");
    }
}
