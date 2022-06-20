package com.flipkart.bean;

public class StudentGrade {
    private String courseID;
    private String grade;

    /**
	 * @param courseID
	 * @param grade
	 */
	public StudentGrade(String courseID, String grade) {
		super();
		this.courseID = courseID;
		this.grade = grade;
	}

	public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
