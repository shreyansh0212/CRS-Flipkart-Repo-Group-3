package com.flipkart.service;

public interface UserInterface {
    public int login(String userID, String password);
    void updatePersonalDetails();
    void updatePassword();
    void showPersonalDetails();
}
