package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

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
     *
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
    public void addProfessor(Professor professor) throws UserAlreadyExist, ProfessorNotAdded, UserNotAdded, ProfessorAlreadyExistsException, SQLException;

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
    public List<Course> showCourses();

    /**
     * Show Pending Requests
     *
     * @return
     */
    public List<Student> pendingRequests();

    /**
     * Show Non Registered Students
     *
     * @return
     */
    public List<Student> viewPendingRegistrations();

    void assignCourse(String courseCode, String professorId);
}
