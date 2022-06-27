package com.flipkart.bean;

/**
 * Course Bean Class
 */
public class Course {
    private String courseID;
    private String courseName;
    private boolean isOffered;
    private String professorID;
    private int courseStrength;

    /**
     * Parameterized Constructor
     * @param courseID
     * @param courseName
     * @param isOffered
     * @param professorID
     * @param courseStrength
     */
	public Course(String courseID, String courseName, boolean isOffered, String professorID,Integer courseStrength) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.isOffered = isOffered;
		this.professorID = professorID;
        this.courseStrength = courseStrength;
	}

    /**
     * Default Constructor
     */
    public Course() {

    }

    /**
     * Get CourseID
     * @return
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Set CourseID
     * @param courseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Get Course Name
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set Course Name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * check if Offered
     * @return
     */
    public boolean isOffered() {
        return isOffered;
    }

    /**
     * Set Offered
     * @param offered
     */
    public void setOffered(boolean offered) {
        isOffered = offered;
    }

    /**
     * Get Professor ID
     * @return
     */
    public String getProfessorID() {
        return professorID;
    }

    /**
     * Set Professor ID
     * @param professorID
     */
    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }

    /**
     * Get Course Strength
     * @return
     */
    public Integer getCourseStrength() {
        return courseStrength;
    }

    /**
     * Set Course Strength
     * @param courseStrength
     */
    public void setCourseStrength(Integer courseStrength) {
        this.courseStrength = courseStrength;
    }
}
