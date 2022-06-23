package com.flipkart.dao;

import com.flipkart.exception.CourseNotPresentException;
import javafx.util.Pair;

import java.sql.*;
import java.util.List;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.*;

public class StudentDAOOperation implements StudentDAOInterface{
    /**
     * @param userID
     * @param preference
     */
    @Override
    public void preferenceUpdate(String userID, List<String> preference) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = updPref;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,preference.get(0));
            stmt.setString(2,preference.get(1));
            stmt.setString(3,preference.get(2));
            stmt.setString(4,preference.get(3));
            stmt.setString(5,preference.get(4));
            stmt.setString(6,preference.get(5));
            stmt.setString(7,userID);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void preferenceShow(String userID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = studshow;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println("Your course preferences are: ");
            System.out.println("1. " + rs.getString("preference1"));
            System.out.println("2. " + rs.getString("preference2"));
            System.out.println("3. " + rs.getString("preference3"));
            System.out.println("4. " + rs.getString("preference4"));
            System.out.println("5. " + rs.getString("preference5"));
            System.out.println("6. " + rs.getString("preference6"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @param userID
     */
    @Override
    public void addToRegistration(String userID, String courseID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = addtoreg;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,courseID);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean chkRegistration(String userID, String courseID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String chk = chkpresent;
            PreparedStatement stmt = conn.prepareStatement(chk);
            stmt.setString(1,userID);
            stmt.setString(2,courseID);
            ResultSet rs = stmt.executeQuery(chk);
            if(!rs.last()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @param userID
     */
    @Override
    public void dropFromRegistration(String userID, String courseID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = dropfromreg;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,courseID);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public List<String> viewEnrolledCourses(String userID) {
        List<String> enr = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = viewenrcour;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            System.out.println("Your registered courses are: ");
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                enr.add(rs.getString("courseid"));
//                System.out.println(rs.getString("courseid") + ": " + rs.getString("grade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return enr;
    }

    public boolean isRegistered(String userID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = studshow;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getBoolean("isregistered");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFeePaymentStatus(String userID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = studshow;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getBoolean("feesPaymentStatus");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = addpay;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,refID);
            stmt.setString(2,userID);
            stmt.setInt(3,amt);
            stmt.setString(4,mode);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public List<Pair<String, String>> grades(String userID) {
        List<Pair<String,String>> grd = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = viewenrcour;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            System.out.println("Your registered courses are: ");
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                grd.add(new Pair <String,String> (rs.getString("courseid"),rs.getString("grade")));
//                System.out.println(rs.getString("courseid") + ": " + rs.getString("grade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return grd;
    }

    /**
     *
     */
    @Override
    public void showCourses() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = viewcour;
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("Your registered courses are: ");
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("courseid") + ", " + rs.getString("coursename") +
                        ", " + rs.getString("department") + ", " + rs.getBoolean("isoffered")+ ", " +
                        rs.getString("professorid") + ", " + rs.getInt("coursestrength"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
