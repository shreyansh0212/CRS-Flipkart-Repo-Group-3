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
import com.flipkart.constants.DatabaseUtil;
import com.flipkart.dao.UserDAOInterface;
import com.flipkart.dao.UserDAOOperation;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.UserImplementation;
import com.flipkart.service.UserInterface;
import javafx.util.Pair;

public class CRSApplication {

    public static Connection connection;

    static boolean successfulLogin;

    public static UserInterface userInterface = new UserImplementation();

    public static void main(String[] args) {

        connection = DatabaseUtil.getConn();
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
                    String password = in.next();

                    // verifying credentials

                    String role = "";

                    boolean successfulLogin = userInterface.verifyCredentials(userID,password);

                    if(successfulLogin) {
                        role = userInterface.getRole(userID);
                    }
                    if(role.equals(null)) role="";
                    switch (role) {
                        case "admin":
                            AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                            adminCRSMenu.showMenu(userID);
                            break;

                        case "student":
                            StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                            studentCRSMenu.showMenu(userID);
                            break;

                        case "professor":
                            ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu();
                            professorCRSMenu.showMenu(userID);
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
                    password = in.next();
                    System.out.println("Enter your Name: ");
                    String name = in.next();
                    System.out.println("Enter your Batch: ");
                    String batch = in.next();
                    System.out.println("Enter your Address: ");
                    String address = in.next();
                    StudentInterface studentInterface =new StudentImplementation();
                    studentInterface.newRegistration(userID,password,name,batch,address);
                    break;

                case 3:
                    System.out.println("Enter your UserID: ");
                    userID = in.next();
                    System.out.println("Enter your Password: ");
                    password = in.next();
                    userInterface.updatePassword(userID,password);
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
