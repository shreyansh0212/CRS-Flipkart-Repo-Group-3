package com.flipkart.application;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.flipkart.application.AdminCRSMenu;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public class CRSApplication {

    public static HashMap<Integer, Admin> adminDB;

    public static HashMap<Integer, Student> studentDB;

    public static HashMap<Integer, Professor> professorDB;

    public static HashMap<Integer, Student> pendingDB;

    public static HashMap<String, Course> courseCatalogDB;



    public static void main(String[] args)
    {
        // Scanner scan = new Scanner(System.in);
        // Check if the scanner has a token
        // System.out.println("Boolean Result: " + scan.hasNext());
        // Print the string
        // System.out.println("String: " +scan.nextLine());
        // scan.close();
        /**
         *      1. Login + Role
         *      2. New Student Registration
         *      3. Update Password
         *      4. Exit
         */
        // add admin
        Integer input;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("----------- Welcome to Course Registration System ------------");
            System.out.println("1. Login + Role");
            System.out.println("2. New Student Registration");
            System.out.println("3. Update Password");
            System.out.println("4. Exit");
            input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Select your Role: ");
                    System.out.println("1. Admin");
                    System.out.println("2. Student");
                    System.out.println("3. Professor");

                    int roleInput = in.nextInt();

                    System.out.print("Enter your UserID: ");
                    String userID = in.nextLine();
                    System.out.println("Enter your Password: ");
                    String password = in.nextLine();

                    // verify


                    switch (roleInput) {
                        case 1:
                            AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                            adminCRSMenu.showMenu();
                            break;

                        case 2:
                            StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                            studentCRSMenu.showMenu();
                            break;

                        case 3:
                            ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu();
                            professorCRSMenu.showMenu();
                            break;

                        default:
                            System.out.println("Invalid Role");
                    }
                    break;

                case 2:
                    // registration
                    System.out.print("Enter your UserID: ");
                    userID = in.nextLine();
                    System.out.println("Enter your Password: ");
                    password = in.nextLine();
                    System.out.println("Enter your Name: ");
                    String name = in.nextLine();
                    Student student = new Student(Integer.parseInt(userID),name,"student",password,null,null, false,null,false);
                    pendingDB.put(Integer.parseInt(userID),student);
                    break;

                case 3:
                    System.out.println("Update Password");
                    break;

                default:
                    System.out.println("Invalid Input");

            }
        }
        while(input!=4);


//        System.out.println("--------Enter Your Details-------- ");
//        //Scanner in = new Scanner(System.in);
//        System.out.print("Enter your UserID: ");
//        String UserID = in.nextLine();
//         System.out.println("Name: " + UserID);
//
//        // Try and Catch
//        System.out.print("Enter your Password: ");
//        String password = in.nextLine();
//         System.out.println("Password: " + password);
//         System.out.print("Enter your role: ");
//
//        String role = in.nextLine();
//        // Specific Role Menu
//        System.out.println("Role: " + role);

        in.close();
    }
}
