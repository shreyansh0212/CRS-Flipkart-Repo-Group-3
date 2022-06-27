package com.flipkart.bean;

/**
 * Student Grade bean Class
 */
public class StudentGrade {
    private String courseID;
    private String grade;

    /**
     * Constructor
     * @param courseID
     * @param grade
     */
	public StudentGrade(String courseID, String grade) {
		super();
		this.courseID = courseID;
		this.grade = grade;
	}

    /**
     * Get Course ID
     * @return
     */
	public String getCourseID() {
        return courseID;
    }

    /**
     * Set Course ID
     * @param courseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Get Grade
     * @return
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Set Grade
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

}
