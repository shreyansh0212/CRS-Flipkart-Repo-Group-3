package com.flipkart.service;

public interface UserInterface {
    public int login(String userID, String password);
    void updatePersonalDetails();
    void updatePassword(String userID,String password);
    String getRole(String userID);
    boolean verifyCredentials(String userID,String password);
}
