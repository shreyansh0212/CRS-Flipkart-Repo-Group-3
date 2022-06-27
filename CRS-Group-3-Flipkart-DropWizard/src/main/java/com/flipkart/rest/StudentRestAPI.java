/**
 *
 */
package com.flipkart.rest;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Payment;
import com.flipkart.exception.*;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.service.StudentImplementation;

@Path("/student")
public class StudentRestAPI {

    StudentInterface studentInterface = new StudentImplementation();
    private static Logger logger = Logger.getLogger(StudentRestAPI.class);

    /**
     * Method to handle API request for course registration
     * @param courseList
     * @param studentID
     * @throws ValidationException
     * @return
     */

    @POST
    @Path("/registerCourses")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourses(List<String> courseList,
                                    @NotNull
                                    @QueryParam("studentId") String studentID)	throws ValidationException{

        try
        {
            if(studentInterface.getRegistrationStatus(studentID))
                return Response.status(200).entity("Student "+ studentID+" is already registered.").build();

            List<EnrolledStudent> availableCourseList = studentInterface.viewEnrolledCourses(studentID);
            Set<String> hash_set = new HashSet<String>();

            for(String courseID:courseList) {
                studentInterface.checkCourse(courseID, studentID, availableCourseList);

                if(!hash_set.add(courseID))
                    return Response.status(500).entity("Duplicate value  : "+courseID).build();
            }

            for(String courseID:courseList)
                studentInterface.addCourse(courseID, studentID);

            studentInterface.setRegistrationStatus(studentID);
        }
        catch (CourseLimitExceededException | SeatNotAvailableException | CourseNotPresentException | CourseAlreadyRegistered e)
        {
            logger.info(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }


        return Response.status(201).entity( "Registration Successful").build();

    }

    @PUT
    @Path("/fillPreference")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fillPreference(
            @NotNull
            @QueryParam("studentID") String studentID,
            @NotNull
            @QueryParam("preference1") String preferenceOne,
            @NotNull
            @QueryParam("preference2") String preferenceTwo,
            @NotNull
            @QueryParam("preference3") String preferenceThree,
            @NotNull
            @QueryParam("preference4") String preferenceFour,
            @NotNull
            @QueryParam("preference5") String preferenceFive,
            @NotNull
            @QueryParam("preference6") String preferenceSix) throws ValidationException, UserNotFoundException, StudentNotApproved {
        if(!studentInterface.checkApprovalStatus(studentID))
            return Response.status(200).entity("Student Approval Pending").build();
        try {
            List<String> preference = Arrays.asList(preferenceOne,preferenceTwo,preferenceThree,preferenceFour,preferenceFive,preferenceSix);
            studentInterface.preferenceUpdate(studentID,preference);
            return Response.status(201).entity("You have added Course Preference").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }



    /**
     * Handles api request to add a course
     * @param courseID
     * @param studentID
     * @return
     * @throws ValidationException
     */
    @POST
    @Path("/addCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(
            @NotNull
            @QueryParam("courseID") String courseID,
            @NotNull
            @QueryParam("studentID") String studentID) throws ValidationException{


//        if(!studentInterface.getRegistrationStatus(studentID))
//            return Response.status(200).entity("Student course registration is pending").build();

        try{
//            studentInterface.checkCourse(courseID, studentID, availCourseList);
            studentInterface.addCourse(courseID, studentID);
            return Response.status(201).entity( "You have successfully added Course : " + courseID).build();
        }
        catch (CourseLimitExceededException | SeatNotAvailableException | CourseNotPresentException | CourseAlreadyRegistered e)
        {
            System.out.println(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    /**
     * Handles API request to drop a course
     * @param courseID
     * @param studentID
     * @return
     * @throws ValidationException
     */
    @DELETE
    @Path("/dropCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(
            @NotNull
            @QueryParam("courseID") String courseID,
            @NotNull
            @QueryParam("studentID") String studentID) throws ValidationException{

//        if(studentInterface.getRegistrationStatus(studentID) == false)
//            return Response.status(200).entity("Student course registration is pending").build();

        try{
            studentInterface.dropCourse(courseID, studentID);
            return Response.status(201).entity( "You have successfully dropped Course : " + courseID).build();
        }
        catch(CourseNotPresentException e) {
            return Response.status(501).entity("You have not registered for course : ").build();
        }
    }


    /**
     * Method handles API request to view the list of available courses for a student
     *
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewAvailableCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourseCatalog(){
        return studentInterface.showCourses();
    }


    /**
     * Method handles API request to view the list of registered courses for a student
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewEnrolledCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnrolledStudent> viewEnrolledCourses(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{

        return studentInterface.viewEnrolledCourses(studentId);
    }

    /**
     * Method handles request to display the grade card for student
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGrade> viewGradeCard(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        return studentInterface.viewGradeCard(studentId);
    }

    /**
     * Method handles API request to make payment for registered courses
     * @param userID
     * @param mode
     * @return
     * @throws ValidationException
     */
    @POST
    @Path("/payFees")
    @Produces(MediaType.APPLICATION_JSON)
    public Payment payFees(
            @NotNull
            @QueryParam("userID") String userID,
            @NotNull
            @QueryParam("amount") float amount,
            @NotNull
            @QueryParam("mode") String mode) throws ValidationException, UserNotFoundException, StudentNotRegistered, SQLException, FeesAlreadyPaid {

            return studentInterface.payFees(userID,amount,mode);
    }



}