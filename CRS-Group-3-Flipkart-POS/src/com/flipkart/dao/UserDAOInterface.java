package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

/**
 * Interface for the User DAO operations
 */
public interface UserDAOInterface {
    // boolean -> return status of function
    public boolean verifyCredentials(String userID, String password) throws UserNotFoundException;

    boolean updatePassword(String userID, String password) throws UserNotFoundException;

    public String getRole(String userID);
}
