package com.flipkart.dao;

import javafx.util.Pair;

import java.util.List;

public interface StudentDAOInterface {
    public void preferenceUpdate(String userID, List<String> preference);
    public void addCourse(String userID, String courseID);
    public void dropCourse(String userID, String courseID);

    public List<String> viewEnrolledCourses(String userID);
    public List<Pair<String, String>> viewGrades(String userID);

    public boolean isRegistered(String userID);

    public boolean isFeePaymentStatus(String userID);

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt);

    void newRegistration(String studentID, String password, String name, String batch, String address);
}
