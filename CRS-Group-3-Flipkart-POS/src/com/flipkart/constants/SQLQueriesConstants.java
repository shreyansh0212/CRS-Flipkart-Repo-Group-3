package com.flipkart.constants;

public class SQLQueriesConstants {
    public static final String logincheck= "SELECT * FROM user WHERE userid = ? and password = ?";
    public static final String registerStudent = "INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String registerUser = "INSERT INTO user VALUES (?,?,?)";
    public static final String updPref = "UPDATE student SET preference1 = ?, preference2 = ?, preference3 = ?, " +
            "preference4 = ?, preference5 = ?, preference6 = ? WHERE studentid = ?";
    public static final String studshow = "SELECT * FROM student where studentid = ?";
    public static final String addtoreg = "INSERT INTO registeredcourses VALUES (?,?,?)";
    public static final String dropfromreg = "DELETE FROM registeredcourses WHERE userid = ? and courseid = ?";
    public static final String viewcour = "SELECT * FROM registeredcourses";
    public static final String viewenrcour = "SELECT * FROM registeredcourses WHERE userid = ?";
    public static final String addpay = "INSERT INTO paymentmode VALUES (?,?,?,?,now())";
    public static final String viewcourbyprof = "SELECT * FROM course WHERE professorid = ?";
    public static final String viewstudbycourse = "SELECT * FROM registeredcourses WHERE courseid = ?";
    public static final String addgrade = "UPDATE registeredcourses SET grade = ?";

}
