package com.flipkart.rest;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.exception.StudentNotApproved;
import com.flipkart.service.*;
import io.dropwizard.jersey.PATCH;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Student;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.UserImplementation;

@Path("/user")
public class UserRestAPI {
    StudentInterface studentInterface= new StudentImplementation();
    AdminInterface adminInterface = new AdminImplementation();
    UserInterface userInterface =new UserImplementation();

    @PATCH
    @Path("/updatePassword")
    @Produces("application/json")
    public Response updatePassword(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("newPassword") String newPassword) throws ValidationException, UserNotFoundException {

        if(userInterface.updatePassword(userId, newPassword)) {
            return Response.status(201).entity("Password updated successfully! ").build();
        }
        else {
            return Response.status(500).entity("Something went wrong, please try again!").build();
        }

    }

    /**
     *
     * @param userId
     * @param password
     * @return
     */

    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response verifyCredentials(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("password") String password) throws ValidationException, StudentNotApproved {

        try
        {
            boolean loggedin=userInterface.verifyCredentials(userId, password);
            if(loggedin)
            {
                String role=userInterface.getRole(userId);
                switch(role)
                {

                    case "student":
                        boolean isApproved= studentInterface.checkApprovalStatus(userId);
                        if(!isApproved)
                        {
                            return Response.status(200).entity("Login unsuccessful! Student "+userId+" has not been approved by the administration!" ).build();
                        }
                        break;

                }
                return Response.status(200).entity("Login successful").build();
            }
            else
            {
                return Response.status(500).entity("Invalid credentials!").build();
            }
        }
        catch (UserNotFoundException e)
        {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    /**
     *
     * @param userId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/getRole")
    @Produces("application/json")
    public String getRole(
            @NotNull
            @QueryParam("userId") String userId ) throws ValidationException{

        return userInterface.getRole(userId);
    }

    /**
     *
     * @param student
     * @return 201, if user is created, else 500 in case of error
     */
    @POST
    @Path("/studentRegistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@Valid Student student)
    {
        try
        {
            studentInterface.newRegistration(student.getUserID(),student.getName(), student.getPassword(), student.getBatch(),student.getAddress());
        }
        catch(Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

        return Response.status(201).entity("Registration Successful for "+student.getUserID()).build();
    }

}