/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.envisionnepal.gunasho.repository;

import com.envisionnepal.gunasho.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByMobileNumber(String mobileno);

    User findByPassword(String password);
    void deleteByMobileNumber(String mobileno);
}

