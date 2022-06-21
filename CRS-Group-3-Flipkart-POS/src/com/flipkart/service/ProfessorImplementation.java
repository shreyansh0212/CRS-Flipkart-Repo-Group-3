package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import javafx.util.Pair;

import java.util.*;

import static com.flipkart.application.CRSApplication.*;

public class ProfessorImplementation implements ProfessorInterface{
    /**
     *
     */
    @Override
    public void addGrade(String courseID) {
        List< Student> enrolledStudents = viewEnrolledStudents(courseID);
        System.out.println("Enter Grade for Students in " + courseID + " : ");
        Scanner in = new Scanner(System.in);
        for(Student enrolled:enrolledStudents) {
            System.out.println("StudentID " + enrolled.getUserID() +  " : ");
            String grade = in.next();
            Pair<String,String> psi=new Pair<String,String>(courseID, enrolled.getUserID());
            registeredCoursesDB.put(psi,grade);
            // studentDB.get(enrolled.getUserID()).setCoursePreferences() = enrolled.getCoursesRegistered();
        }
    }

    /**
     *
     */
    @Override
    public List <Student > viewEnrolledStudents(String courseID) {
        List <Student > enrolledStudents = new ArrayList<Student>();
        for(Map.Entry entry:registeredCoursesDB.entrySet()) {
            Pair<String,String> psi = (Pair<String,String>) entry.getKey();
            if(psi.getKey().equals(courseID)) {
                enrolledStudents.add(studentDB.get(psi.getValue()));
            }
        }
        return enrolledStudents;
    }

    /**
     *
     */
    @Override
    public List<String> getCourses(String userID) {
        List<String> courses = new ArrayList<>();
        for (Map.Entry entry:courseCatalogDB.entrySet()) {
            Course course = (Course) entry.getValue();

            if(course.isOffered() && course.getProfessorID().equals(userID)) {

                System.out.println("CourseID: "+ course.getCourseID());
                courses.add(course.getCourseID());
            }
        }
        return courses;
    }
}
