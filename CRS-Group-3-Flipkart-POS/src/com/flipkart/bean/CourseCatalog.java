package com.flipkart.bean;

public class CourseCatalog {
    private String CourseID;
    private String CourseName;

    /**
	 * @param courseID
	 * @param courseName
	 */
	public CourseCatalog(String courseID, String courseName) {
		super();
		CourseID = courseID;
		CourseName = courseName;
	}

	public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
