package com.flipkart.service;

import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOOperation;
import com.flipkart.exception.UserNotFoundException;

public class UserImplementation implements UserInterface{
    UserDAOInterface userDAOInterface = new UserDAOOperation();

    /**
     * Update password
     * @param userID
     * @param password
     * @throws UserNotFoundException
     */
    @Override
    public void updatePassword(String userID, String password) throws UserNotFoundException{
        userDAOInterface.updatePassword(userID,password);
    }

    /**
     * Get Role
     * @param userID
     * @return
     */
    @Override
    public String getRole(String userID) {
        return userDAOInterface.getRole(userID);
    }

    /**
     * Verify Credentials
     * @param userID
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
        return userDAOInterface.verifyCredentials(userID,password);
    }
}
