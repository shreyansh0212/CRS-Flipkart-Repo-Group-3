package com.flipkart.application;

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

    public static HashMap<String, Admin> adminDB = new HashMap<>();
    // Admin admin0 = new Admin(000,"admin0","admin","pass",null);

    // StudentID, Student
    public static HashMap<String, Student> studentDB = new HashMap<>();

    // ProfessorID, Professor
    public static HashMap<String, Professor> professorDB = new HashMap<>();

    // StudentID, Student
    public static HashMap<String, Student> pendingDB = new HashMap<>();

    // CourseID, Course
    public static HashMap<String, Course> courseCatalogDB = new HashMap<>();

    // courseID, StudentID, Grade
    public static HashMap<Pair<String,String>,String> registeredCoursesDB = new HashMap<>();



    public static void main(String[] args) {
        Admin admin0 = new Admin("000","admin0","admin","pass",null);
        adminDB.put(admin0.getUserID(),admin0);

        Professor prof0 = new Professor("001","prof0","prof","pass",null,null,null,null);
        professorDB.put(prof0.getUserID(),prof0);

        Course course1 = new Course("CS0001","Elementary",true,"001");
        Course course2 = new Course("CS0002","Intermediate",true,"001");
        courseCatalogDB.put("CS0001",course1);
        courseCatalogDB.put("CS0002",course2);

        Student student1 = new Student("S001","student1",null,"pass",null,null,false,null,null,false);
        studentDB.put("S001",student1);

        Integer input;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("----------- Welcome to Course Registration System ------------");
            System.out.println("1. Login + Role");
            System.out.println("2. New Student Registration");
            System.out.println("3. Update Password");
            System.out.println("4. Exit");
            System.out.println("Enter Your Choice: ");
            input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Select your Role: ");
                    System.out.println("1. Admin");
                    System.out.println("2. Student");
                    System.out.println("3. Professor");
                    System.out.println("Enter Your Choice: ");
                    int roleInput = in.nextInt();

                    System.out.println("Enter your UserID: ");
                    String userID = in.next();
                    System.out.println("Enter your Password: ");
                    String password = in.next();

                    // verify


                    switch (roleInput) {
                        case 1:
                            AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                            adminCRSMenu.showMenu();
                            break;

                        case 2:
                            StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                            studentCRSMenu.showMenu(userID);
                            break;

                        case 3:
                            ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu();
                            professorCRSMenu.showMenu(userID);
                            break;

                        default:
                            System.out.println("Invalid Role");
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
                    Student student = new Student(userID,name,"student",password,null,null, false,null,null,false);
                    pendingDB.put(userID,student);
                    break;

                case 3:
                    System.out.println("Update Password");
                    break;

                default:
                    System.out.println("Invalid Input");

            }
        }
        while(input!=4);
        in.close();
    }
}
