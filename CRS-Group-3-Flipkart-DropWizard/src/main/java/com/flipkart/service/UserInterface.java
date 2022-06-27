package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;

/**
 * Service Interface for User
 */
public interface UserInterface {

    /**
     * Update User Password
     *
     * @param userID
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    boolean updatePassword(String userID, String password) throws UserNotFoundException;

    /**
     * Get Role
     * @param userID
     * @return
     */
    String getRole(String userID);

    /**
     * Verify Credentials
     * @param userID
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    boolean verifyCredentials(String userID,String password) throws UserNotFoundException;
}
