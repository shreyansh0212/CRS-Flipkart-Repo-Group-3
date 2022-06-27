package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.constants.DatabaseUtil;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseAlreadyRegistered;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLQueriesConstants.*;

public class StudentDAOOperation implements StudentDAOInterface{

    PreparedStatement preparedStatement;
    Connection connection = DatabaseUtil.getConn();

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

                } catch (CourseAlreadyRegistered | CourseLimitExceededException ce) {
                    throw ce;
                }
            }

        } catch (SQLException e) {
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
            this.checkCourseAvailability(userID,courseID);
            PreparedStatement stmt = connection.prepareStatement(ADD_TO_REGISTER);
            stmt.setString(1,courseID);
            stmt.setString(2,userID);
            String professorID = "P001";
            stmt.setString(3,professorID);

            System.out.println(stmt);

            int row = stmt.executeUpdate();
            if(row!=0) {
                incrementCourseStrength(courseID);
            }
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
    public void dropCourse(String courseID, String userID) {
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
     *
     * @param userID
     * @return
     */
    @Override
    public List<EnrolledStudent> viewEnrolledCourses(String userID) {
        List<EnrolledStudent> enrolledCourses = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(VIEW_ENROLLED_COURSES);
            preparedStatement.setString(1,userID);
            System.out.println("Your registered courses are: ");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                EnrolledStudent course = new EnrolledStudent();
                course.setCourseID(rs.getString("courseid"));
                course.setCourseName(rs.getString("coursename"));
                course.setStudentID(rs.getString("userID"));
                course.setProfessorID(rs.getString("professorid"));

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

    public boolean getRegistrationStatus(String userID){
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
    public boolean feePaymentStatus(String userID) throws UserNotFoundException {
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
    @Override
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
        try {
            AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
            adminDAOInterface.addUser(new User(studentID, name, "student", password));
        }catch(UserAlreadyExist e){
            throw e;
        }
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
    public Boolean getApprovalStatus(String userID) throws UserNotFoundException{
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
    public List<StudentGrade> viewGrades(String userID) {
        List<StudentGrade> grades = new ArrayList<>();
        try {
            String sql = VIEW_ENROLLED_COURSES;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,userID);
            System.out.println("------ Grade Card -------");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                grades.add(new StudentGrade (rs.getString("courseid"),rs.getString("grade")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grades;
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

    /**Method to checkCourseAvailability , if course is already registered by student then return 1 ,
     * if number of registered courses by student is greater than 5 then return 0, else return 2
     * @param studentId
     * @param courseCode
     * @return
     * @throws SQLException
     */

    @Override
    public int checkCourseAvailability(String studentId, String courseCode) throws SQLException, CourseNotPresentException{
        int count = 0;
        try {

            preparedStatement = connection.prepareStatement(SQLQueriesConstants.CHECK_COURSE_AVAILABILITY);
            preparedStatement.setString(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if(courseCode.equals(rs.getString("courseCode")))
                    return 1;
                count++;
            }

            if(count >= 6)
                return 0;


        }
        catch (SQLException se)
        {

            System.out.println(se.getMessage());

        }
        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return 2;
    }

    /**
     * Check if seat is available for that particular course
     * @param courseCode
     * @return
     * @throws SQLException
     */
    @Override
    public boolean seatAvailable(String courseCode) throws SQLException {

        Connection conn = DatabaseUtil.getConn();
        PreparedStatement stmt;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.GET_SEATS);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("seats"));
                return (rs.getInt("seats") > 0);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }

        return true;
    }

    @Override
    public Payment payFees(String userID, float amount, String mode) throws UserNotFoundException, FeesAlreadyPaid, StudentNotRegistered, SQLException {
//        if(this.feePaymentStatus(userID)) {
//            throw new FeesAlreadyPaid(userID);
//        }
//        if(!this.getRegistrationStatus(userID)) {
//            throw new StudentNotRegistered(userID);
//        }
        Payment payment = new Payment();
        preparedStatement = connection.prepareStatement(ADD_PAYMENT);
        String referenceID = userID+LocalDateTime.now();
        preparedStatement.setString(1,referenceID);
        preparedStatement.setString(2,userID);
        preparedStatement.setFloat(3,amount);
        preparedStatement.setString(4,mode);
        int row = preparedStatement.executeUpdate();
        if(row > 0) {
            System.out.println("Payment Done");
            payment.setAmount(amount);
            payment.setMode(mode);
            payment.setReferenceID(referenceID);
            payment.setUserID(userID);
            this.reportPayment(userID);
        }
        return payment;
    }


}
