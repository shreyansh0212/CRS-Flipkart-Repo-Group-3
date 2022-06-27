package com.flipkart.dao;

import com.flipkart.constants.DatabaseUtil;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDAOOperation implements UserDAOInterface{

    /**
     * to verify the user credentials
     * @param userID
     * @param password
     * @return
     */

    Connection connection = DatabaseUtil.getConn();
    @Override
    public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
            preparedStatement.setString(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                throw new UserNotFoundException(userID);
            } else if (password.equals(resultSet.getString("password"))){
//                System.out.println("Successful Login!");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * to update the present password
     * @param userID
     * @return
     */
    @Override
    public boolean updatePassword(String userID, String password) throws UserNotFoundException{
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.UPDATE_PASSWORD);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,userID);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet==1){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * returns the role of the user
     * @param userID
     * @return
     */
    @Override
    public String getRole(String userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_ROLE);
            preparedStatement.setString(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println(resultSet.getString("role"));
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
