package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.StudentInterface;

import java.util.Scanner;

public class StudentCRSMenu {
    StudentInterface studentImpl;
    Scanner scanner;
    public StudentCRSMenu() {

        studentImpl = new StudentImplementation();
        scanner = new Scanner(System.in);

    }

    public void showMenu(String userID) {
        studentImpl.loginMsg(userID);
        int input = 0;
        do {
            createMenu();
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    registerCourses(userID);
                    break;

                case 2:
                    addCourse(userID);
                    break;

                case 3:
                    dropCourse(userID);
                    break;

                case 4:
                    viewEnrolledCourses(userID);
                    break;
                case 5:
                    payFees(userID);
                    break;

                case 6:
                    viewGradeCard(userID);
                    break;

                case 7:
                    showCourses();
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Invalid Selection");
            }
        }
        while(input!=8);
    }

    public void createMenu() {
        System.out.println("---------- Welcome Student ----------");
        System.out.println("Please select your function:");
        System.out.println("1. Register Courses: ");
        System.out.println("2. Add Course: ");
        System.out.println("3. Drop Course: ");
        System.out.println("4. View Enrolled Courses");
        System.out.println("5. Pay Fees");
        System.out.println("6. View Grade Card");
        //System.out.println("7. Change Password");
        System.out.println("7. See Course Catalog");
        System.out.println("8. Exit");
        System.out.println("Enter Your Choice: ");
    }

    public void registerCourses(String userID) {
        studentImpl.registerCourses(userID);
    }

    public void addCourse(String userID) {
        try {
            studentImpl.addCourse(userID);
        } catch (CourseAlreadyPresent e) {
            throw new RuntimeException(e);
        }
    }

    public void dropCourse(String userID) {
        try {
            studentImpl.dropCourse(userID);
        } catch (CourseNotPresentException e) {
            throw new RuntimeException(e);
        }
    }

    public void payFees(String userID) {
        studentImpl.payFees(userID);
    }

    public void viewEnrolledCourses(String userID) {
        studentImpl.viewEnrolledCourses(userID);
    }

    public void showCourses() {
        studentImpl.showCourses();
    }

    public void viewGradeCard(String userID) {
        studentImpl.viewGradeCard(userID);
    }
}
