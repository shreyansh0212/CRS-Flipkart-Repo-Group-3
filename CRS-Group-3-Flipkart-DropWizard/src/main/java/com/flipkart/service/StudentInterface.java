package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Payment;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Service Interface for Student
 */
public interface StudentInterface {
    Boolean getApprovalStatus(String userID) throws UserNotFoundException;
    /**
     * Print Login Message
     * @param userID
     */
    public void loginMsg(String userID);

    /**
     * Student Course Registration
     * @param userID
     * @throws SQLException
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     * @throws SeatNotAvailableException
     */
    public void registerCourses(String userID) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException, SeatNotAvailableException;

    /**
     * Add Course by Student
     * @param userID
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     */
    public void addCourse(String courseID,String userID) throws CourseAlreadyRegistered, CourseNotPresentException, SeatNotAvailableException, CourseLimitExceededException;

    /**
     * Drop Course by Student
     * @param userID
     * @throws CourseNotPresentException
     */
    public void dropCourse(String courseID,String userID) throws CourseNotPresentException;

    /**
     * View Student Enrolled Courses
     *
     * @param userID
     */
    public List<EnrolledStudent> viewEnrolledCourses(String userID);

    /**
     * Pay Fees
     *
     * @param userID
     * @return
     */
    public Payment payFees(String userID,float amount, String mode) throws UserNotFoundException, StudentNotRegistered, SQLException, FeesAlreadyPaid;

    /**
     * View Grade Card
     *
     * @param userID
     * @return
     */
    List<StudentGrade> viewGradeCard(String userID);

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
    void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist, UserNotAdded;

    /**
     * Show Course Catalog
     */
    List<Course> showCourses();

    /**
     * Check Student Approval Status
     * @param userID
     * @return
     * @throws UserNotFoundException
     * @throws StudentNotApproved
     */
    Boolean checkApprovalStatus(String userID) throws UserNotFoundException, StudentNotApproved;

    Boolean getRegistrationStatus(String studentID);

    void setRegistrationStatus(String studentID);

    boolean checkCourse(String courseCode, String studentId, List<EnrolledStudent> availableCourseList) throws CourseLimitExceededException, SeatNotAvailableException, CourseAlreadyRegistered, CourseNotPresentException;

    void preferenceUpdate(String studentID, List<String> preference) throws SQLException, CourseAlreadyRegistered, SeatNotAvailableException, CourseNotPresentException, CourseLimitExceededException;
}
