package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDAOInterface;
import com.flipkart.dao.CoursesDAOOperation;
import com.flipkart.dao.StudentDAOInterface;
import com.flipkart.dao.StudentDAOOperation;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentImplementation implements StudentInterface{
    /**
     * @param userID
     */
    @Override
    public void registerCourses(String userID) {
        List<String> preference = new ArrayList<>();
        showCourses();
        System.out.println("Select four primary and two alternative courses using CourseID: ");
        Scanner in = new Scanner(System.in);
        for(int i=1;i<=6;i++) {
            System.out.println(i + ". Enter CourseID: ");
            preference.add(in.next());
        }
        StudentDAOInterface upd = new StudentDAOOperation();
        upd.preferenceUpdate(userID,preference);
//        student.setCoursePreferences(preference);
//        for(int i=0;i<6;i++) {
//            System.out.println(preference.get(i));
//        }
        // not applying student strength condition
//        List<String> preferences = student.getCoursePreferences();
//        for(int i=0;i<4;i++) {
//            Pair<String,String> pss = new Pair<>(preferences.get(i),student.getUserID());
//            registeredCoursesDB.put(pss,null);
//        }
        // in.close();
    }

    /**
     * @param userID
     */
    @Override
    public void addCourse(String userID) {
        StudentDAOInterface pref = new StudentDAOOperation();
        pref.preferenceShow(userID);
        showCourses();
        System.out.println("Enter CourseID to add Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
        StudentDAOInterface upd = new StudentDAOOperation();
        upd.addToRegistration(userID,courseID);
//        Pair<String,String> pss = new Pair<>(courseID,student.getUserID());
//        registeredCoursesDB.put(pss,null);
//        System.out.println("Course Added with CourseID: " + courseID);
    }

    /**
     * @param userID
     */
    @Override
    public void dropCourse(String userID) {
        StudentDAOInterface pref = new StudentDAOOperation();
        pref.preferenceShow(userID);
        showCourses();
        System.out.println("Enter CourseID to add Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
        StudentDAOInterface upd = new StudentDAOOperation();
        upd.dropFromRegistration(userID,courseID);
//        List<String> enrolledCourses = viewEnrolledCourses(student);
//        for(int i=0;i<enrolledCourses.size();i++) {
//            Pair<String,String> pss = new Pair<>(enrolledCourses.get(i),student.getUserID());
//            //String grade = registeredCoursesDB.get(pss);
//            // System.out.println("CourseID: " + enrolledCourses.get(i) + " --> Grade: " + grade);
//        }
//        System.out.println("Enter CourseID to drop Course: ");
//        Scanner in = new Scanner(System.in);
//        String courseID = in.next();
//        Pair<String,String> pss = new Pair<>(courseID,student.getUserID());
//        //registeredCoursesDB.remove(pss);
//        System.out.println("Course Dropped with CourseID: " + courseID);
    }

    /**
     * @param userID
     */
    @Override
    public void viewEnrolledCourses(String userID) {
        StudentDAOInterface forReg = new StudentDAOOperation();
        if(!forReg.isRegistered(userID)){
            System.out.println("Registration has not been completed yet.");
            return;
        }
        StudentDAOInterface studCour = new StudentDAOOperation();
        List<String> enrollCour = studCour.viewEnrolledCourses(userID);
        for(int i = 0; i < enrollCour.size(); i++){
            System.out.println(enrollCour.get(i));
        }
//        List<String> enrolledCourses = new ArrayList<>();
//        for(Map.Entry entry:registeredCoursesDB.entrySet()) {
//            Pair<String,String> pss = (Pair<String, String>) entry.getKey();
//            if(pss.getValue().equals(student.getUserID())) {
//                System.out.println("Enrolled Courses: ");
//                System.out.println("CourseID: " + pss.getKey());
//                enrolledCourses.add(pss.getKey());
//            }
//        }
//        return enrolledCourses;

    }

    /**
     * @param userID
     */
    @Override
    public void payFees(String userID) {
        StudentDAOInterface forFee = new StudentDAOOperation();
        if(!forFee.isFeePaymentStatus(userID)) {
            System.out.println("Select Payment Method: ");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit Card");
            System.out.println("3. Net Banking");
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input) {
                case 1:
                    forFee.setFeePaymentStatus(userID,"Credit Card",userID,100);
                    System.out.println("Used Credit Card");
                    break;

                case 2:
                    forFee.setFeePaymentStatus(userID,"Debit Card",userID,100);
                    System.out.println("Used Debit Card");
                    break;

                case 3:
                    forFee.setFeePaymentStatus(userID,"Net Banking",userID,100);
                    System.out.println("Used Net Banking");
                    break;

                default:
                    System.out.println("Invalid Selection");
            }
        }
        else {
            System.out.println("Fees Already Paid");
        }
    }

    /**
     * @param userID
     */
    @Override
    public void viewGradeCard(String userID) {
//        System.out.println("Show Student Details!");
        StudentDAOInterface forGrades = new StudentDAOOperation();
        System.out.println("Student Grades");
        List<Pair<String,String>> enrolledCourses = forGrades.grades(userID);
        for(int i=0;i<enrolledCourses.size();i++) {
//            Pair<String,String> pss = new Pair<>(enrolledCourses.get(i),student.getUserID());
//            String grade = registeredCoursesDB.get(pss);
            System.out.println("CourseID: " + enrolledCourses.get(i).getKey() + " --> Grade: " + enrolledCourses.get(i).getValue());
        }

    }

    /**
     *
     */
    @Override
    public void showCourses() {
        CoursesDAOInterface show = new CoursesDAOOperation();
        show.courseList();
//        for (Map.Entry entry:courseCatalogDB.entrySet())
//        {
//            Course course = (Course) entry.getValue();
//            System.out.println("CourseID: " + entry.getKey() + ", Course Name: " + course.getCourseName());
//        }
    }
}
