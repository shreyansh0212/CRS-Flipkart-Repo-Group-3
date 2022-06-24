package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static com.flipkart.application.CRSApplication.connection;
import static com.flipkart.constants.SQLQueriesConstants.adminshow;
import static com.flipkart.constants.SQLQueriesConstants.profshow;


public class AdminDAOOperation implements AdminDAOInterface{

    private PreparedStatement statement = null;

    /**
     *
     */

    public String getUsername(String userID){
        String username = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(adminshow);
            preparedStatement.setString(1,userID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                username = rs.getString("adminname");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username;
    }
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
    public void addUser(User user) throws UserAlreadyExist {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER);
            statement.setString(1,user.getUserID());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getRole());
            int row = statement.executeUpdate();
            if(row==0){
                throw new UserAlreadyExist(user.getUserID());
            }
        } catch (SQLException e) {
            throw new UserAlreadyExist(user.getUserID());
        }
    }

    /**
     * @param professor
     */
    @Override
    public void addProfessor(Professor professor) throws UserAlreadyExist{
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
    public void addCourse(Course course) throws CourseAlreadyPresent {
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
            throw new CourseAlreadyPresent(course.getCourseID());
        }
    }

    /**
     * @param courseID
     */
    @Override
    public void dropCourse(String courseID) throws CourseNotPresentException {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.DELETE_COURSE);
            statement.setString(1,courseID);
            int row = statement.executeUpdate();
            if(row!=0) {
                System.out.println("Course Deleted!");
            }
            else{
                throw new CourseNotPresentException(courseID);
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
