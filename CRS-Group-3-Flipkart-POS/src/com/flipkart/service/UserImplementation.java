package com.flipkart.service;

import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOOperation;

public class UserImplementation implements UserInterface{
    @Override
    public void updatePersonalDetails() {

    }
    public int login(String userID, String password){
        UserDAOInterface log = new UserDAOOperation();
        return log.login(userID,password);
    }
    /**
     *
     */
    @Override
    public void updatePassword() {

    }

    /**
     *
     */
    @Override
    public void showPersonalDetails() {

    }
}
