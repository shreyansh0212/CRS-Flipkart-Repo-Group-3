package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminDAOInterface {
    public void approveStudent();
    public void addProfessor(Professor professor);
    public void generateReport();
    public void addCourse(Course course);
    public void dropCourse(String courseID);
    public void showCourses();
    void approvePendingRequests();
}
