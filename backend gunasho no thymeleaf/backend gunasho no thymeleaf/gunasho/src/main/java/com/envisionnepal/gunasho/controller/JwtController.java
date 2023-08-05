/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.controller;

import com.envisionnepal.gunasho.responseandregister.LoginRequest;
import com.envisionnepal.gunasho.entities.User;
import com.envisionnepal.gunasho.repository.UserRepository;
import com.envisionnepal.gunasho.responseandregister.Response;
import com.envisionnepal.gunasho.service.JwtService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/gunasho/authenticate")
public class JwtController {
    @Autowired
    private JwtService jwtservice ;
    @Autowired
     private  AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userepo;
//    @PostMapping(path = "/login", consumes = {"application/json"})
//    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest request) {
//        System.out.println("Entered login");
//        Optional<User> theUser = userepo.findByMobileNumber(request.getUsername());
//        Response response = new Response();
//
//        if (theUser.isPresent()) {
//            User user = theUser.get();
//            System.out.println(user.getMobileNumber());
//
//            if (user.isVerfication() && !user.isIssuspend()) {
//                Authentication authentication = authenticationManager
//                        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//                if (authentication.isAuthenticated()) {
//                    System.out.println("Inside if authentication");
//                    response.setToken(jwtservice.generateToken(user.getMobileNumber()));
//                    response.setUser(user);
//                    response.setMessage("Login Successful");
//                    return new ResponseEntity<>(response, HttpStatus.OK);
//                }
//                else {
//                    response.setMessage("Invalid Credentials");
//                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//                }
//
//            } else {
//                if (user.isIssuspend()) {
//                    response.setMessage("User is suspended");
//                    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
//                } else {
//                    response.setMessage("User is not verified by the admin");
//                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//                }
//            }
//        } else {
//            response.setMessage("User not found");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }


    @PostMapping(path = "/login", consumes = {"application/json"})
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest request) {
        System.out.println("Entered login");
        Optional<User> theUser = userepo.findByMobileNumber(request.getUsername());
        Response response = new Response();

        if (theUser.isPresent()) {
            User user = theUser.get();
            System.out.println(user.getMobileNumber());

            if (user.isVerfication() && !user.isIssuspend()) {
                try {
                    Authentication authentication = authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

                    if (authentication.isAuthenticated()) {
                        System.out.println("Inside if authentication");
                        response.setToken(jwtservice.generateToken(user.getMobileNumber()));
                        response.setUser(user);
                        response.setMessage("Login Successful");
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } else {
                        response.setMessage("Invalid password. Please try again.");
                        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                    }
                } catch (AuthenticationException e) {
                    // Authentication failed, password is incorrect
                    response.setMessage("Invalid password. Please try again.");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }
            } else {
                if (user.isIssuspend()) {
                    response.setMessage("User is suspended");
                    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
                } else {
                    response.setMessage("User is not verified by the admin");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }
            }
        } else {
            response.setMessage("User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}
