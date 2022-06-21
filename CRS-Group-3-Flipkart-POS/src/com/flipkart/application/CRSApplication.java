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
        adminDB.put("000",admin0);

        Professor prof0 = new Professor("P001","prof1","prof","pass",null,null,null,null);
        professorDB.put("P001",prof0);
        Professor prof1 = new Professor("P002","prof2","prof","pass",null,null,null,null);
        professorDB.put("P002",prof1);
        Professor prof2 = new Professor("P003","prof3","prof","pass",null,null,null,null);
        professorDB.put("P003",prof2);
        Professor prof3 = new Professor("P004","prof4","prof","pass",null,null,null,null);
        professorDB.put("P004",prof3);

        Course course1 = new Course("C001","Elementary1",true,"P001");
        Course course2 = new Course("C002","Elementary2",true,"P002");
        Course course3 = new Course("C003","Elementary3",true,"P001");
        Course course4 = new Course("C004","Elementary4",true,"P003");
        Course course5 = new Course("C005","Elementary5",true,"P004");
        Course course6 = new Course("C006","Elementary6",true,"P002");
        courseCatalogDB.put("C001",course1);
        courseCatalogDB.put("C002",course2);
        courseCatalogDB.put("C003",course3);
        courseCatalogDB.put("C004",course4);
        courseCatalogDB.put("C005",course5);
        courseCatalogDB.put("C006",course6);

        Student student1 = new Student("S001","student1",null,"pass",null,null,false,null,null,false);
        studentDB.put("S001",student1);

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
                    int roleInput=0;
                    if(studentDB.get(userID)!=null && studentDB.get(userID).getPassword().equals(password)) roleInput=2;
                    if(adminDB.get(userID)!=null && adminDB.get(userID).getPassword().equals(password)) roleInput=1;
                    if(professorDB.get(userID)!=null && professorDB.get(userID).getPassword().equals(password)) roleInput=3;

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
                    Student student = new Student(userID,name,"student",password,null,null, false,null,null,false);
                    pendingDB.put(userID,student);
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
