package com.flipkart.dao;

import com.flipkart.constants.SQLQueriesConstants;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.application.CRSApplication.connection;

public class ProfessorDAOOperation implements ProfessorDAOInterface{
    //PreparedStatement preparedStatement;
    /**
     * @param courseID
     * @param studentID
     * @param grade
     * @return
     */
    @Override
    public boolean addGrade(String grade, String courseID, String studentID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);
            preparedStatement.setString(1,grade);
            preparedStatement.setString(2,courseID);
            preparedStatement.setString(3,studentID);
            int row = preparedStatement.executeUpdate();
            if(row!=0) {
                System.out.println("Grade Added Successfully!");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * @param professorID
     * @return
     */
    @Override
    public List<Pair<String, String>> viewEnrolledStudents(String professorID) {
        List<Pair<String,String>> enrolledStudent = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.VIEW_ENROLLED_STUDENTS);
            preparedStatement.setString(1,professorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                enrolledStudent.add(new Pair<>(resultSet.getString("courseid"),resultSet.getString("studentid")));
                System.out.println("CourseID: " + resultSet.getString("courseid") + " - StudentID: " + resultSet.getString("studentid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enrolledStudent;
    }

    /**
     * @param professorID
     * @return
     */
    @Override
    public List<String> getCourses(String professorID) {
        List<String> offeredCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);
            preparedStatement.setString(1,professorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String courseID = resultSet.getString("courseid");
                offeredCourses.add(courseID);
                System.out.println("CourseID: " + courseID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return offeredCourses;
    }
}
