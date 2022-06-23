package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.AdminDAOOperation;

import java.util.Map;

import static com.flipkart.application.CRSApplication.*;


public class AdminImplementation implements AdminInterface{
    AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
    /**
     *
     */


    @Override
    public void approveStudent(String studentid) {
        adminDAOInterface.approveStudent(studentid);
    }

    /**
     *
     */
    @Override
    public void addProfessor(Professor professor) {
       adminDAOInterface.addProfessor(professor);
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
        adminDAOInterface.addCourse(course);
    }

    @Override
    public void dropCourse(String courseID) {
        adminDAOInterface.dropCourse(courseID);
    }

    @Override
    public void showCourses() {
        adminDAOInterface.showCourses();
    }

    @Override
    public void approvePendingRequests() {

    }
}
