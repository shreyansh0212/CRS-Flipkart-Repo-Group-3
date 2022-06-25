package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.exception.*;

import java.time.LocalDateTime;
import java.util.List;


public class AdminImplementation implements AdminInterface{
    AdminDAOInterface adminDAOInterface = new AdminDAOOperation();

    /**
     * Print login Message
     * @param userID
     */
    @Override
    public void loginMsg(String userID){
        String username = adminDAOInterface.getUsername(userID);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Admin - " + username + "(" + userID + ") has logged in at time " + localDateTime);
    }

    /**
     * View Course Registration
     * @param studentId
     */
    @Override
    public void viewCourseRegistration(String studentId){
        List<String> regcourses = adminDAOInterface.getCourses(studentId);
        regcourses.forEach(System.out::println);
    }

    /**
     * Approve Course Registration
     * @param studentId
     * @throws UserNotFoundException
     */
    @Override
    public void approveCourseRegistration(String studentId) throws UserNotFoundException {
        adminDAOInterface.approveCourseRegistration(studentId);
    }

    /**
     * Approve Admission Registration
     * @param studentid
     */
    @Override
    public void approveStudent(String studentid) {
        adminDAOInterface.approveStudent(studentid);
    }

    /**
     * Add New Professor
     * @param professor
     * @throws UserAlreadyExist
     * @throws ProfessorNotAdded
     * @throws UserNotAdded
     * @throws ProfessorAlreadyExistsException
     */
    @Override
    public void addProfessor(Professor professor) throws UserAlreadyExist, ProfessorNotAdded, UserNotAdded, ProfessorAlreadyExistsException {
       try{
           adminDAOInterface.addProfessor(professor);
       }catch (UserAlreadyExist | UserNotAdded | ProfessorNotAdded | ProfessorAlreadyExistsException e){
           throw e;
       }
    }

    /**
     * Add Course to Catalog
     * @param course
     * @throws CourseAlreadyPresent
     */
    @Override
    public void addCourse(Course course) throws CourseAlreadyPresent {
        try{
            adminDAOInterface.addCourse(course);
        }catch(CourseAlreadyPresent e){
            throw e;
        }
    }

    /**
     * Delete course from Catalog
     * @param courseID
     * @throws CourseNotPresentException
     */
    @Override
    public void dropCourse(String courseID) throws CourseNotPresentException {
        try{
            adminDAOInterface.dropCourse(courseID);
        }catch(CourseNotPresentException e){
            throw e;
        }
    }

    /**
     * Show Course Catalog
     */
    @Override
    public void showCourses() {
        adminDAOInterface.showCourses();
    }

    /**
     * Show Pending Requests
     */
    @Override
    public void PendingRequests() {adminDAOInterface.viewPendingRequests();}

    /**
     * Show Non Registered Students
     */
    @Override
    public void NonRegisteredstudentlist(){adminDAOInterface.viewNotRegisteredStudents();}

}

