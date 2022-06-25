package com.flipkart.service;

import com.flipkart.dao.*;
import com.flipkart.exception.*;
import javafx.util.Pair;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentImplementation implements StudentInterface{

    StudentDAOInterface studentDAOInterface = new StudentDAOOperation();

    /**
     * Print Login Message
     * @param userID
     */
    @Override
    public void loginMsg(String userID){
        String username = studentDAOInterface.getUsername(userID);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Student - " + username + "(" + userID + ") has logged in at time " + localDateTime);
    }

    /**
     * Student Course Registration
     * @param userID
     * @throws SQLException
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     * @throws SeatNotAvailableException
     */
    @Override
    public void registerCourses(String userID) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException, SeatNotAvailableException {
        List<String> preference = new ArrayList<>();

        AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
        adminDAOInterface.showCourses();
        try{
            System.out.println("Select four primary and two alternative courses using CourseID: ");
            Scanner in = new Scanner(System.in);
            for(int i=1;i<=6;i++) {
                System.out.println("Preference " + i + ". Enter CourseID: ");
                String courseID = in.next();
                try {
                    studentDAOInterface.checkCourseAvailability(courseID);
                    preference.add(courseID);
                }catch (CourseNotPresentException e){
                    throw e;
                }
            }
            studentDAOInterface.preferenceUpdate(userID,preference);
        }catch (CourseAlreadyRegistered | CourseNotPresentException | CourseLimitExceededException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add Course by Student
     * @param userID
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     */
    @Override
    public void addCourse(String userID) throws CourseAlreadyRegistered,CourseNotPresentException {
        showCourses();
        System.out.println("Enter CourseID to add Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
        try{
            studentDAOInterface.addCourse(courseID,userID);
            System.out.println("Course Added with CourseID: " + courseID);
        }catch(CourseAlreadyRegistered | CourseNotPresentException | SeatNotAvailableException |
               CourseLimitExceededException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Drop Course by Student
     * @param userID
     * @throws CourseNotPresentException
     */
    @Override
    public void dropCourse(String userID) throws CourseNotPresentException {
        List<String> enrolledCourses = studentDAOInterface.viewEnrolledCourses(userID);
        System.out.println("Enter CourseID to drop Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
        studentDAOInterface.dropCourse(userID,courseID);
    }

    /**
     * View Student Enrolled Courses
     * @param userID
     */
    @Override
    public void viewEnrolledCourses(String userID) {
        studentDAOInterface.viewEnrolledCourses(userID);
    }

    /**
     * Pay Fees
     * @param userID
     */
    @Override
    public void payFees(String userID){
        try {
            if(studentDAOInterface.isFeePaymentStatus(userID)) {
                throw new FeesAlreadyPaid(userID);
            }
            if(!studentDAOInterface.isRegistered(userID)) {
                throw new StudentNotRegistered(userID);
            }
            System.out.println("Select Payment Method: ");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit Card");
            System.out.println("3. Net Banking");
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input) {
                case 1:
                    studentDAOInterface.setFeePaymentStatus(userID,"Credit Card",userID,100);
                    break;

                case 2:
                    studentDAOInterface.setFeePaymentStatus(userID,"Debit Card",userID,100);
                    break;

                case 3:
                    studentDAOInterface.setFeePaymentStatus(userID,"Net Banking",userID,100);
                    break;

                default:
                    System.out.println("Invalid Selection");
            }
        } catch (FeesAlreadyPaid | UserNotFoundException | StudentNotRegistered e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * View Grade Card
     * @param userID
     */
    @Override
    public void viewGradeCard(String userID) {
        System.out.println("Student Grades");
        List<Pair<String,String>> enrolledCourses = studentDAOInterface.viewGrades(userID);
        for(int i=0;i<enrolledCourses.size();i++) {
            System.out.println("CourseID: " + enrolledCourses.get(i).getKey() + " --> Grade: " + enrolledCourses.get(i).getValue());
        }

    }

    /**
     * New Registration
     * @param studentID
     * @param password
     * @param name
     * @param batch
     * @param address
     * @throws UserAlreadyExist
     * @throws UserNotAdded
     */
    @Override
    public void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist, UserNotAdded {
        try{
            studentDAOInterface.newRegistration(studentID,password,name,batch,address);
        }catch(UserAlreadyExist | UserNotAdded e){
            throw e;
        }
    }

    /**
     * Show Course Catalog
     */
    @Override
    public void showCourses() {
        AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
        adminDAOInterface.showCourses();
    }

    /**
     * Check Student Approval Status
     * @param userID
     * @return
     * @throws UserNotFoundException
     * @throws StudentNotApproved
     */
    @Override
    public Boolean checkApprovalStatus(String userID) throws UserNotFoundException, StudentNotApproved {
        Boolean approvalStatus = studentDAOInterface.checkApprovalStatus(userID);
        if(!approvalStatus){
            throw new StudentNotApproved(userID);
        }else{
            return true;
        }

    }

}
