package com.qoonnect.rectem_api.service;

import com.qoonnect.rectem_api.dao.UserDao;
import com.qoonnect.rectem_api.model.Pdf;
import com.qoonnect.rectem_api.model.User;
import com.qoonnect.rectem_api.security.PasswordHashVerifier;
import com.qoonnect.rectem_api.security.PasswordHasher;
import com.qoonnect.rectem_api.util.FileIOUtil;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
    private final UserDao userDao;
    private final FileIOUtil fileIOUtil;
    public UserService(){
        fileIOUtil=new FileIOUtil();
        userDao = new UserDao();

    }
    public Response saveUser(String name,String password,InputStream profilePicture, FormDataContentDisposition fileDetail) {
        if(name==null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        try{
            PasswordHasher passwordHasher = new PasswordHasher(password);
            String pass = passwordHasher.getHashedPasswordPass();
            System.out.println(pass);
            // Create user object
            User user = new User();
            user.setUsername(name);
            user.setPassword(pass);
            user.setProfilePicture(fileIOUtil.getUploadedFile(profilePicture));

            userDao.save(user);
            return Response.ok(user).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    public Response getUserById(Long id) {
        if(id==null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        User user = userDao.findById(id);

        if (user == null || user.getProfilePicture() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Return the profile picture as stream
        InputStream inputStream = new ByteArrayInputStream(user.getProfilePicture());
        return Response.ok(inputStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + user.getId() + "_profile_picture.jpg\"")
                .build();

    }

    public Response verifyUser(User user) {
        if(user==null){
            System.out.println("user is null");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        System.out.println(user.getPassword());
        User confirmUser = userDao.findByUsername(user.getUsername());
        if(confirmUser==null || confirmUser.getPassword()==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean passwordVerified = PasswordHashVerifier.verifyPassword(confirmUser.getPassword(), user.getPassword());
        if(passwordVerified){

            return Response.ok(confirmUser).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}