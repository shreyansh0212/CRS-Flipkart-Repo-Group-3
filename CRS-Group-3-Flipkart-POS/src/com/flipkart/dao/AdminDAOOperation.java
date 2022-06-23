package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static com.flipkart.application.CRSApplication.connection;


public class AdminDAOOperation implements AdminDAOInterface{

    private PreparedStatement statement = null;

    /**
     *
     */


    @Override
    public void approveStudent(String studentID) {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT);
            statement.setString(1,studentID);
            int row = statement.executeUpdate();
            if(row!=0) {
                System.out.println("Student Approved!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param user
     */
    @Override
    public void addUser(User user) {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER);
            statement.setString(1,user.getUserID());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getRole());
            int row = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param professor
     */
    @Override
    public void addProfessor(Professor professor) {
        this.addUser(professor);
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR);
            statement.setString(1,professor.getUserID());
            statement.setString(2,professor.getName());
            statement.setString(3,professor.getDepartment());
            int row = statement.executeUpdate();
            if(row!=0) {
                System.out.println("Professor Added!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    @Override
    public void generateReport() {

    }

    /**
     * @param course
     */
    @Override
    public void addCourse(Course course) {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            statement.setString(1,course.getCourseID());
            statement.setString(2,course.getCourseName());
            statement.setString(3,course.getProfessorID());
            int row = statement.executeUpdate();
            if(row!=0)
            {
                System.out.println("Course Added!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param courseID
     */
    @Override
    public void dropCourse(String courseID) {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.DELETE_COURSE);
            statement.setString(1,courseID);
            int row = statement.executeUpdate();
            if(row!=0) {
                System.out.println("Course Deleted!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    @Override
    public void showCourses() {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.SHOW_COURSES);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                System.out.println(" CourseID: " + resultSet.getString(1) + ", Course Name: " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public List<Student> approvePendingRequests() {

        return null;
    }
}
