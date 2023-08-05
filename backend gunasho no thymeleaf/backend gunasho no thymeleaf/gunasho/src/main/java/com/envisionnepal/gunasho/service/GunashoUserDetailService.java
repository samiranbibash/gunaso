/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.service;

import com.envisionnepal.gunasho.entities.GunashoUserDetail;
import com.envisionnepal.gunasho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class GunashoUserDetailService implements UserDetailsService{
      @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMobileNumber(username)
                .map(GunashoUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
    
}
