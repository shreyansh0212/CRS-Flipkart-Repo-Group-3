package com.flipkart.constants;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Used to Connect to Database
 */
public class DatabaseUtil {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRSDatabase";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Blue_176454";
    static Connection conn = null;

    /**
     * Get  Connection  Sql Database Object
     * Throws Exception if  Not connected to SQL Database
     * @return
     */
    public static Connection getConn() {
        try {
            Class.forName(JDBC_DRIVER);
            // Step 4 make/open  a connection

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
