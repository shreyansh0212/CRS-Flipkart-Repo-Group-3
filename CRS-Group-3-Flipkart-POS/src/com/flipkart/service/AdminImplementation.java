package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;

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
    public void addProfessor(Professor professor) throws UserAlreadyExist {
       try{
           adminDAOInterface.addProfessor(professor);
       }catch (UserAlreadyExist e){
           throw e;
       }
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
    public void addCourse(Course course) throws CourseAlreadyPresent {
        try{
            adminDAOInterface.addCourse(course);
        }catch(CourseAlreadyPresent e){
            throw e;
        }
    }

    @Override
    public void dropCourse(String courseID) throws CourseNotPresentException {
        try{
            adminDAOInterface.dropCourse(courseID);
        }catch(CourseNotPresentException e){
            throw e;
        }
    }

    @Override
    public void showCourses() {
        adminDAOInterface.showCourses();
    }

    @Override
    public void approvePendingRequests() {

    }
}
