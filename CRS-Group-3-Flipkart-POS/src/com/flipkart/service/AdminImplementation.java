package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.Map;

import static com.flipkart.application.CRSApplication.*;


public class AdminImplementation implements AdminInterface{
    /**
     *
     */
    @Override
    public void approveStudent() {
        // registration approval
    }

    /**
     *
     */
    @Override
    public void addProfessor(Professor professor) {
        //professorDB.put(professor.getUserID(),professor);
        System.out.println("Professor (UserID: " + professor.getUserID() + ") added successfully!");
    }

    /**
     *
     */
    @Override
    public void generateReport() {
        // done in student implementation
    }

    /**
     *
     */
    @Override
    public void addCourse(Course course) {
        //courseCatalogDB.put((String)course.getCourseID(),course);
        System.out.println("Course Added with ID: " + course.getCourseID() + " course name: " + course.getCourseName());
    }

    /**
     *
     */
    @Override
    public void dropCourse(String courseID) {
        //System.out.println("Course Removed with ID: " + courseID + " course name: " + courseCatalogDB.get(courseID).getCourseName());
        //courseCatalogDB.remove(courseID);
    }

    /**
     *
     */
    @Override
    public void showCourses() {
//        for (Map.Entry entry:courseCatalogDB.entrySet()) {
//            System.out.println("CourseID: " + (String)entry.getKey() + ", Course Name: " + ((Course)entry.getValue()).getCourseName() + ", ProfessorID: " + ((Course)entry.getValue()).getProfessorID());
//        }
    }

    @Override
    public void approvePendingRequests() {
//        for(Map.Entry entry:pendingDB.entrySet()) {
//            Student student = (Student)entry.getValue();
//            studentDB.put((String)entry.getKey(),student);
//            System.out.println("Student with UserID: " + (String)entry.getKey() + " Approved");
//            student.setRegistered(true);
//        }
//        pendingDB.clear();
    }
}
