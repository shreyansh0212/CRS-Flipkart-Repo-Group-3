package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAOInterface {
    public String getUsername(String userID);
    public List<String> getCourses(String studentID);
    public void approveCourseRegistration(String studentId) throws UserNotFoundException;
    public void approveStudent(String studentID);
    public void addUser(User user) throws UserAlreadyExist, UserNotAdded;
    public void addProfessor(Professor professor) throws UserAlreadyExist, UserNotAdded, ProfessorNotAdded, ProfessorAlreadyExistsException, SQLException;
    public void generateReport();
    public void addCourse(Course course) throws CourseAlreadyPresent;
    public void dropCourse(String courseID) throws CourseNotPresentException;
    public List<Course> showCourses();
    public List<Student> viewPendingRequests();

    public List<Student> viewPendingRegistrations();

    void assignCourse(String courseCode, String professorId);
}
