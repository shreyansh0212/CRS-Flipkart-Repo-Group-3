package com.flipkart.dao;

import com.flipkart.exception.LoginFailedException;

import java.sql.*;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.logincheck;

public class UserDAOOperation implements UserDAOInterface{
    /**
     *
     */

    public int login(String userID, String password) throws LoginFailedException {
        int role = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = logincheck;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.last()){
                rs.first();
                String roleString = rs.getString("role");
                if(roleString == "admin")role = 1;
                else if (roleString == "student")role = 2;
                else if(roleString == "professor")role = 3;
                else role = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return role;
    }
    @Override
    public void updatePersonalDetails() {

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
