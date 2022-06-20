package com.flipkart.application;

import java.util.Scanner;

public class CRSApplication {
    public static void main(String[] args)
    {
        String s = "Welcome to Course Registration System";
        //Create scanner Object and pass string in it

        System.out.println(s);
        // Scanner scan = new Scanner(System.in);
        // Check if the scanner has a token
        // System.out.println("Boolean Result: " + scan.hasNext());
        // Print the string
        // System.out.println("String: " +scan.nextLine());
        // scan.close();
        /**
         *      1. Login
         *      2. New Student Registration
         *      3. Update Password
         *      4. Select Role
         */
        System.out.println("--------Enter Your Details-------- ");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your UserID: ");
        String UserID = in.nextLine();
         System.out.println("Name: " + UserID);

        // Try and Catch
        System.out.print("Enter your Password: ");
        String password = in.nextLine();
         System.out.println("Password: " + password);
         System.out.print("Enter your role: ");

        String role = in.nextLine();
        // Specific Role Menu
        System.out.println("Role: " + role);

        in.close();
    }
}
