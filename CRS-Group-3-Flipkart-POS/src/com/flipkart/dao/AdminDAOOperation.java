package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.flipkart.application.CRSApplication.connection;
import static com.flipkart.constants.SQLQueriesConstants.*;


public class AdminDAOOperation implements AdminDAOInterface{

    private PreparedStatement statement = null;
    NotificationDAOInterface notificationDAOInterface = new NotificationDAOOperation();
    String adminID = "Admin";
    String message;
    /**
     * getting username from
     *@aram userid
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

    public void approveCourseRegistration(String studentId) throws UserNotFoundException{
        try {
            statement = connection.prepareStatement(APPROVE_REGISTRATION);
            statement.setString(1,studentId);
            int row = statement.executeUpdate();
            if(row > 0) {
                message = "Student Registration for Courses Approved!";
                System.out.println(message);
                notificationDAOInterface.sendNotification(notificationDAOInterface.getNotificationID(adminID,studentId), adminID,studentId,message);
            } else {
                throw new UserNotFoundException(studentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getCourses(String studentID){
        List<String> enrolledCourses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(VIEW_ENROLLED_COURSES);
            preparedStatement.setString(1,studentID);
            System.out.println("Student's registered courses are: ");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                enrolledCourses.add(rs.getString("courseid"));
//                System.out.println(rs.getString("courseid") + ": " + rs.getString("grade"));
//                System.out.println("Student ID: " + studentID + " -> Course ID: " + rs.getString("courseid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enrolledCourses;
    }
    @Override
    public void approveStudent(String studentID) {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT);
            statement.setString(1,studentID);
            int row = statement.executeUpdate();
            if(row!=0) {
                message = "Student " + studentID + "  Approved!";
                System.out.println(message);
                notificationDAOInterface.sendNotification(notificationDAOInterface.getNotificationID(adminID,studentID), adminID,studentID,message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param user
     */
    @Override
    public void addUser(User user) throws UserAlreadyExist, UserNotAdded {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER);
            statement.setString(1,user.getUserID());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getRole());
            int row = statement.executeUpdate();
            if(row==0){
                throw new UserNotAdded(user.getUserID());
            }
        } catch (SQLException e) {
            throw new UserAlreadyExist(user.getUserID());
        }
    }

    /**
     * @param professor
     */
    @Override
    public void addProfessor(Professor professor) throws UserAlreadyExist, UserNotAdded, ProfessorNotAdded, ProfessorAlreadyExistsException {
        try{
            this.addUser(professor);
        }catch(UserNotAdded e){
            throw new ProfessorNotAdded(professor.getUserID());
        }catch(UserAlreadyExist ee){
            throw new ProfessorAlreadyExistsException(professor.getUserID());
        }
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR);
            statement.setString(1,professor.getUserID());
            statement.setString(2,professor.getName());
            statement.setString(3,professor.getDepartment());
            int row = statement.executeUpdate();
            if(row!=0) {
                System.out.println("Professor Added!");
            }else{
                throw new ProfessorNotAdded(professor.getUserID());
            }
        } catch (SQLException e) {
            throw new ProfessorAlreadyExistsException(professor.getUserID());
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
                System.out.println(" CourseID: " + resultSet.getString("courseid") + ", Course Name: " + resultSet.getString("coursename")+", Course Strength: " + resultSet.getString("coursestrength"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewPendingRequests(){
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT_LIST);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                System.out.println(" StudentID: " + resultSet.getString(1) + "   Student Name: " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public void viewNotRegisteredStudents(){
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.NON_REGISTERED_STUDENT_LIST);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                System.out.println(" StudentID: " + resultSet.getString(1) + "   Student Name: " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }

