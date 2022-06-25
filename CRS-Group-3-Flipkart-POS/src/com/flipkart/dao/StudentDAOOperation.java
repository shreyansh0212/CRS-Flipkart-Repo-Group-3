package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseAlreadyRegistered;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.*;
import com.mysql.cj.protocol.Resultset;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.application.CRSApplication.*;
import static com.flipkart.constants.SQLQueriesConstants.*;

public class StudentDAOOperation implements StudentDAOInterface{

    PreparedStatement preparedStatement;

    /**
     * get the username
     * @param userID
     * @return
     */
    public String getUsername(String userID) {
        String username = "";
        try {
            preparedStatement = connection.prepareStatement(SHOW_STUDENT);
            preparedStatement.setString(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                username = rs.getString("studentname");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username;
    }

    /**
     * update the preference of the courses
     * @param userID
     * @param preference
     * @throws SQLException
     * @throws CourseAlreadyRegistered
     * @throws CourseNotPresentException
     * @throws CourseLimitExceededException
     * @throws SeatNotAvailableException
     */
    @Override
    public void preferenceUpdate(String userID, List<String> preference) throws SQLException, CourseAlreadyRegistered, CourseNotPresentException, CourseLimitExceededException, SeatNotAvailableException {
        try {
            Integer count = this.getNumberOfEnrolledCourses(userID);
            if (count >= 6) {
                throw new CourseLimitExceededException(count);
            }
        }catch (CourseLimitExceededException e){
            throw e;
        }
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

                } catch (CourseAlreadyRegistered ce) {
                    throw ce;
                } catch (CourseLimitExceededException ee) {
                    throw ee;
                }
            }

        } catch (SQLException | CourseAlreadyRegistered | CourseNotPresentException e) {
            throw e;
        }
    }

    /**
     * add course to registered
     * @param userID
     */
    @Override
    public void addCourse(String courseID, String userID) throws CourseAlreadyRegistered, CourseNotPresentException, CourseLimitExceededException, SeatNotAvailableException {
        try {
            Integer count = this.getNumberOfEnrolledCourses(userID);
            if(count>=6){
                throw new CourseLimitExceededException(count);
            }
            this.checkCourseAvailability(courseID);
            PreparedStatement stmt = connection.prepareStatement(ADD_TO_REGISTER);
            stmt.setString(1,courseID);
            stmt.setString(2,userID);
            int row = stmt.executeUpdate();
            if(row!=0) {
                System.out.println("Added Course: " + courseID);
                incrementCourseStrength(courseID);

            }
        }
        catch (com.flipkart.exception.SeatNotAvailableException e) {
            throw new SeatNotAvailableException(courseID);
        } catch (SQLException ce){
            throw new CourseAlreadyRegistered(courseID,userID);
        }catch (CourseNotPresentException ce){
            throw ce;
        } catch (CourseLimitExceededException ee) {
            throw ee;
        }
    }

    /**
     * increment course strength
     * @param courseID
     */
    private void incrementCourseStrength(String courseID) {
        try {
            PreparedStatement stm = connection.prepareStatement(SQLQueriesConstants.INCREMENT_COURSE_STRENGTH);
            stm.setString(1,courseID);
            stm.executeUpdate();
            System.out.println("its happening");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * drop the course
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
                decrementCourseStrength(courseID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * decrement course strength
     * @param courseID
     */
    private void decrementCourseStrength(String courseID) {
        try {
            PreparedStatement stm = connection.prepareStatement(DECREMENT_COURSE_STRENGTH);
            stm.setString(1,courseID);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * view list of Enrolled courses
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

    /**
     * to check whether course is registered
     * @param userID
     * @return
     */

    public boolean isRegistered(String userID){
        try {
            PreparedStatement stmt = connection.prepareStatement(SHOW_STUDENT);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery();
            return rs.getBoolean("isregistered");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * to check Fee payment status
     * @param userID
     * @return
     * @throws UserNotFoundException
     */
    public boolean isFeePaymentStatus(String userID) throws UserNotFoundException {
        try {
            PreparedStatement stmt = connection.prepareStatement(GET_FEES_STATUS);
            stmt.setString(1,userID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return rs.getBoolean("feesPaymentStatus");
            }
            else {
                throw new UserNotFoundException(userID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Report Payment
     * @param userID
     * @throws UserNotFoundException
     */
    public void reportPayment(String userID) throws UserNotFoundException {
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.REPORT_PAYMENT);
            preparedStatement.setString(1,userID);
            int row = preparedStatement.executeUpdate();
            if(row > 0) {

            }
            else {
                throw new UserNotFoundException(userID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the fee payment status
     * @param userID
     * @param mode
     * @param refID
     * @param amt
     * @throws UserNotFoundException
     */
    public void setFeePaymentStatus(String userID, String mode, String refID, int amt) throws UserNotFoundException {
        try {
            String sql = ADD_PAYMENT;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,refID);
            stmt.setString(2,userID);
            stmt.setInt(3,amt);
            stmt.setString(4,mode);
            int row = stmt.executeUpdate();
            if(row > 0) {
                System.out.println("Reference ID:" + refID + "Fees Paid Successfully for StudentID:" + userID + "using " + mode);
                this.reportPayment(userID);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To register the new student
     * @param studentID
     * @param password
     * @param name
     * @param batch
     * @param address
     */
    @Override
    public void newRegistration(String studentID, String password, String name, String batch, String address) throws UserAlreadyExist, UserNotAdded {
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
            }else {
                throw new UserNotAdded(studentID);
            }

        } catch (SQLException e) {
            throw new UserAlreadyExist(studentID);
        }
    }

    /**
     * to check Approval status
     * @param userID
     * @return
     */
    @Override
    public Boolean checkApprovalStatus(String userID) throws UserNotFoundException{
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_APPROVAL_STATUS);
            preparedStatement.setString(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getBoolean("isapproved");
            }
            else{
                throw new UserNotFoundException(userID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * to view grades
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

    /**
     * to check course Availability
     * @param courseID
     * @return
     * @throws CourseNotPresentException
     * @throws com.flipkart.exception.SeatNotAvailableException
     */
    public Boolean checkCourseAvailability(String courseID) throws CourseNotPresentException, com.flipkart.exception.SeatNotAvailableException {
        try{
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.CHECK_COURSE_AVAILABILITY);
            preparedStatement.setString(1,courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            int seats=0;
            seats=seatsavaliable(courseID);
            if(!resultSet.next()) {
                throw new CourseNotPresentException(courseID);
            }
            else if(seats>10){
                throw new com.flipkart.exception.SeatNotAvailableException(courseID);
            }
            return true;
        } catch (SQLException | CourseNotPresentException e) {
            throw new CourseNotPresentException(courseID);
        }
        catch (com.flipkart.exception.SeatNotAvailableException ce){
            throw new com.flipkart.exception.SeatNotAvailableException(courseID);
        }
    }

    /**
     * to check seat availiblity
     * @param courseID
     * @return
     */

    private int seatsavaliable(String courseID) {
        try {
            preparedStatement = connection.prepareStatement(CHECK_COURSE_AVAILABILITY);
            int ans=0;
        preparedStatement.setString(1,courseID);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            ans = Integer.parseInt(rs.getString("coursestrength"));
        }

        return ans;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * get Number of Enrolled courses
     * @param studentID
     * @return
     */

    public Integer getNumberOfEnrolledCourses(String studentID){
        try{
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_NUMBER_OF_ENROLLED_COURSES);
            preparedStatement.setString(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
