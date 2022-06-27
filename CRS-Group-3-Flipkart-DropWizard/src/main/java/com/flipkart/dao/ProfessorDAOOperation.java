package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constants.DatabaseUtil;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.GradeNotAddedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLQueriesConstants.SHOW_PROFESSOR;

public class ProfessorDAOOperation implements ProfessorDAOInterface{

    /**
     * get username
     * @param userID
     * @return
     */

    Connection connection = DatabaseUtil.getConn();
    @Override
    public String getUsername(String userID){
        String username = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_PROFESSOR);
            preparedStatement.setString(1,userID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                username = rs.getString("professorname");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username;
    }

    //PreparedStatement preparedStatement;
    /**
     * add grade for the given course and student
     * @param courseID
     * @param studentID
     * @param grade
     * @return
     */
    @Override
    public boolean addGrade(String grade, String courseID, String studentID) throws GradeNotAddedException {
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
            throw new GradeNotAddedException(studentID,courseID);
        }
        return false;
    }



    /**
     * view list of the enrolled student along with the course
     *
     * @param professorID
     * @return
     */
    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String professorID) {
        List<EnrolledStudent> enrolledStudent = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.VIEW_ENROLLED_STUDENTS);
            preparedStatement.setString(1,professorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                EnrolledStudent student = new EnrolledStudent();
                student.setCourseID(resultSet.getString("courseid"));
                student.setStudentID(resultSet.getString("studentid"));
                student.setCourseName(resultSet.getString("coursename"));

                enrolledStudent.add(student);
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
    public List<Course> getCourses(String professorID) {
        List<Course> offeredCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);
            preparedStatement.setString(1,professorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Course course = new Course();
                course.setCourseName(resultSet.getString("coursename"));
                course.setCourseID(resultSet.getString("courseid"));
                course.setCourseStrength(resultSet.getInt("coursestrength"));
                course.setOffered(resultSet.getBoolean("isoffered"));
                course.setProfessorID(resultSet.getString("professorid"));
                offeredCourses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return offeredCourses;
    }
}
