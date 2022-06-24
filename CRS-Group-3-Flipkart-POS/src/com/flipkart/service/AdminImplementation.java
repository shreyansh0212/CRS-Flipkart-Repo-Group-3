package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.exception.CourseAlreadyPresent;
import com.flipkart.exception.CourseNotPresentException;
import com.flipkart.exception.UserAlreadyExist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.flipkart.application.CRSApplication.*;


public class AdminImplementation implements AdminInterface{
    AdminDAOInterface adminDAOInterface = new AdminDAOOperation();
    /**
     *
     */
    public void loginMsg(String userID){
        String username = adminDAOInterface.getUsername(userID);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Admin - " + username + "(" + userID + ") has logged in at time " + localDateTime);
    }

    public void viewCourseRegistration(String studentId){
        List<String> regcourses = adminDAOInterface.getCourses(studentId);
        regcourses.forEach(System.out::println);
    }

    public void updIsRegistered(String studentId, boolean approval){
        adminDAOInterface.updIsRegistered(studentId,approval);
    }
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
    public void PendingRequests() {adminDAOInterface.viewPendingRequests();}

    }
}
