package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.*;

/**
 * Service Interface for Admin
 */
public interface AdminInterface {
    /**
     * Print login Message
     * @param userID
     */
    public void loginMsg(String userID);

    /**
     * View Pending Course Registration
     * @param studentId
     */
    public void viewCourseRegistration(String studentId);

    /**
     * Approve Course Registration
     * @param studentId
     * @throws UserNotFoundException
     */
    public void approveCourseRegistration(String studentId) throws UserNotFoundException;

    /**
     * Approve Admission Registration
     * @param studentid
     */
    public void approveStudent(String studentid);

    /**
     * Add New Professor
     * @param professor
     * @throws UserAlreadyExist
     * @throws ProfessorNotAdded
     * @throws UserNotAdded
     * @throws ProfessorAlreadyExistsException
     */
    public void addProfessor(Professor professor) throws UserAlreadyExist, ProfessorNotAdded, UserNotAdded, ProfessorAlreadyExistsException;

    /**
     * Add Course to Catalog
     * @param course
     * @throws CourseAlreadyPresent
     */
    public void addCourse(Course course) throws CourseAlreadyPresent;

    /**
     * Delete course from Catalog
     * @param courseID
     * @throws CourseNotPresentException
     */
    public void dropCourse(String courseID) throws CourseNotPresentException;

    /**
     * Show Course Catalog
     */
    public void showCourses();

    /**
     * Show Pending Requests
     */
    public void PendingRequests();

    /**
     * Show Non Registered Students
     */
    public void NonRegisteredstudentlist();
}
