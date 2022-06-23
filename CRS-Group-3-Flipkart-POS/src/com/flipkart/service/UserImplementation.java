package com.flipkart.service;

import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOOperation;
import com.flipkart.exception.LoginFailedException;

public class UserImplementation implements UserInterface{
    @Override
    public void updatePersonalDetails() {

    }
    public int login(String userID, String password){
        UserDAOInterface log = new UserDAOOperation();
        int role = 0;
        try{
            role = log.login(userID,password);
        } catch (LoginFailedException e){
            System.out.println(e.getMsg());
        }
        return role;
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
