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

    public void loginMsg(String userID){
        String username = studentDAOInterface.getUsername(userID);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Student - " + username + "(" + userID + ") has logged in at time " + localDateTime);
    }

    /**
     * @param userID
     */
    @Override
    public void registerCourses(String userID) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException {
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
     * @param userID
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
        }catch(CourseAlreadyRegistered | CourseNotPresentException | CourseLimitExceededException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param userID
     */
    @Override
    public void dropCourse(String userID) throws CourseNotPresentException {
        List<String> enrolledCourses = studentDAOInterface.viewEnrolledCourses(userID);
        System.out.println("Enter CourseID to drop Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
/*
        boolean ifprsnt = studentDAOInterface.chkRegistration(userID,courseID);
        if(!ifprsnt){
            throw new CourseNotPresentException();
        }
*/
        studentDAOInterface.dropCourse(userID,courseID);
    }

    /**
     * @param userID
     */
    @Override
    public void viewEnrolledCourses(String userID) {
//        if(!studentDAOInterface.isRegistered(userID)){
//            System.out.println("Registration has not been completed yet.");
//            return;
//        }
        studentDAOInterface.viewEnrolledCourses(userID);

    }

    /**
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
                    System.out.println("Used Credit Card");
                    break;

                case 2:
                    studentDAOInterface.setFeePaymentStatus(userID,"Debit Card",userID,100);
                    System.out.println("Used Debit Card");
                    break;

                case 3:
                    studentDAOInterface.setFeePaymentStatus(userID,"Net Banking",userID,100);
                    System.out.println("Used Net Banking");
                    break;

                default:
                    System.out.println("Invalid Selection");
            }
        } catch (FeesAlreadyPaid | UserNotFoundException | StudentNotRegistered e) {
            System.out.println(e.getMessage());
        }

    }

    /**
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
     * @param studentID
     * @param name
     * @param batch
     * @param address
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
     *
     */
    @Override
    public void showCourses() {
        AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
        adminDAOInterface.showCourses();
    }

    /**
     * @param userID
     * @return
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
