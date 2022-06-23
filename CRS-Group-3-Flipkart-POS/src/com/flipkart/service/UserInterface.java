package com.flipkart.service;

public interface UserInterface {

    void updatePassword(String userID,String password);
    String getRole(String userID);
    boolean verifyCredentials(String userID,String password);
}
