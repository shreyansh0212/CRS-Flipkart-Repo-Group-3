package com.flipkart.dao;

import java.sql.*;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.logincheck;

public class LoginDAOOperation {

    public static int login(String userID, String password) {
        int role = 0;
        try {
            String sql = logincheck;
            PreparedStatement stmt = connection.prepareStatement(sql);
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
        }

        return role;
    }
    public static boolean isapproved(String userID){
        return false;
    }
}
