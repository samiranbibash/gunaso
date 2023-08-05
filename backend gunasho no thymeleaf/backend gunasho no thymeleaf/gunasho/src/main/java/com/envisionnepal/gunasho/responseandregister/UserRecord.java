/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.responseandregister;

/**
 *
 * @author User
 */
public class UserRecord {
   private Long id ;
   String fullName; 
  
   String mobileNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

 
    
 

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public UserRecord(Long id, String fullName,String mobileNumber) {
        this.id = id;
        this.fullName = fullName;
       
      this.mobileNumber = mobileNumber;
    }
  
    
}
