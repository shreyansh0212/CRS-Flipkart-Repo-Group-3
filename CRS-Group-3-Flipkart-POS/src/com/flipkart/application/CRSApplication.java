package com.flipkart.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.flipkart.application.AdminCRSMenu;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import javafx.util.Pair;

public class CRSApplication {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRSDatabase";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Blue_176454";


    public static void main(String[] args) {


        // Step 2
        // Declare the Connection or prepared-statement variable here
        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Register Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");

            // Step 4 make/open  a connection

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql="insert into user values(?,?,?)";
            //String sql = "UPDATE Employees set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);
            stmt = conn.prepareStatement(sql);

            // Hard coded data

            String id="A001";
            String password="pass";
            String role="admin";

            //Bind values into the parameters.
            stmt.setString(1, id);
            stmt.setString(2,password);
            stmt.setString(3,role);
            stmt.executeUpdate();


            // Let us update age of the record with ID = 102;
			      int rows = stmt.executeUpdate();
			      System.out.println("Rows impacted : " + rows );


            // Let us select all the records and display them.
//            sql = "SELECT id, name ,address, location FROM employee";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int eid  = rs.getInt("id");
//                String name1 = rs.getString("name");
//                String address1 = rs.getString("address");
//                String location1 = rs.getString("location");
//
//                //Display values
//                System.out.print("ID: " + eid);
//                System.out.print(", Age: " + name1);
//                System.out.print(", First: " + address1);
//                System.out.println(", Last: " + location1);
//            }

            //STEP 6: Clean-up environment
            // rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


        Integer input;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("----------- Welcome to Course Registration System ------------");
            System.out.println("1. Login");
            System.out.println("2. New Student Registration");
            System.out.println("3. Update Password");
            System.out.println("4. Exit");
            System.out.println("Enter Your Choice: ");
            input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter your UserID: ");
                    String userID = in.next();
                    System.out.println("Enter your Password: ");
                    String password1 = in.next();

                    // verifying credentials
                    int roleInput=0;
//                    if(studentDB.get(userID)!=null && studentDB.get(userID).getPassword().equals(password)) roleInput=2;
//                    if(adminDB.get(userID)!=null && adminDB.get(userID).getPassword().equals(password)) roleInput=1;
//                    if(professorDB.get(userID)!=null && professorDB.get(userID).getPassword().equals(password)) roleInput=3;

                    switch (roleInput) {
                        case 1:
                            //AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                            //adminCRSMenu.showMenu();
                            break;

                        case 2:
                            //StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                            //studentCRSMenu.showMenu(userID);
                            break;

                        case 3:
                            //ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu();
                            //professorCRSMenu.showMenu(userID);
                            break;

                        default:
                            System.out.println("Invalid Credentials");
                    }
                    break;

                case 2:
                    // registration
                    System.out.println("Enter your UserID: ");
                    userID = in.next();
                    System.out.println("Enter your Password: ");
                    password1 = in.next();
                    System.out.println("Enter your Name: ");
                    String name = in.next();
                    // Student student = new Student(userID,name,"student",password,null,null, false,null,null,false);
                    // pendingDB.put(userID,student);
                    break;

                case 3:
                    System.out.println("Update Password");
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid Input");

            }
        }
        while(input!=4);
        in.close();
    }
}
