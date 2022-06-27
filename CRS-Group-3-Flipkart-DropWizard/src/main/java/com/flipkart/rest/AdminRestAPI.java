/**
 *
 */
package com.flipkart.rest;

import java.sql.SQLException;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.exception.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
// import com.flipkart.exception.CourseNotDeletedException;
// import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminImplementation;

@Path("/admin")
public class AdminRestAPI {

    AdminInterface adminOperation = new AdminImplementation();

    /**
     * /admin/assignCourseToProfessor
     * REST-service for assigning course to professor
     * @param courseID
     * @param professorId
     * @return
     */
    @POST
    @Path("/assignCourseToProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignCourseToProfessor(
            @NotNull
            @QueryParam("courseID") String courseID,
            @NotNull
            @QueryParam("professorID") String professorId) throws ValidationException {
        try {
            adminOperation.assignCourse(courseID, professorId);
            return Response.status(201).entity("courseID: " + courseID + " assigned to professor: " + professorId).build();

        } catch (Exception e) {

            return Response.status(409).entity(e.getMessage()).build();

        }
    }

    /**
     * /admin/addProfessor
     * REST-service for addding a new professor
     * @param professor
     * @return
     */
    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor professor) throws ValidationException, UserNotAdded {
        try {
            adminOperation.addProfessor(professor);
            return Response.status(201).entity("Professor with professorId: " + professor.getUserID() + " added").build();

        } catch (ProfessorNotAdded | UserAlreadyExist | ProfessorAlreadyExistsException | SQLException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    /**
     * /admin/viewPendingAdmissions
     * REST-service for getting all pending-approval of students
     * @return
     */
    @GET
    @Path("/viewPendingAdmissions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingAdmissions() {
        return adminOperation.pendingRequests();
    }

    /**
     * /admin/viewPendingRegistrations
     * REST-service for getting all pending course registrations of students
     * @return
     */
    @GET
    @Path("/viewPendingRegistrations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingRegistrations() {
        return adminOperation.viewPendingRegistrations();
    }

    /**
     * /admin/approveStudent
     * REST-service for approving the student admission
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
         try {
            adminOperation.approveStudent(studentId);
            return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();

        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

    }

    /**
     * /admin/registerStudent
     * REST-service for approving the student course registration
     * @param studentId
     * @return
     */
    @PUT
    @Path("/registerStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerStudent(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        try {
            adminOperation.approveCourseRegistration(studentId);
            return Response.status(201).entity("Student with studentId: " + studentId + " registered").build();

        } catch (Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    /**
     * /admin/viewCoursesInCatalogue
     * REST-service for getting courses in the catalog
     * @return
     */
    @GET
    @Path("/showCourseCatalog")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCoursesInCatalogue() {
        return adminOperation.showCourses();
    }

    /**
     * /admin/deleteCourse
     * REST-services for dropping a course from catalog
     * @param courseID
     * @return
     */
    @DELETE
    @Path("/deleteCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(
            @NotNull
            @QueryParam("courseID") String courseID) throws ValidationException, CourseNotPresentException {
            adminOperation.dropCourse(courseID);
            return Response.status(201).entity("Course with courseID: " + courseID + " deleted from catalog").build();

        // }
//        catch (CourseNotPresentException | CourseNotDeletedException e) {
//
//            return Response.status(409).entity(e.getMessage()).build();
//
//        }
    }

    /**
     * /admin/addCourse
     * REST-service for adding a new course in catalog
     * @param course
     * @return
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) throws ValidationException{
        try {
            adminOperation.addCourse(course);
            return Response.status(201).entity("Course with courseCode: " + course.getCourseID() + " added to catalog").build();

        }
        catch (CourseAlreadyPresent e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }
}
	