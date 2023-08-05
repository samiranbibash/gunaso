/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.envisionnepal.gunasho.repository;

import com.envisionnepal.gunasho.entities.Gunasho;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface GuanashoRepository extends JpaRepository<Gunasho, Long>{
    @Transactional
    void deleteByGunashoId(Long id);
   List<Gunasho> findByUserId(Long userID);
    Optional<Gunasho> findByGunashoId(Long id);
     Optional<Gunasho> findByIscompleted(String status);

    
}
