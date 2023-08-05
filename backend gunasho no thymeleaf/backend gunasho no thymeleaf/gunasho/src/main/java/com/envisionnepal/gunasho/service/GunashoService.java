/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.envisionnepal.gunasho.service;

import com.envisionnepal.gunasho.entities.Gunasho;
import com.envisionnepal.gunasho.responseandregister.GunashoRegister;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author User
 */
@Service
public interface GunashoService {
//      Gunasho add(Gunasho gunasho);

//    GunashoRegister add(GunashoRegister gunashoRegister);

    Gunasho add(Gunasho gunasho);

    GunashoRegister createGunasho(GunashoRegister gunashoRegister);

    List<Gunasho> getAllGunasho();
    void delete(Long id);
  List<Gunasho> getGunashobyuser(String username);
   public String updateiscomplete(Long id,String statusnow);
   public String updateGunasho(Long id , GunashoRegister gn);

//    Gunasho add(Gunasho gunasho);
}
