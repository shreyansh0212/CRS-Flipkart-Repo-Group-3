package com.flipkart.bean;

/**
 * Course Catalog Bean Class
 */
public class CourseCatalog {
    private String CourseID;
    private String CourseName;

    /**
     * Parameterized Constructor
     * @param courseID
     * @param courseName
     */
	public CourseCatalog(String courseID, String courseName) {
		super();
		CourseID = courseID;
		CourseName = courseName;
	}

    /**
     * Get CourseID
     * @return
     */
	public String getCourseID() {
        return CourseID;
    }

    /**
     * Set CourseID
     * @param courseID
     */

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    /**
     * Get Course Name
     * @return
     */
    public String getCourseName() {
        return CourseName;
    }

    /**
     * Set Course Name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
