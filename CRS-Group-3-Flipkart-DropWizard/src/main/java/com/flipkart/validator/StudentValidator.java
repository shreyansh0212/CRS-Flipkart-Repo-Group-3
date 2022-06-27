package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotPresentException;

/**
 *
 * Class for Student Validator
 *
 */
public class StudentValidator {

    /**
     * Method to validate if student is already registered for this particular course (courseCode) or not
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return
     * @throws CourseNotPresentException
     */
    public static boolean isRegistered(String courseCode,int studentId,List<Course>registeredCourseList) throws CourseNotPresentException
    {
        for(Course course : registeredCourseList)
        {
            if(courseCode.equalsIgnoreCase(course.getCourseID()))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Method to validate if courseID is valid or not
     * @param courseCode
     * @param availableCourseList
     * @return
     */
    public static boolean isValidCourseCode(String courseCode,List<Course>availableCourseList)
    {
        for(Course course : availableCourseList)
        {
            if(courseCode.equalsIgnoreCase(course.getCourseID()))
            {
                return true;
            }
        }

        return false;

    }

    /**
     * Method to check Registration Status
     * @param registeredCourseList
     * @param availableCourseList
     * @return boolean indicating Registration Status
     */
    public static boolean isValidRegistration(List<String> registeredCourseList , List<Course>availableCourseList)
    {
        for(int j=0;j<registeredCourseList.size();j++)
        {
            for(int i=j+1;i<registeredCourseList.size();i++)
            {
                if(registeredCourseList.get(i).equals(registeredCourseList.get(j)))
                    return false;
            }
        }



        int count=0;
        for(String registeredCourse : registeredCourseList)
        {
            for(Course availableCourse: availableCourseList)
            {

                if(availableCourse.getCourseID().equals(registeredCourse))
                    count++;
            }
        }

        return count==6;

    }

}