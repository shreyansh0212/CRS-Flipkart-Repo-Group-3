package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.flipkart.application.CRSApplication.courseCatalogDB;
import static com.flipkart.application.CRSApplication.registeredCoursesDB;

public class StudentImplementation implements StudentInterface{
    /**
     * @param student
     */
    @Override
    public void registerCourses(Student student) {
        List<String> preference = new ArrayList<>();
        showCourses();
        System.out.println("Select four primary and two alternative courses using CourseID: ");
        Scanner in = new Scanner(System.in);
        for(int i=1;i<=6;i++) {
            System.out.println(i + ". Enter CourseID");
            preference.add(in.next());
        }
        student.setCoursePreferences(preference);
        for(int i=0;i<6;i++) {
            System.out.println(preference.get(i));
        }
        // not applying student strength condition
        List<String> preferences = student.getCoursePreferences();
        for(int i=0;i<4;i++) {
            Pair<String,String> pss = new Pair<>(preferences.get(i),student.getUserID());
            registeredCoursesDB.put(pss,null);
        }
        // in.close();
    }

    /**
     * @param student
     */
    @Override
    public void addCourse(Student student) {
        showCourses();
        System.out.println("Enter CourseID to add Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
        Pair<String,String> pss = new Pair<>(courseID,student.getUserID());
        registeredCoursesDB.put(pss,null);
        System.out.println("Course Added with CourseID: " + courseID);
    }

    /**
     * @param student
     */
    @Override
    public void dropCourse(Student student) {
        List<String> enrolledCourses = viewEnrolledCourses(student);
        for(int i=0;i<enrolledCourses.size();i++) {
            Pair<String,String> pss = new Pair<>(enrolledCourses.get(i),student.getUserID());
            String grade = registeredCoursesDB.get(pss);
            // System.out.println("CourseID: " + enrolledCourses.get(i) + " --> Grade: " + grade);
        }
        System.out.println("Enter CourseID to drop Course: ");
        Scanner in = new Scanner(System.in);
        String courseID = in.next();
        Pair<String,String> pss = new Pair<>(courseID,student.getUserID());
        registeredCoursesDB.remove(pss);
        System.out.println("Course Dropped with CourseID: " + courseID);
    }

    /**
     * @param student
     */
    @Override
    public List<String> viewEnrolledCourses(Student student) {
        List<String> enrolledCourses = new ArrayList<>();
        for(Map.Entry entry:registeredCoursesDB.entrySet()) {
            Pair<String,String> pss = (Pair<String, String>) entry.getKey();
            if(pss.getValue().equals(student.getUserID())) {
                System.out.println("Enrolled Courses: ");
                System.out.println("CourseID: " + pss.getKey());
                enrolledCourses.add(pss.getKey());
            }
        }
        return enrolledCourses;

    }

    /**
     * @param student
     */
    @Override
    public void payFees(Student student) {
        if(!student.isFeePaymentStatus()) {
            System.out.println("Select Payment Method: ");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit Card");
            System.out.println("3. Net Banking");
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input) {
                case 1:
                    student.setFeePaymentStatus(true);
                    System.out.println("Used Credit Card");
                    break;

                case 2:
                    student.setFeePaymentStatus(true);
                    System.out.println("Used Debit Card");
                    break;

                case 3:
                    student.setFeePaymentStatus(true);
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
     * @param student
     */
    @Override
    public void changePassword(Student student) {

    }

    /**
     * @param student
     */
    @Override
    public void viewGradeCard(Student student) {
        System.out.println("Show Student Details!");
        System.out.println("Student Grades");
        List<String> enrolledCourses = viewEnrolledCourses(student);
        for(int i=0;i<enrolledCourses.size();i++) {
            Pair<String,String> pss = new Pair<>(enrolledCourses.get(i),student.getUserID());
            String grade = registeredCoursesDB.get(pss);
            System.out.println("CourseID: " + enrolledCourses.get(i) + " --> Grade: " + grade);
        }

    }

    /**
     *
     */
    @Override
    public void showCourses() {
        for (Map.Entry entry:courseCatalogDB.entrySet())
        {
            Course course = (Course) entry.getValue();
            System.out.println("CourseID: " + entry.getKey() + ", Course Name: " + course.getCourseName());
        }
    }
}
