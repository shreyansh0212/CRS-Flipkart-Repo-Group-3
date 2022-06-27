package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Payment;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.*;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentImplementation implements StudentInterface{

    StudentDAOInterface studentDAOInterface = new StudentDAOOperation();
    AdminDAOInterface adminDAOInterface = new AdminDAOOperation();

    @Override
    public Boolean getApprovalStatus(String userID) throws UserNotFoundException {
        return studentDAOInterface.getApprovalStatus(userID);
    }

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
                    studentDAOInterface.checkCourseAvailability(userID,courseID);
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
    public void addCourse(String courseID,String userID) throws CourseAlreadyRegistered, CourseNotPresentException, SeatNotAvailableException, CourseLimitExceededException {
        studentDAOInterface.addCourse(courseID,userID);
    }

    /**
     * Drop Course by Student
     * @param userID
     * @throws CourseNotPresentException
     */
    @Override
    public void dropCourse(String courseID,String userID) throws CourseNotPresentException {
        studentDAOInterface.dropCourse(courseID,userID);
    }

    /**
     * View Student Enrolled Courses
     *
     * @param userID
     */
    @Override
    public List<EnrolledStudent> viewEnrolledCourses(String userID) {
        return studentDAOInterface.viewEnrolledCourses(userID);
    }

    /**
     * Pay Fees
     *
     * @param userID
     * @return
     */
    @Override
    public Payment payFees(String userID,float amount, String mode) throws UserNotFoundException, StudentNotRegistered, SQLException, FeesAlreadyPaid {
        return studentDAOInterface.payFees(userID,amount,mode);
//        try {
//            if(studentDAOInterface.feePaymentStatus(userID)) {
//                throw new FeesAlreadyPaid(userID);
//            }
//            if(!studentDAOInterface.getRegistrationStatus(userID)) {
//                throw new StudentNotRegistered(userID);
//            }
//
//        } catch (FeesAlreadyPaid | UserNotFoundException | StudentNotRegistered e) {
//            System.out.println(e.getMessage());
//        }
//        return
    }

    /**
     * View Grade Card
     *
     * @param userID
     * @return
     */
    @Override
    public List<StudentGrade> viewGradeCard(String userID) {
        System.out.println("Student Grades");
        return studentDAOInterface.viewGrades(userID);
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
    public void newRegistration(String studentID, String name, String password, String batch, String address) throws UserAlreadyExist, UserNotAdded {
        try{
            studentDAOInterface.newRegistration(studentID,name,password,batch,address);
        }catch(UserAlreadyExist | UserNotAdded e){
            throw e;
        }
    }

    /**
     * Show Course Catalog
     */
    @Override
    public List<Course> showCourses() {
        return adminDAOInterface.showCourses();
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
        Boolean approvalStatus = studentDAOInterface.getApprovalStatus(userID);
        if(!approvalStatus){
            throw new StudentNotApproved(userID);
        }else{
            return true;
        }

    }

    @Override
    public Boolean getRegistrationStatus(String studentID) {
        return studentDAOInterface.getRegistrationStatus(studentID);
    }

    @Override
    public void setRegistrationStatus(String studentID) {

    }

    @Override
    public boolean checkCourse(String courseCode, String studentId, List<EnrolledStudent> availableCourseList) throws CourseLimitExceededException, SeatNotAvailableException, CourseAlreadyRegistered, CourseNotPresentException {
    /*
        try {
            int response = studentDAOInterface.checkCourseAvailability(studentId, courseCode);

            if (response == 0){
                throw new CourseLimitExceededException(6);
            }
            else if (response == 1) {
                throw new CourseAlreadyRegistered(courseCode);
            }
            else if (!studentDAOInterface.seatAvailable(courseCode)) {
                throw new SeatNotAvailableException(courseCode);
            }
            else if(!StudentValidator.isValidCourseCode(courseCode, ava)){
                throw new CourseNotPresentException(courseCode);
            }

            return true;

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return false;
        */
        return true;
    }

    @Override
    public void preferenceUpdate(String studentID,List<String> preference) throws SQLException, CourseAlreadyRegistered, SeatNotAvailableException, CourseNotPresentException, CourseLimitExceededException {
        studentDAOInterface.preferenceUpdate(studentID, preference);
    }
}
