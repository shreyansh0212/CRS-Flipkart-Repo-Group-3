package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;

/**
 * Service Interface for User
 */
public interface UserInterface {

    void updatePassword(String userID,String password) throws UserNotFoundException;
    String getRole(String userID);
    boolean verifyCredentials(String userID,String password) throws UserNotFoundException;
}
