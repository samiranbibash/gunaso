/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.envisionnepal.gunasho.service;

import com.envisionnepal.gunasho.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author User
 */
@Service
public interface UserService {
    String add(User user);
    List<User> getAllUsers();
    void delete(String phone);
  User  getUser(String phone);
   public String updatePassword(Long id,String password);

    User forgetPassword(User userDto, String mobileNumber);

    public String verifyUserAccount(Long id, boolean status);
    public String suspendUserAccount(Long id,boolean status);

    User getUserByMobileNumber(String mobileNumber);
    
}
