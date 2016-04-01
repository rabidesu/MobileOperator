//package com.tsystems.jschool.mobile.controllers;
//
//import com.tsystems.jschool.mobile.entities.User;
//import com.tsystems.jschool.mobile.services.API.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/report")
//@Controller
//public class RestController {
//
//    @Autowired
//    private UserService userService;
//
//    @GET
//    @Path("/{action}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public User getReport(@PathParam("action") String action) {
//        return userService.getUserById(action);
//    }
//}
