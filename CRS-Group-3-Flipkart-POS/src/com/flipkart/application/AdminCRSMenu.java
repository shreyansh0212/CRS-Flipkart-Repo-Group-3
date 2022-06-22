package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.service.AdminImplementation;
import com.flipkart.service.AdminInterface;

import java.util.Scanner;

public class AdminCRSMenu {
    AdminInterface adminImpl;
    public AdminCRSMenu() {
        adminImpl = new AdminImplementation();
    }
    public void showMenu(String userID) {

        Scanner in = new Scanner(System.in);
        int input=0;
        do {
            createMenu();
            input = in.nextInt();
            switch (input) {
                case 1:
                    Professor prof1 = new Professor("002","prof1","prof","pass",null,null,null,null);
//                    System.out.println("Enter ProfessorID: ");
//                    String profID = in.next();
//                    System.out.println("Enter Professor Name: ");
//                    String profName = in.next();
                    adminImpl.addProfessor(prof1);
                    break;

                case 2:
                    adminImpl.showCourses();
                    break;

                case 3:
                    System.out.println("Enter CourseID to add: ");
                    String courseID = in.next();
                    System.out.println("Enter Course Name to add: ");
                    String courseName = in.next();
                    System.out.println("Enter ProfessorID to add: ");
                    String professorID = in.next();
                    Course course = new Course(courseID,courseName,false,professorID);
                    adminImpl.addCourse(course);
                    break;

                case 4:
                    System.out.println("Enter CourseID to Drop: ");
                    adminImpl.dropCourse(in.next());
                    break;

                case 5:
                    adminImpl.approveStudent();
                    break;

                case 6:
                    adminImpl.approvePendingRequests();
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

//    public void addProfessor() {
//
//    }
//
//    public void showCourseCatalog() {
//
//    }
//
//    public void addCourseToCatalog() {
//
//    }
//
//    public void deleteCourseFromCatalog() {
//
//    }
//
//    public void approveStudent() {
//
//    }

}

