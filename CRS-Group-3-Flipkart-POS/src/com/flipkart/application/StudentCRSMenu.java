package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.StudentInterface;

import java.util.Scanner;

public class StudentCRSMenu {
    StudentInterface studentImpl;
    Scanner scanner;
    public StudentCRSMenu() {

        scanner = new Scanner(System.in);
        studentImpl = new StudentImplementation();
    }

    public void showMenu(String userID) {

        int input = 0;
        do {
            createMenu();
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    registerCourses();
                    break;

                case 2:
                    addCourse();
                    break;

                case 3:
                    dropCourse();
                    break;

                case 4:
                    viewEnrolledCourses();
                    break;

                case 5:
                    payFees();
                    break;

                case 6:
                    viewGradeCard();
                    break;

                case 7:
                    viewCourseCatalog();
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Invalid Selection");
            }
        }
        while(input!=8);
    }

    private void viewCourseCatalog() {

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
        System.out.println("7. Change Password");
        System.out.println("8. See Course Catalog");
        System.out.println("9. Exit");
        System.out.println("Enter Your Choice: ");
    }

    public void registerCourses() {

    }

    public void addCourse() {

    }

    public void dropCourse() {

    }

    public void payFees() {

    }

    public void viewEnrolledCourses() {

    }

    public void viewCourses() {
        // used to check available courses
    }

    public void viewGradeCard() {

    }
}
