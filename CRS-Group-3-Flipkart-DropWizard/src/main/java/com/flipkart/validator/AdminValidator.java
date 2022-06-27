/**
 *
 */
package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

/**
 *
 * Class for Admin Validator
 *
 */
public class AdminValidator {

    /**
     * Method to validate if newCourse is not already present in catalog
     * @param newCourse
     * @param courseList
     * @return
     */
    public static boolean isValidNewCourse(Course newCourse, List<Course> courseList) {
        for(Course course : courseList) {
            if(newCourse.getCourseID().equalsIgnoreCase(course.getCourseID())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to validate if dropCourse is already present in catalog
     * @param dropCourseCode
     * @param courseList
     * @return
     */
    public static boolean isValidDropCourse(String dropCourseCode, List<Course> courseList) {
        for(Course course : courseList) {
            if(dropCourseCode.equalsIgnoreCase(course.getCourseID())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to validate if studentId is still unapproved
     * @param studentId
     * @param studentList
     * @return
     */
    public static boolean isValidUnapprovedStudent(String studentId, List<Student> studentList) {
        for(Student student : studentList) {
            if(studentId == student.getUserID()) {
                return true;
            }
        }
        return false;
    }
}