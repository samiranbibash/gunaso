/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.serviceimpl;

import com.envisionnepal.gunasho.entities.Gunasho;
import com.envisionnepal.gunasho.entities.User;
import com.envisionnepal.gunasho.responseandregister.GunashoRegister;
import com.envisionnepal.gunasho.repository.GuanashoRepository;
import com.envisionnepal.gunasho.repository.UserRepository;
import com.envisionnepal.gunasho.service.GunashoService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class GunashoServiceImpl implements GunashoService {
    GuanashoRepository repo;
    UserRepository userepo;

    ModelMapper modelMapper;

    @Autowired
    public GunashoServiceImpl(GuanashoRepository repo, UserRepository userepo, ModelMapper modelMapper) {
        this.repo = repo;
        this.userepo = userepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Gunasho add(Gunasho gunasho) {
        return repo.save(gunasho);
    }


//    @Override
//    public Gunasho add(Gunasho gunasho) {
//        return repo.save(gunasho);
//    }
//
//    @Override
//    public GunashoRegister add(GunashoRegister gunashoRegister) {
//        Gunasho gunasho=this.dtoToUser(gunashoRegister);
//        Gunasho savedGunasho=this.repo.save(gunasho);
//        return this.userToDto(savedGunasho);
//    }

    @Override
    public GunashoRegister createGunasho(GunashoRegister gunashoRegister) {

        LocalDateTime currentDateAndTime = LocalDateTime.now();
//        gunashoRegister.setDateapplied(currentDateAndTime);

        Gunasho gunasho = this.dtoToUser(gunashoRegister);


        Gunasho savedGunasho = this.repo.save(gunasho);
        return this.userToDto(savedGunasho);
    }

    public GunashoRegister userToDto(Gunasho gunasho) {
        GunashoRegister gunashoRegister = this.modelMapper.map(gunasho, GunashoRegister.class);
        return gunashoRegister;
    }

    public Gunasho dtoToUser(GunashoRegister userDto) {
        Gunasho user = this.modelMapper.map(userDto, Gunasho.class);
        return user;
    }

    @Override
    public List<Gunasho> getAllGunasho() {
         return repo.findAll().stream().map(gunasho-> new Gunasho(gunasho)).collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
      repo.deleteByGunashoId(id);  
 
   }

    @Override
    public List<Gunasho> getGunashobyuser(String username) {
        Optional<User> u = userepo.findByMobileNumber(username);
        if(u.isPresent()){
         
       return repo.findByUserId(u.get().getId()).stream().map(gunasho-> new Gunasho(gunasho)).collect(Collectors.toList());

        

        }else{
            return null;
        }
       
    }

    @Override
    public String updateiscomplete(Long id, String statusnow) {
             Optional<Gunasho> theuser = repo.findByGunashoId(id);
             
        if (theuser.isPresent()) {
            Gunasho existing = theuser.get();
            existing.setIscompleted(statusnow);
           
             
             // Save the updated entity
             System.out.print("before  updating ");
        Gunasho  u =     repo.save(existing);
           System.out.print("after  updating :"+u);
       
          if(u !=null){
       
           return "Upload success";
          }else{
              return "Upload Failed ";
          }
        } else 
        {
           return "Fail to get User";
        }

    }

    public void updateCompletedStatusInWorkOrder(Long gunashoId, String iscompleted) {
        List<String> validStatuses = Arrays.asList("1", "2", "3", "4","5");
        if (!validStatuses.contains(iscompleted)) {
            throw new IllegalArgumentException("Invalid completed status: " + iscompleted);
        }

        Gunasho gunasho = repo.findById(gunashoId)
                .orElseThrow(null);

        String currentStatus = gunasho.getIscompleted();

        int currentStatusIndex = validStatuses.indexOf(currentStatus);
        int newStatusIndex = validStatuses.indexOf(iscompleted);
        if (newStatusIndex <= currentStatusIndex) {
            throw new IllegalArgumentException("Invalid completed status order: " + iscompleted);
        }

        for (int i = currentStatusIndex + 1; i < newStatusIndex; i++) {
            String skippedStatus = validStatuses.get(i);
            if (!skippedStatus.equals(currentStatus)) {
                throw new IllegalArgumentException("Skipped completed status: " + skippedStatus);
            }
        }

        gunasho.setIscompleted(iscompleted);

        repo.save(gunasho);
    }



//    public String updateiscompletedate(Long id) {
//             Optional<Gunasho> theuser = repo.findByGunashoId(id);
//              LocalDate ldate = LocalDate.now();
//        Date date = Date.valueOf(ldate);
//        if (theuser.isPresent()) {
//            Gunasho existing = theuser.get();
//            existing.setDatecompleted(date);
//
//
//             // Save the updated entity
//             System.out.print("before  updating ");
//        Gunasho  u =     repo.save(existing);
//           System.out.print("after  updating :"+u);
//
//          if(u !=null){
//
//           return "Upload success";
//          }else{
//              return "Upload Failed ";
//          }
//        } else
//        {
//           return "Fail to get User";
//        }
//           }
   
    @Override
    public String updateGunasho(Long id, GunashoRegister gn) {
          Optional<Gunasho> theuser = repo.findByGunashoId(id);
           if (theuser.isPresent()) {
             Gunasho existing = theuser.get();
             
           existing.setCategory((gn.getCategory() != null) ? gn.getCategory(): existing.getCategory());
           existing.setSubcategory((gn.getSubcategory() != null) ? gn.getSubcategory(): existing.getSubcategory());
           existing.setImpact((gn.getImpact() != null) ? gn.getImpact(): existing.getImpact());
           existing.setDetails((gn.getDetails()!= null) ? gn.getDetails(): existing.getDetails());
           existing.setLocation((gn.getLocation() != null) ? gn.getLocation(): existing.getLocation());
           existing.setIncident((gn.getIncident()!= null) ? gn.getIncident(): existing.getIncident());
        
           existing.setAdminmessage((gn.getAdminmessage()!= null) ? gn.getAdminmessage(): existing.getAdminmessage());
           existing.setIscompleted((gn.getIscompleted()!= null) ? gn.getIscompleted(): existing.getIscompleted());
             existing.setAssignedto((gn.getAssignedto()!= null) ? gn.getAssignedto(): existing.getAssignedto());
        
            Gunasho  u =     repo.save(existing);
            if(u !=null){
       
         return "Update success";
          }else{
              return "Update Failed ";}
          
           }else{
                 return "Failed to find User";
           }
       
   
      
    }

}
