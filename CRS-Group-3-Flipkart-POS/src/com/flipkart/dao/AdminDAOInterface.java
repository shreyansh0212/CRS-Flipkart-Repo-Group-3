package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

public interface AdminDAOInterface {
    public void approveStudent(String studentID);
    public void addUser(User user);
    public void addProfessor(Professor professor);
    public void generateReport();
    public void addCourse(Course course);
    public void dropCourse(String courseID);
    public void showCourses();
    public List<Student> approvePendingRequests();
}
