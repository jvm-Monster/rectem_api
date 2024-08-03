package com.qoonnect.rectem_api.resource;

import com.qoonnect.rectem_api.model.User;
import com.qoonnect.rectem_api.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Path("/users")
public class UserResource {
    private final UserService userService;

    public UserResource() {
        userService = new UserService();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response registerUser(@FormDataParam("username") String name,
                                 @FormDataParam("password") String password,
                                 @FormDataParam("file") InputStream uploadedInputStream,
                                 @FormDataParam("file") FormDataContentDisposition fileDetail) {


        return userService.saveUser(name,password,uploadedInputStream,fileDetail); // Return response from userService.saveUser(user)
    }


    @POST
    @Path("/login")
    public Response loginUser(User user) {
        // Implementation code for logging in a user
        return userService.verifyUser(user);
    }


    @GET
    @Path("/{userId}/profile-picture")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getUserProfilePicture(@PathParam("userId") Long userId) {
        // Fetch user from userService or repository
        return userService.getUserById(userId);

    }
}
