

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.controller;

import com.envisionnepal.gunasho.entities.Admin;
import com.envisionnepal.gunasho.entities.User;
import com.envisionnepal.gunasho.repository.AdminRepository;
import com.envisionnepal.gunasho.responseandregister.*;
import com.envisionnepal.gunasho.service.JwtService;
import com.envisionnepal.gunasho.serviceimpl.UserServiceImpl;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/gunasho")
public class UserController {
    @Autowired
    UserServiceImpl serviceimpl;
     @Autowired
    JwtService jwtservice;
     @Autowired
    AdminRepository adminRepository;

    //all permit
    @PostMapping(value = "/authenticate/adduser", consumes = {"multipart/form-data"})
    public ResponseEntity<Response> registerUser(@ModelAttribute UserRegister request) throws IOException {
        Response response = new Response();
        String frontpath1 = "no file";
        String backpath2 = "no file";
        String recentiamgepath = "no file";
        if (request.getIdentityFront() != null) {
            frontpath1 = serviceimpl.uploadImage(request.getIdentityFront());
        }
        if (request.getIdentityBack() != null) {
            backpath2 = serviceimpl.uploadImage(request.getIdentityBack());
        }
        if (request.getRecent_photo() != null) {
            recentiamgepath = serviceimpl.uploadImage(request.getRecent_photo());
        }
        if (request.getRole() == null) {
            request.setRole("User");

        }
       
       // Integer regno = Integer.parseInt(request.getIdentitynumber());

        User user = new User(request.getFullName(),
                request.getMobileNumber(),
                request.getPassword(),
                request.getRole(),
                false,
                request.getAddress(),
                request.getNationalityidentity(),
                request.getIdentitynumber(),
                frontpath1,
                backpath2,
                request.getCitizenissueDate(),
                request.getCitizenissueplace(),
                recentiamgepath,
                false
        );

        String  u = serviceimpl.add(user);
        if(u ==null){
         
         response.setMessage("User is not   created "  );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setMessage(u);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
      
          
    }



//admin access only 
    @GetMapping("/admin/getallusers")
    public ResponseEntity<List<User>> getallUser() {

        return new ResponseEntity<>(serviceimpl.getAllUsers(), HttpStatus.OK);

    }
        @GetMapping("/user/getusers")
    public ResponseEntity<User> getaUser(@RequestHeader(value = "Authorization") String authorization) {
        String token = authorization.replaceAll("Bearer", " ").trim();
        System.out.print("token after trim" + token);
        String username = jwtservice.extractUsernameFromToken(token);

        return new ResponseEntity<>(serviceimpl.getUser(username), HttpStatus.OK);

    }

//    @GetMapping("/user/getuserbymobile")
//    public ResponseEntity<Response> getUserByMobile(@RequestParam("mobileNumber") String mobileNumber) {
//        User user = serviceimpl.getUserByMobileNumber(mobileNumber);
//        Response response = new Response();
//
//        if (user != null) {
//            response.setMessage("User exists.");
//            response.setUser(user);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            response.setMessage("User not found");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/user/mobileNumber")
    public ResponseEntity<UserResponse> getUserByMobile(@RequestParam("mobileNumber") String mobileNumber) {
        User user = serviceimpl.getUserByMobileNumber(mobileNumber);

        if (user != null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getFullName());
            userResponse.setAddress(user.getAddress());
            userResponse.setPhoneNumber(user.getMobileNumber());
            userResponse.setProfile(user.getRecent_photo());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping("/authenticate/getPasswordChange")
//    public ResponseEntity<Response> getUserMobile(@RequestParam("mobileNumber") String mobileNumber) {
//        User user = serviceimpl.getUserByMobileNumber(mobileNumber);
//        Response response = new Response();
//
//        if (user.getMobileNumber() != null) {
//            response.setMessage("User exists.");
//            response.setToken(jwtservice.generateToken(user.getMobileNumber()));
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else{
//            response.setMessage("User not found");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/authenticate/getPasswordChange")
    public ResponseEntity<Response> getUserMobile(@RequestParam("mobileNumber") String mobileNumber) {
        User user = serviceimpl.getUserByMobileNumber(mobileNumber);
        Response response = new Response();

        if (user != null) {
            response.setMessage("User exists.");
            response.setToken(jwtservice.generateToken(user.getMobileNumber()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }




    //everyone access
           @GetMapping("/authenticate/checkToken")
    public ResponseEntity<Boolean> checkToken(@RequestHeader(value = "Authorization") String authorization) {
        String token = authorization.replaceAll("Bearer", " ").trim();
        System.out.print("token after trim" + token);
        Boolean checked= jwtservice.isTokenExpired(token);

        return new ResponseEntity<>(checked, HttpStatus.OK);

    }
    @PutMapping("/authenticate/updatepassword/{id}")
    public ResponseEntity<Response> updatepasswordUser(@PathVariable("id") long id, @RequestParam("password") String pwd
    ) {
        Response respo = new Response();
        System.out.print("in controller");
        String msg = serviceimpl.updatePassword(id, pwd);
        System.out.print(msg);
        respo.setMessage(msg);

        return new ResponseEntity<>(respo, HttpStatus.OK);

    }
    //admin acces only
    @PutMapping("/admin/verify/{id}")
    public ResponseEntity<Response> updateVerification(@PathVariable("id") long id,@RequestParam("verify")Boolean b ){
        Response repo = new Response();
        String msg = serviceimpl.verifyUserAccount(id, b);
        repo.setMessage(msg);
           return new ResponseEntity<>(repo, HttpStatus.OK);
        
    }

       @PutMapping("/admin/suspendUser/{id}")
    public ResponseEntity<Response> suspendUserAccount(@PathVariable("id") long id,@RequestParam("suspend")Boolean b ){
        Response repo = new Response();
        String msg = serviceimpl.suspendUserAccount(id, b);
        repo.setMessage(msg);
           return new ResponseEntity<>(repo, HttpStatus.OK);
        
    }


    @PostMapping("user/{mobileNumber}/forgot-password")
    public ResponseEntity<ApiResponse> changePassword(
            @PathVariable("mobileNumber") String mobileNumber,
    @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        String newPassword = changePasswordRequest.getNewPassword();
        String confirmPassword = changePasswordRequest.getConfirmPassword();

        if (!newPassword.equals(confirmPassword)) {
            ApiResponse errorResponse = new ApiResponse("New password and confirm password do not match", false);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        User userDto = new User();
        userDto.setPassword(newPassword);

        try {
            User updatedUser = serviceimpl.forgetPassword(userDto, mobileNumber);
            ApiResponse response = new ApiResponse("Password changed successfully", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse("Failed to change password", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
