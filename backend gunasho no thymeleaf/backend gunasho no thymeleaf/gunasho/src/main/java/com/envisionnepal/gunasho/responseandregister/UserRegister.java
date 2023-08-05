/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.responseandregister;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
public class UserRegister {
   private String fullName;
    private String mobileNumber;
   private String password ;
   private String role ;
   private boolean verfication ; 
  private String address;
   private String nationalityidentity;
  private String identitynumber; 
 private MultipartFile identityFront;
  private MultipartFile identityBack;
 private  String citizenissueDate;
 private  String citizenissueplace;
private  MultipartFile recent_photo;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isVerfication() {
        return verfication;
    }

    public void setVerfication(boolean verfication) {
        this.verfication = verfication;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalityidentity() {
        return nationalityidentity;
    }

    public void setNationalityidentity(String nationalityidentity) {
        this.nationalityidentity = nationalityidentity;
    }

    public String getIdentitynumber() {
        return identitynumber;
    }

    public void setIdentitynumber(String identitynumber) {
        this.identitynumber = identitynumber;
    }

   

    public MultipartFile getIdentityFront() {
        return identityFront;
    }

    public void setIdentityFront(MultipartFile identityFront) {
        this.identityFront = identityFront;
    }

    public MultipartFile getIdentityBack() {
        return identityBack;
    }

    public void setIdentityBack(MultipartFile identityBack) {
        this.identityBack = identityBack;
    }

    public String getCitizenissueDate() {
        return citizenissueDate;
    }

    public void setCitizenissueDate(String citizenissueDate) {
        this.citizenissueDate = citizenissueDate;
    }

    public String getCitizenissueplace() {
        return citizenissueplace;
    }

    public void setCitizenissueplace(String citizenissueplace) {
        this.citizenissueplace = citizenissueplace;
    }

    public MultipartFile getRecent_photo() {
        return recent_photo;
    }

    public void setRecent_photo(MultipartFile recent_photo) {
        this.recent_photo = recent_photo;
    }

    public UserRegister(String fullName, String mobileNumber, String password, String role, boolean verfication, String address, String nationalityidentity, String identitynumber, MultipartFile identityFront, MultipartFile identityBack, String citizenissueDate, String citizenissueplace, MultipartFile recent_photo) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.role = role;
        this.verfication = verfication;
        this.address = address;
        this.nationalityidentity = nationalityidentity;
        this.identitynumber = identitynumber;
        this.identityFront = identityFront;
        this.identityBack = identityBack;
        this.citizenissueDate = citizenissueDate;
        this.citizenissueplace = citizenissueplace;
        this.recent_photo = recent_photo;
    }

    public UserRegister() {
    }
    


}
