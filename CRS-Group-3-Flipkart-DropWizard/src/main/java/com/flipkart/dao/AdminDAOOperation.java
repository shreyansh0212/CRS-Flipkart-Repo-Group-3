package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.DatabaseUtil;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.flipkart.constants.SQLQueriesConstants.*;


public class AdminDAOOperation implements AdminDAOInterface{

    private PreparedStatement statement = null;
    NotificationDAOInterface notificationDAOInterface = new NotificationDAOOperation();
    String adminID = "Admin";
    String message;
    Connection connection = DatabaseUtil.getConn();
    /**
     * getting username from
     * @param userID
     *
     */

    public String getUsername(String userID){
        String username = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ADMIN);
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

    /**
     * approved the student registration for the course
     * @param studentId
     * @throws UserNotFoundException
     */
    public void approveCourseRegistration(String studentId) throws UserNotFoundException{
        try {
            statement = connection.prepareStatement(APPROVE_REGISTRATION);
            statement.setString(1,studentId);
            int row = statement.executeUpdate();
            if(row > 0) {
                message = "Student Registration for Courses Approved!";
                System.out.println(message);
                // notificationDAOInterface.sendNotification(notificationDAOInterface.getNotificationID(adminID,studentId), adminID,studentId,message);
            } else {
                throw new UserNotFoundException(studentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the list of enrolled courses
     * @param studentID
     * @return
     */
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

    /**
     * Approves the student
     * @param studentID
     */
    @Override
    public void approveStudent(String studentID) {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT);
            statement.setString(1,studentID);
            int row = statement.executeUpdate();
            if(row!=0) {
                message = "Student " + studentID + "  Approved!";
                System.out.println(message);
                // notificationDAOInterface.sendNotification(notificationDAOInterface.getNotificationID(adminID,studentID), adminID,studentID,message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * add the user to the database
     * @param user
     * @throws UserAlreadyExist
     * @throws UserNotAdded
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
     * Add Professor to the database
     * @param professor
     * @throws ProfessorNotAdded
     */
    @Override
    public void addProfessor(Professor professor) throws ProfessorNotAdded, ProfessorAlreadyExistsException, SQLException, UserNotAdded, UserAlreadyExist {
        try{
            this.addUser(professor);
        }catch(UserNotAdded e){
            throw new ProfessorNotAdded(professor.getUserID());
        }catch(UserAlreadyExist ee){
            throw new ProfessorAlreadyExistsException(professor.getUserID());
        }
        try {
            this.addUser(professor);
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
        } catch (SQLException | UserNotAdded | UserAlreadyExist e) {
            throw e;
        }
    }

    /**
     * to generate Report
     */
    @Override
    public void generateReport() {

    }

    /**
     * Add course to the database
     * @param course
     * @throws CourseAlreadyPresent
     */
    @Override
    public void addCourse(Course course) throws CourseAlreadyPresent {
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            statement.setString(1,course.getCourseID());
            statement.setString(2,course.getCourseName());
            int row = statement.executeUpdate();
            if(row!=0) {
                System.out.println("Course Added!");
            }
        } catch (SQLException e) {
            throw new CourseAlreadyPresent(course.getCourseID());
        }
    }

    /**
     * drop course from database
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
     * show list of all the courses
     */
    @Override
    public List<Course> showCourses() {
        List<Course> coursesAvailable = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.SHOW_COURSES);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                coursesAvailable.add(new Course(resultSet.getString("courseid"),resultSet.getString("coursename"),resultSet.getBoolean("isoffered"),resultSet.getString("professorid"),resultSet.getInt("coursestrength")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coursesAvailable;
    }

    /**
     * view pending Requests
     *
     * @return
     */
    @Override
    public List<Student> viewPendingRequests(){
        List<Student> pendingRequests = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT_LIST);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
//                System.out.println("Pending: StudentID: " + resultSet.getString(1) + "   Student Name: " + resultSet.getString(2));
                Student student = new Student();
                student.setUserID(resultSet.getString("studentid"));
                student.setBatch(resultSet.getString("studentbatch"));
                student.setAddress(resultSet.getString("address"));
                student.setName(resultSet.getString("studentname"));
                student.setRole("student");
                pendingRequests.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pendingRequests;
    }

    /**
     * view list of not registered student
     *
     * @return
     */
    public List<Student> viewPendingRegistrations(){
        List<Student> pendingRequests = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQLQueriesConstants.NON_REGISTERED_STUDENT_LIST);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                System.out.println(" StudentID: " + resultSet.getString(1) + "   Student Name: " + resultSet.getString(2));
                Student student = new Student();
                student.setUserID(resultSet.getString("studentid"));
                student.setBatch(resultSet.getString("studentbatch"));
                student.setAddress(resultSet.getString("address"));
                student.setName(resultSet.getString("studentname"));
                student.setRole("student");
                pendingRequests.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pendingRequests;
    }
    @Override
    public void assignCourse(String courseCode, String professorId) {;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ASSIGN_COURSE);
            preparedStatement.setString(1,professorId);
            preparedStatement.setString(2,courseCode);
//            System.out.println(preparedStatement);
            int resultRows = preparedStatement.executeUpdate();
            if(resultRows > 0) {
                System.out.println("Course assigned successfully");
            }
            else{
                System.out.println("Couldn't assign course");
            }
        }catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }

}

