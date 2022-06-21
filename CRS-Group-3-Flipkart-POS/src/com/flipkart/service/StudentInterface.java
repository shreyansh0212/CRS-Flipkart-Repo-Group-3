package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.List;

public interface StudentInterface {

    public void registerCourses(Student student);
    public void addCourse(Student student);
    public void dropCourse(Student student);
    public List<String> viewEnrolledCourses(Student student);
    public void payFees(Student student);
    void changePassword(Student student);
    void viewGradeCard(Student student);

    void showCourses();
}
