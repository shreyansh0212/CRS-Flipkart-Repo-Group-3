package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.LoginDAOOperation;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.StudentInterface;

import java.util.Scanner;

public class StudentCRSMenu {
    StudentInterface studentImpl;
    public StudentCRSMenu() {
        studentImpl = new StudentImplementation();
    }

    public void showMenu(String userID) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            createMenu();
            input = in.nextInt();
            //Student student = studentDB.get(userID);
            switch (input) {
                case 1:
                    studentImpl.registerCourses(userID);
                    break;

                case 2:
                    try {
                        studentImpl.addCourse(userID);
                    } catch (CourseAlreadyPresent e){
                        System.out.println(e.getMsg());
                    }
                    break;

                case 3:
                    try {
                        studentImpl.dropCourse(userID);
                    } catch (CourseNotPresentException e){
                        System.out.println(e.getMsg());
                    }
                    break;

                case 4:
                    studentImpl.viewEnrolledCourses(userID);
                    break;
                case 5:
                    studentImpl.payFees(userID);
                    break;

                case 6:
                    studentImpl.viewGradeCard(userID);
                    break;

                case 7:
                    //studentImpl.changePassword(student);
                    break;

                case 8:
                    studentImpl.showCourses();
                    break;

                case 9:
                    break;

                default:
                    System.out.println("Invalid Selection");
            }
        }
        while(input!=9);
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
