package com.flipkart.dao;

import java.sql.*;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.*;

public class RequestApprovalDAOOperation {

    public static void request(String userID, String password, String name, String batch, String address) {
        try {
            String sql = registerUser;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,password);
            stmt.setString(3,"student");
            stmt.executeUpdate(sql);
            sql = registerStudent;
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,name);
            stmt.setString(3,batch);
            stmt.setString(4,address);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
