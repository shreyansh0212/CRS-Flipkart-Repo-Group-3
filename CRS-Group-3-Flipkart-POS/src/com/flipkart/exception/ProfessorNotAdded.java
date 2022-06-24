package com.flipkart.exception;

public class ProfessorNotAdded extends Exception{
    private String professorID;
    public ProfessorNotAdded (String professorID) {
        this.professorID = professorID;
    }
    public String getMessage() {
        return "Professor: " + professorID + " not added";
    }
}
