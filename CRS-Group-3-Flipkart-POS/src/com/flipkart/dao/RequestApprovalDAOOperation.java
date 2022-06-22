package com.flipkart.dao;

import java.sql.*;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.*;

public class RequestApprovalDAOOperation {

    public static void request(String userID, String password, String name, String batch, String address) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = registerUser;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,password);
            stmt.setString(3,"student");
            stmt.executeUpdate(sql);
            sql = registerStudent;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,name);
            stmt.setString(3,batch);
            stmt.setString(4,address);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
