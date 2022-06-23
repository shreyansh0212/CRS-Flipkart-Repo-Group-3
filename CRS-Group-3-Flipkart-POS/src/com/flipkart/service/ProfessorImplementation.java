package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.ProfessorDAOOperation;
import javafx.util.Pair;

import java.util.*;

import static com.flipkart.application.CRSApplication.*;

public class ProfessorImplementation implements ProfessorInterface{
    /**
     *
     */
    @Override
    public void addGrade(String courseID) {
        ProfessorDAOInterface showEnrolledStudents = new ProfessorDAOOperation();
        List<String> students = showEnrolledStudents.getCourses(courseID);
        Scanner in = new Scanner(System.in);
        for(int i = 0; i < students.size(); i++){
            System.out.println("Enter grade for " + students.get(i)+": ");
            String grade = in.next();
            showEnrolledStudents.addGrade(courseID,students.get(i),grade);
        }
//        List< Student> enrolledStudents = viewEnrolledStudents(courseID);
//        System.out.println("Enter Grade for Students in " + courseID + " : ");
//        Scanner in = new Scanner(System.in);
//        for(Student enrolled:enrolledStudents) {
//            System.out.println("StudentID " + enrolled.getUserID() +  " : ");
//            String grade = in.next();
//            Pair<String,String> psi=new Pair<String,String>(courseID, enrolled.getUserID());
//            registeredCoursesDB.put(psi,grade);
//            // studentDB.get(enrolled.getUserID()).setCoursePreferences() = enrolled.getCoursesRegistered();
//        }
    }

    /**
     *
     */
    @Override
    public void viewEnrolledStudents(String courseID) {
//        List <Student > enrolledStudents = new ArrayList<Student>();
//        for(Map.Entry entry:registeredCoursesDB.entrySet()) {
//            Pair<String,String> psi = (Pair<String,String>) entry.getKey();
//            if(psi.getKey().equals(courseID)) {
//                enrolledStudents.add(studentDB.get(psi.getValue()));
//                System.out.println("Student: " + psi.getValue());
//            }
//        }
//        return enrolledStudents;
        ProfessorDAOInterface showEnrolledStudents = new ProfessorDAOOperation();
        List<String> students = showEnrolledStudents.getCourses(courseID);
        for(int i = 0; i < students.size(); i++){
            System.out.println(students.get(i));
        }
    }

    /**
     *
     */
    @Override
    public void getCourses(String userID) {
        ProfessorDAOInterface showCourses = new ProfessorDAOOperation();
        List<String> courses = showCourses.getCourses(userID);
        for(int i = 0; i < courses.size(); i++){
            System.out.println(courses.get(i));
        }
//        for (Map.Entry entry:courseCatalogDB.entrySet()) {
//            Course course = (Course) entry.getValue();
//
//            if(course.isOffered() && course.getProfessorID().equals(userID)) {
//
//                System.out.println("CourseID: "+ course.getCourseID());
//                courses.add(course.getCourseID());
//            }
//        }
    }
}
