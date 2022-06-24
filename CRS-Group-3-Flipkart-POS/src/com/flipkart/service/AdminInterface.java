package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminInterface {
    public void loginMsg(String userID);
    public void approveStudent(String studentid);
    public void addProfessor(Professor professor);
    public void generateReport();
    public void addCourse(Course course);
    public void dropCourse(String courseID);
    public void showCourses();

    void approvePendingRequests();
}
