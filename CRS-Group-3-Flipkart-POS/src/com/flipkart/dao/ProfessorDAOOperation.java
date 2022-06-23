package com.flipkart.dao;

import com.flipkart.bean.Student;
import javafx.util.Pair;

import java.sql.*;
import java.util.List;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.*;

public class ProfessorDAOOperation implements ProfessorDAOInterface{
    /**
     * @param courseID
     */
    @Override
    public void addGrade(String courseID, String userID, String grade) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = addgrade;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,courseID);
            stmt.setString(2,userID);
            stmt.setString(3,grade);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param courseID
     * @return
     */
    @Override
    public List<String> viewEnrolledStudents(String courseID) {
        List<String> students = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = viewstudbycourse;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,courseID);
            System.out.println("Students enrolled in this course are: ");
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                students.add(rs.getString("studentid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public List<String> getCourses(String userID) {
        List<String> courses = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = viewcourbyprof;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            System.out.println("Your assigned courses are: ");
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                courses.add(rs.getString("courseid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }
}
