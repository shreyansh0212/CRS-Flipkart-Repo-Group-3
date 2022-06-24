package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstants;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.*;

public class StudentDAOOperation implements StudentDAOInterface{

    PreparedStatement preparedStatement;

    public String getUsername(String userID){
        String username = "";
        try {
            preparedStatement = connection.prepareStatement(studshow);
            preparedStatement.setString(1,userID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                username = rs.getString("studentname");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username;
    }
    /**
     * @param userID
     * @param preference
     */
    @Override
    public void preferenceUpdate(String userID, List<String> preference) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PREFERENCE);
            for (int i=0;i<6;i++) {
                preparedStatement.setString(i+1,preference.get(i));
            }
            preparedStatement.setString(7,userID);
            int row = preparedStatement.executeUpdate();
            if(row!=0) {
                System.out.println("Your Courses are Registered!");
                try {
                    for(int i=0;i<4;i++) {
                        this.addCourse(preference.get(i),userID);
                    }

                } catch (NullPointerException e) {

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userID
     */
    @Override
    public void addCourse(String courseID, String userID) {
        try {
            PreparedStatement stmt = connection.prepareStatement(ADD_TO_REGISTER);
            stmt.setString(1,courseID);
            stmt.setString(2,userID);
            int row = stmt.executeUpdate();
            if(row!=0) {
                System.out.println("Added Course: " + courseID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userID
     */
    @Override
    public void dropCourse(String userID, String courseID) {
        try {
            preparedStatement = connection.prepareStatement(DROP_FROM_REGISTER);

            preparedStatement.setString(1,courseID);
            preparedStatement.setString(2,userID);

            int status = preparedStatement.executeUpdate();
            if(status!=0) {
                System.out.println("Course: " + courseID + " Dropped!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public List<String> viewEnrolledCourses(String userID) {
        List<String> enrolledCourses = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(VIEW_ENROLLED_COURSES);
            preparedStatement.setString(1,userID);
            System.out.println("Your registered courses are: ");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                enrolledCourses.add(rs.getString("courseid"));
//                System.out.println(rs.getString("courseid") + ": " + rs.getString("grade"));
                System.out.println("Student ID: " + userID + " -> Course ID: " + rs.getString("courseid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enrolledCourses;
    }

    public boolean isRegistered(String userID){
        try {
            String sql = studshow;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery();
            return rs.getBoolean("isregistered");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFeePaymentStatus(String userID){
        try {
            String sql = studshow;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getBoolean("feesPaymentStatus");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFeePaymentStatus(String userID, String mode, String refID, int amt){
        try {
            String sql = ADD_PAYMENT;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,refID);
            stmt.setString(2,userID);
            stmt.setInt(3,amt);
            stmt.setString(4,mode);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param studentID
     * @param password
     * @param name
     * @param batch
     * @param address
     */
    @Override
    public void newRegistration(String studentID, String password, String name, String batch, String address) {
        AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
        adminDAOInterface.addUser(new User(studentID,name,"student",password));
        try {
            preparedStatement = connection.prepareStatement(NEW_REGISTER_STUDENT);
            preparedStatement.setString(1,studentID);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,batch);
            preparedStatement.setString(4,address);
            int row = preparedStatement.executeUpdate();
            if(row > 0) {
                System.out.println("New Registration Done!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public List<Pair<String, String>> viewGrades(String userID) {
        List<Pair<String,String>> grd = new ArrayList<>();
        try {
            String sql = VIEW_ENROLLED_COURSES;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,userID);
            System.out.println("------ Grade Card -------");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                grd.add(new Pair <String,String> (rs.getString("courseid"),rs.getString("grade")));
//                System.out.println(rs.getString("courseid") + ": " + rs.getString("grade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grd;
    }
}
