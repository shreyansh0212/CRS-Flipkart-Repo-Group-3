package com.flipkart.constants;

/**
 * SQL Queries Used
 */
public class SQLQueriesConstants {
    public static final String NEW_REGISTER_STUDENT = "INSERT INTO student (studentid,studentname,studentbatch,address) VALUES (?,?,?,?)";
    public static final String UPDATE_PREFERENCE = "UPDATE student SET preference1 = ?, preference2 = ?, preference3 = ?, " +
            "preference4 = ?, preference5 = ?, preference6 = ? WHERE studentid = ?";
    public static final String SHOW_STUDENT = "SELECT * FROM student where studentid = ?";
    public static final String SHOW_ADMIN = "SELECT * FROM admin WHERE adminid = ?";
    public static final String SHOW_PROFESSOR = "SELECT * FROM professor WHERE professorid = ?";
    public static final String ADD_TO_REGISTER = "INSERT INTO registeredcourses (courseid,studentid,professorid) VALUES (?,?,?)";
    public static final String INCREMENT_COURSE_STRENGTH ="UPDATE course SET coursestrength=coursestrength+1 WHERE courseid =? ";
    public static final String DECREMENT_COURSE_STRENGTH ="UPDATE course SET coursestrength=coursestrength-1 WHERE courseid =? ";
    public static final String DROP_FROM_REGISTER = "delete from registeredcourses where (courseid = ? ) and (studentid = ?)";
    public static final String VIEW_ENROLLED_COURSES = "SELECT * FROM registeredcourses WHERE studentid = ?";
    public static final String ADD_PAYMENT = "INSERT INTO paymentmode(referenceid,studentid,amount,mode) VALUES (?,?,?,?)";
    public static final String VERIFY_CREDENTIALS = "select password from user where userid = ?";
    public static final String UPDATE_PASSWORD = "update user set password=? where userid = ?";
    public static final String GET_ROLE = "select role from user where userid = ?";
    public static final String ADD_NOTIFICATION = "INSERT INTO notification VALUES (?,?,?,?)";
    public static final String APPROVE_REGISTRATION = "UPDATE student SET isregistered = 1 WHERE studentid = ?";
    public static final String DELETE_COURSE = "delete from course where courseid = ?";
    public static final String ADD_COURSE = "insert into course(courseid, coursename) values (?, ?)";
    public static final String ADD_PROFESSOR = "insert into professor(professorid, professorname, department) values (?, ?, ?)";
    public static final String ADD_USER = "insert into user(userid, password, role) values (?, ?, ?)";
    public static final String SHOW_COURSES = "select * from course";
    public static final String APPROVE_STUDENT = "update student set isapproved = 1 where studentid = ?";
    public static final String APPROVE_STUDENT_LIST = "select * from student where isapproved = 0 ";
    public static final String NON_REGISTERED_STUDENT_LIST = "select * from student where isregistered = 0 ";
    public static final String CHECK_COURSE_AVAILABILITY = "select * from course where courseid = ?";
    public static final String GET_SEATS = "SELECT coursestrength FROM course WHERE courseid = ?";
    public static final String ADD_GRADE="update registeredcourses set grade=? where courseid=? and studentid=?";
    public static final String GET_COURSES="select * from course where professorid = ?";
    public static final String VIEW_ENROLLED_STUDENTS="select course.courseid,course.coursename,registeredcourses.studentid from course inner join registeredcourses on course.courseid = registeredcourses.courseid where course.professorid = ? order by course.courseid";
    public static final String GET_APPROVAL_STATUS = "select isapproved from student where studentid=?";
    public static final String GET_FEES_STATUS = "select feesPaymentStatus from student where studentid = ?";
    public static final String GET_NUMBER_OF_ENROLLED_COURSES = "select count(*) from registeredcourses where studentid=?";
    public static final String REPORT_PAYMENT = "update student set feesPaymentStatus = 1 where studentid = ?";
    public static final String ASSIGN_COURSE = "UPDATE course SET professorid = ? WHERE courseid = ?";
}
