package com.flipkart.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.EnrolledStudent;
import com.flipkart.service.ProfessorImplementation;
import javafx.util.Pair;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Course;
// import com.flipkart.bean.EnrolledStudent;
import com.flipkart.service.ProfessorInterface;
// import com.flipkart.service.ProfessorOperation;
import com.flipkart.validator.ProfessorValidator;

@Path("/professor")
public class ProfessorRestAPI {
    ProfessorInterface professorInterface=new ProfessorImplementation();

    @GET
    @Path("/getEnrolledStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnrolledStudent> viewEnrolledStudents(
            @NotNull
            @QueryParam("profId") String professorID) throws ValidationException	{

        List<EnrolledStudent> students=new ArrayList<>();
        try
        {
            students=professorInterface.viewEnrolledStudents(professorID);
        }
        catch(Exception ex)
        {
            return null;
        }
        return students;
    }

    @GET
    @Path("/getCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(
            @NotNull
            @QueryParam("profId") String profId) throws ValidationException	{

        List<Course> courses=new ArrayList<Course>();
        try
        {
            courses=professorInterface.getCourses(profId);
        }
        catch(Exception ex)
        {
            return null;
        }
        return courses;

    }

    @PUT
    @Path("/addGrade")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @QueryParam("studentId") String studentID,

            @NotNull
            @QueryParam("courseCode") String courseID,

            @NotNull
            @QueryParam("profId") String profId,

            @NotNull
            @QueryParam("grade") String grade) throws ValidationException  	{

        try
        {
            List<EnrolledStudent> enrolledStudents = professorInterface.viewEnrolledStudents(profId);
            List<Course> coursesEnrolled = professorInterface.getCourses(profId);
            professorInterface.addGrade(courseID, studentID, grade);
        }
        catch(Exception ex)
        {
            return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
        }
        return Response.status(200).entity( "Grade updated for student: "+studentID).build();

    }


}