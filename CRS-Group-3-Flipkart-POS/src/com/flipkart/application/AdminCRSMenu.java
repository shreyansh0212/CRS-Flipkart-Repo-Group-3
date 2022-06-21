package com.flipkart.application;

import java.util.Scanner;

public class AdminCRSMenu {

    public void showMenu() {
        Scanner in = new Scanner(System.in);
        int input=0;
        do {
            System.out.println("---------- Welcome Admin ----------");
            System.out.println("Please select your function:");
            System.out.println("1. Add Professor");
            System.out.println("2. Show Course Catalog");
            System.out.println("3. Add Course to Catalog");
            System.out.println("4. Delete Course to Catalog");
            System.out.println("5. Approve Student");
            System.out.println("6. Exit");
            input = in.nextInt();
            switch (input) {
                case 1:
                    addProfessor();
                    break;

                case 2:
                    showCourseCatalog();
                    break;

                case 3:
                    addCourseToCatalog();
                    break;

                case 4:
                    deleteCourseFromCatalog();
                    break;

                case 5:
                    approveStudent();
                    break;

                default:
                    System.out.println("Invalid Input");
            }
        }
        while(input!=6);

    }

    public void addProfessor() {

    }

    public void showCourseCatalog() {

    }

    public void addCourseToCatalog() {

    }

    public void deleteCourseFromCatalog() {

    }

    public void approveStudent() {

    }

}

