package com.flipkart.dao;

import com.flipkart.application.CRSApplication;
import com.flipkart.constants.SQLQueriesConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.application.CRSApplication.connection;

public class UserDAOOperation implements UserDAOInterface{

    /**
     * @param userID
     * @param password
     * @return
     */
    @Override
    public boolean verifyCredentials(String userID, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
            preparedStatement.setString(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                // System.out.println("User Not Found");
            } else if (password.equals(resultSet.getString("password"))){
                System.out.println("Successful Login!");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public boolean updatePassword(String userID,String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.UPDATE_PASSWORD);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,userID);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet==1){
                System.out.println("Password Updated Successfully!");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
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
