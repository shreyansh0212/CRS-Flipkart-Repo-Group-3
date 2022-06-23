package com.flipkart.service;

import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOOperation;

public class UserImplementation implements UserInterface{
    UserDAOInterface userDAOInterface = new UserDAOOperation();

    /**
     * @param userID
     * @param password
     */
    @Override
    public void updatePassword(String userID, String password) {
        userDAOInterface.updatePassword(userID,password);
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public String getRole(String userID) {
        return userDAOInterface.getRole(userID);
    }

    /**
     * @param userID
     * @param password
     * @return
     */
    @Override
    public boolean verifyCredentials(String userID, String password) {
        return userDAOInterface.verifyCredentials(userID,password);
    }
}
