package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.service.AdminImplementation;
import com.flipkart.service.AdminInterface;

import java.util.Scanner;

public class AdminCRSMenu {
    AdminInterface adminImpl;
    Scanner scanner;
    public AdminCRSMenu() {
        scanner = new Scanner(System.in);
        adminImpl = new AdminImplementation();
    }
    public void showMenu(String userID) {
        adminImpl.loginMsg(userID);
        int input=0;
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
                    break;

                default:
                    System.out.println("Invalid Input");
            }
        }
        while(input!=7);

    }

    public void createMenu() {
        System.out.println("---------- Welcome Admin ----------");
        System.out.println("Please select your function:");
        System.out.println("1. Add Professor");
        System.out.println("2. Show Course Catalog");
        System.out.println("3. Add Course to Catalog");
        System.out.println("4. Delete Course from Catalog");
        System.out.println("5. Approve Student");
        System.out.println("6. Approve Student Requests for Registration");
        System.out.println("7. Exit");
        System.out.println("Enter Your Choice: ");
    }

    public void addProfessor() {

        System.out.println("-------------- Add Professor -------------");


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

        adminImpl.addProfessor(professor);
    }

    public void showCourseCatalog() {
        System.out.println("-------------- Show Courses -------------");
        adminImpl.showCourses();
    }

    public void addCourseToCatalog() {
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

        adminImpl.addCourse(course);
    }

    public void deleteCourseFromCatalog() {
        showCourseCatalog();
        System.out.println("-------------- Drop Course -------------");

        System.out.println("Enter Course Id:");
        String courseId = scanner.next();

        adminImpl.dropCourse(courseId);
    }

    public void approveStudent() {
        System.out.println("-------------- Approve Student -------------");

        System.out.println("Enter Student Id:");
        String studentId = scanner.next();

        adminImpl.approveStudent(studentId);
    }
     public void approvePendingRequests() {

     }
}

