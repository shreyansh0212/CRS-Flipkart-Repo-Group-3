package com.flipkart.constants;

public class SQLQueriesConstants {

    // User Queries
    public static final String VERIFY_CREDENTIALS = "select password from user where userid = ?";
    public static final String UPDATE_PASSWORD = "update user set password=? where userid = ?";
    public static final String GET_ROLE = "select role from user where userid = ?";

    // Admin Queries
    public static final String DELETE_COURSE = "delete from course where courseid = ?";
    public static final String ADD_COURSE = "insert into course(courseid, coursename, professorid) values (?, ?, ?)";
    public static final String ADD_PROFESSOR = "insert into professor(professorid, professorname, department) values (?, ?, ?)";
    public static final String ADD_USER = "insert into user(userid, password, role) values (?, ?, ?)";
    public static final String SHOW_COURSES = "select * from course";
    public static final String APPROVE_STUDENT = "update student set isapproved = 1 where studentid = ?";

    // Professor Queries
    public static final String ADD_GRADE="update registeredcourses set grade=? where courseid=? and studentid=?";
    public static final String GET_COURSES="select * from course where professorid=?";
    public static final String VIEW_ENROLLED_STUDENTS="select course.courseid,course.coursename,registeredcourses.studentid from course inner join registeredcourses on course.courseid = registeredcourse.courseid where course.professorid = ? order by course.courseid";







}
