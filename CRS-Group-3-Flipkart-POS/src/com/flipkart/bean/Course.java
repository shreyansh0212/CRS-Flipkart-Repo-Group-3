package com.flipkart.bean;

public class Course {
    private String CourseID;
    private String CourseName;
    private boolean isOffered;
    private String professorID;

    /**
	 * @param courseID
	 * @param courseName
	 * @param isOffered
	 * @param professorID
	 */
	public Course(String courseID, String courseName, boolean isOffered, String professorID) {
		super();
		CourseID = courseID;
		CourseName = courseName;
		this.isOffered = isOffered;
		this.professorID = professorID;
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

    public boolean isOffered() {
        return isOffered;
    }

    public void setOffered(boolean offered) {
        isOffered = offered;
    }

    public String getProfessorID() {
        return professorID;
    }

    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }


}
