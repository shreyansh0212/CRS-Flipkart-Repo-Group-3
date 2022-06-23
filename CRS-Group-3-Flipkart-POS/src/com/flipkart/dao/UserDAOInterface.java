package com.flipkart.dao;

public interface UserDAOInterface {
    public int login(String userID,String password);
    void updatePersonalDetails();
    void updatePassword();
    void showPersonalDetails();
}
