package com.flipkart.constants;

public class SQLQueriesConstants {
    public static final String NEW_REGISTER_STUDENT = "INSERT INTO student (studentid,studentname,studentbatch,address) VALUES (?,?,?,?)";
    public static final String REGISTER_USER = "INSERT INTO user VALUES (?,?,?)";
    public static final String UPDATE_PREFERENCE = "UPDATE student SET preference1 = ?, preference2 = ?, preference3 = ?, " +
            "preference4 = ?, preference5 = ?, preference6 = ? WHERE studentid = ?";
    public static final String studshow = "SELECT * FROM student where studentid = ?";
    public static final String ADD_TO_REGISTER = "INSERT INTO registeredcourses (courseid,studentid) VALUES (?,?)";
//    public static final String REGISTER_COURSES = "INSERT INTO registeredcourses (courseid,studentid) VALUES ((?,?),(?,?),(?,?),(?,?))";
    public static final String DROP_FROM_REGISTER = "delete from registeredcourses where (courseid = ? ) and (studentid = ?)";
    public static final String VIEW_ENROLLED_COURSES = "SELECT * FROM registeredcourses WHERE studentid = ?";
    public static final String ADD_PAYMENT = "INSERT INTO paymentmode VALUES (?,?,?,?,now())";



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
    public static final String CHECK_COURSE_AVAILABILITY = "select * from course where courseid = ?";

    // Professor Queries
    public static final String ADD_GRADE="update registeredcourses set grade=? where courseid=? and studentid=?";
    public static final String GET_COURSES="select * from course where professorid = ?";
    public static final String VIEW_ENROLLED_STUDENTS="select course.courseid,course.coursename,registeredcourses.studentid from course inner join registeredcourses on course.courseid = registeredcourses.courseid where course.professorid = ? order by course.courseid";


    public static final String GET_APPROVAL_STATUS = "select isapproved from student where studentid=?";
}
