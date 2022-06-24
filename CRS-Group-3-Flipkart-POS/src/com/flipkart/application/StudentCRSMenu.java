package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseAlreadyRegistered;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.StudentInterface;

import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 *  Class to show interface for student
 *
 */

public class StudentCRSMenu {
    StudentInterface studentImpl;
    Scanner scanner;
    /**
     *
     * Default Constructor
     *
     */
    public StudentCRSMenu() {

        studentImpl = new StudentImplementation();
        scanner = new Scanner(System.in);

    }

    /**
     * Parameterized Constructor
     * @param userID
     */

    public void showMenu(String userID) {
        studentImpl.loginMsg(userID);
        int input = 0;
        try {
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
            while (input != 8);
        }catch(Exception e){
            System.out.println("Exception Message: " + e.getMessage());
        }
    }

    /**
     *
     * Method to create student Menu
     *
     */
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

    /**
     *
     * Method for student to register courses
     *
     * @param userID
     * @throws SQLException
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     */

    public void registerCourses(String userID) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException {
        try{
            studentImpl.registerCourses(userID);
        }catch(CourseAlreadyRegistered | CourseNotPresentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * Method to add courses In preference list
     * @param userID
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     */

    public void addCourse(String userID) throws CourseAlreadyRegistered, CourseNotPresentException {
        try {
            studentImpl.addCourse(userID);
        } catch (CourseAlreadyRegistered | CourseNotPresentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * method to drop the course from course list
     * @param userID
     */
    public void dropCourse(String userID) {
        try {
            studentImpl.dropCourse(userID);
        } catch (CourseNotPresentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Method to pay the fees
     * @param userID
     */
    public void payFees(String userID) {
        studentImpl.payFees(userID);
    }

    /**
     * Method to view the enrolled courses
     * @param userID
     */
    public void viewEnrolledCourses(String userID) {
        studentImpl.viewEnrolledCourses(userID);
    }

    /**
     *
     * method to show all the courses avaliable
     *
     */
    public void showCourses() {
        studentImpl.showCourses();
    }

    /**
     *
     * Method to view the Report card
     * @param userID
     */

    public void viewGradeCard(String userID) {
        studentImpl.viewGradeCard(userID);
    }
}
