package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.GradeNotAddedException;

import java.util.List;

public interface ProfessorDAOInterface {
    public String getUsername(String userID);
    public boolean addGrade(String grade,String courseID,String studentID) throws GradeNotAddedException;
    public List<EnrolledStudent> viewEnrolledStudents(String professorID);
    public List<Course> getCourses(String professorID);
}
