package com.flipkart.dao;

public interface UserDAOInterface {
    // boolean -> return status of function
    public boolean verifyCredentials(String userID, String password);
    public boolean updatePassword(String userID, String password);
    public String getRole(String userID);
}
