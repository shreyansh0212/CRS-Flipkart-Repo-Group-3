package com.flipkart.dao;

import javafx.util.Pair;

import java.util.List;

public interface StudentDAOInterface {
    public void preferenceUpdate(String userID, List<String> preference);
    public void addToRegistration(String userID, String courseID);
    public void dropFromRegistration(String userID, String courseID);

    public List<String> viewEnrolledCourses(String userID);
    public List<Pair<String, String>> grades(String userID);
    public void showCourses();

    public void preferenceShow(String userID);

    public boolean isRegistered(String userID);

    public boolean isFeePaymentStatus(String userID);

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt);
}
