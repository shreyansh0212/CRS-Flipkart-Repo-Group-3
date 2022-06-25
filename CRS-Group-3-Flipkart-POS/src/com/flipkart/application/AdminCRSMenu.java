package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.*;
import com.flipkart.service.AdminImplementation;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.NotificationImplementation;
import com.flipkart.service.NotificationInterface;

import java.util.Scanner;

/**
 *
 *  Class to show interface for Admin
 *
 */
public class AdminCRSMenu {
    AdminInterface adminImpl;
    Scanner scanner;

    /**
     *
     * Default Constructor
     *
     */
    public AdminCRSMenu() {
        scanner = new Scanner(System.in);
        adminImpl = new AdminImplementation();
    }

    /**
     *  Parameterized Constructor
     * @param userID
     */
    public void showMenu(String userID) {
        try{
            adminImpl.loginMsg(userID);
            int input = 0;
            do {
                createMenu();
                input = scanner.nextInt();
                switch (input) {
                    case 1:
                        addProfessor();
                        break;

                    case 2:
                        showCourseCatalog();
                        break;

                    case 3:
                        addCourseToCatalog();
                        break;

                    case 4:
                        deleteCourseFromCatalog();
                        break;

                    case 5:
                        approveStudent();
                        break;

                    case 6:
                        approvePendingRequests();
                        break;

                    case 7:
                        approveCourseRegistration(userID);
                        break;

                    case 8:
                        break;

                    default:
                        System.out.println("Invalid Input");
                }
            }
            while (input != 8);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     *
     * Method to create Admin Menu
     *
     */

    public void createMenu() {
        System.out.println("---------- Welcome Admin ----------");
        System.out.println("Please select your function:");
        System.out.println("1. Add Professor");
        System.out.println("2. Show Course Catalog");
        System.out.println("3. Add Course to Catalog");
        System.out.println("4. Delete Course from Catalog");
        System.out.println("5. Approve Student");
        System.out.println("6. View Pending Registration Requests");
        System.out.println("7. Approve Course Registration of Students");
        System.out.println("8. Exit");
        System.out.println("Enter Your Choice: ");
    }

    /**
     *  Method to ADD a new professor
     * @throws UserAlreadyExist
     */

    public void addProfessor() throws UserAlreadyExist {

        System.out.println("-------------- Add Professor -------------");

    /**
     *
     * Method to approve a new student
     */

        Professor professor = new Professor();

        System.out.println("Enter User Id:");
        String userId = scanner.next();
        professor.setUserID(userId);

        System.out.println("Enter Password:");
        String password = scanner.next();
        professor.setPassword(password);

        System.out.println("Enter Professor Name:");
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.println("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        professor.setRole("professor");

        try{
            adminImpl.addProfessor(professor);
        }catch(UserAlreadyExist | ProfessorNotAdded | UserNotAdded | ProfessorAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to get the courses list
     */

    public void showCourseCatalog() {
        System.out.println("-------------- Show Courses -------------");
        adminImpl.showCourses();
    }

    /**
     *
     * Method to ADD a new course
     * @throws CourseAlreadyPresent
     */

    public void addCourseToCatalog() throws CourseAlreadyPresent {
        Course course = new Course();
        System.out.println("-------------- Add Course -------------");
        System.out.println("Enter Course Id:");
        String courseId = scanner.next();
        course.setCourseID(courseId);

        System.out.println("Enter Course Name:");
        String courseName = scanner.next();
        course.setCourseName(courseName);

        System.out.println("Enter Professor Id:");
        String professorId = scanner.next();
        course.setProfessorID(professorId);

        course.setOffered(true);
        course.setCourseStrength(0);

        try{
            adminImpl.addCourse(course);
        }catch(CourseAlreadyPresent e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to delete a course
     */

    public void deleteCourseFromCatalog() {
        showCourseCatalog();
        System.out.println("-------------- Drop Course -------------");

        System.out.println("Enter Course Id:");
        String courseId = scanner.next();

        try{
            adminImpl.dropCourse(courseId);
        }catch(CourseNotPresentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * Used to Approve Student Registration
     *
     */

    public void approveStudent() {
        System.out.println("-------------- Approve Student -------------");

        System.out.println("Enter Student Id:");
        String studentId = scanner.next();

        adminImpl.approveStudent(studentId);
    }

    /**
     * Used to Approve Student Course Registration
     * @param adminID
     * @throws UserNotFoundException
     */
    public void approveCourseRegistration(String adminID) throws UserNotFoundException {
        System.out.println("-------------- Approve Course Registration for Students -------------");
        System.out.println("Enter Student Id:");
        String studentId = scanner.next();
        adminImpl.viewCourseRegistration(studentId);
        adminImpl.approveCourseRegistration(studentId);
    }

    /**
     *
     *  Method to get list of students waiting for approval.
     *
     */
     public void approvePendingRequests() {
         System.out.println("-------------- Students Waiting for approval-------------");
         adminImpl.PendingRequests() ;
     }
}

