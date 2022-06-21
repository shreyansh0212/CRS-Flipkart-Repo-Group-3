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
        professorDB.put(professor.getUserID(),professor);
    }

    /**
     *
     */
    @Override
    public void generateReport() {

    }

    /**
     *
     */
    @Override
    public void addCourse(Course course) {
        courseCatalogDB.put((String)course.getCourseID(),course);
    }

    /**
     *
     */
    @Override
    public void dropCourse(String courseID) {
        System.out.println("Course Removed with ID: " + (Course)courseCatalogDB.get(courseID));
        courseCatalogDB.remove(courseID);
    }

    /**
     *
     */
    @Override
    public void showCourses() {
        for (Map.Entry entry:courseCatalogDB.entrySet()) {
            System.out.println(entry);
            // System.out.println("CourseID: " + (String)entry.getKey() + " Course Name: " + ((Course)entry.getValue()).getCourseName() + " ProfessorID: " + ((Course)entry.getValue()).getProfessorID());
        }
    }

    @Override
    public void approvePendingRequests() {
        for(Map.Entry entry:pendingDB.entrySet()) {
            studentDB.put((Integer)entry.getKey(),(Student)entry.getValue());
            System.out.println("Student with UserID: " + (Integer)entry.getKey() + " Approved");
        }
        pendingDB.clear();
    }
}
