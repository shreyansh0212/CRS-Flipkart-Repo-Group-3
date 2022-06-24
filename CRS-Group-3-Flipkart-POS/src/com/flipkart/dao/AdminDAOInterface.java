package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;

public interface AdminDAOInterface {
    public String getUsername(String userID);
    public List<String> getCourses(String studentID);
    public void updIsRegistered(String studentId, boolean approval);
    public void approveStudent(String studentID);
    public void addUser(User user) throws UserAlreadyExist;
    public void addProfessor(Professor professor) throws UserAlreadyExist;
    public void generateReport();
    public void addCourse(Course course) throws CourseAlreadyPresent;
    public void dropCourse(String courseID) throws CourseNotPresentException;
    public void showCourses();
    public void viewPendingRequests();
}
