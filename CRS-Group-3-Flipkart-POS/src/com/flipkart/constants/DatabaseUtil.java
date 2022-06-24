package com.flipkart.constants;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRSDatabase";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Blue_176455";
    static Connection conn = null;

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Step 4 make/open  a connection

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
