/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "verification")
    private boolean verfication;
    @Column(name = "address")
    private String address;
    @Column(name = "nationalityidentity")
    private String nationalityidentity;
    @Column(name = "identitynumber")
    private String identitynumber;
    @Column(name = "identity_front")
    private String identityFront;
    @Column(name = "identityBack")
    private String identityBack;
    @Column(name = "date_of_issue")
    private String citizenissueDate;

    @Column(name = "place_of_issue")
    private String citizenissueplace;
    @Column(name = "recent_photo")
    private String recent_photo;
    @Column(name = "is_suspended")
    private boolean issuspend;

    public boolean isIssuspend() {
        return issuspend;
    }

    public void setIssuspend(boolean issuspend) {
        this.issuspend = issuspend;
    }
    

    public String getRecent_photo() {
        return recent_photo;
    }

    public void setRecent_photo(String recent_photo) {
        this.recent_photo = recent_photo;
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
      
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getIdentityFront() {
        return identityFront;
    }

    public void setIdentityFront(String identityFront) {
        this.identityFront = identityFront;
    }

    public String getIdentityBack() {
        return identityBack;
    }

    public void setIdentityBack(String identityBack) {
        this.identityBack = identityBack;
    }
   


    public boolean isVerfication() {
        return verfication;
    }

    public void setVerfication(boolean verfication) {
        this.verfication = verfication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(Long id, String fullname, String mobileNumber, String password, String role) {
        this.id = id;
        this.fullName= fullname;

        this.mobileNumber = mobileNumber;
        this.password = password;
        this.role = role;
    }
    public User(String fullName, String mobileNumber, String password, String role, boolean verfication,
            String address, String nationalityidentity,
            String identitynumber, String identityFront, String identityBack,
            String citizenissueDate, String citizenissueplace, String recent_photo,boolean issuspend) {
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
        this.issuspend = issuspend ;
    }

  
    
    public User(User user){
         this.id = user.getId();
        this.fullName = user.getFullName();
        this.mobileNumber = user.getMobileNumber();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.verfication = user.isVerfication();
        this.address = user.getAddress();
        this.nationalityidentity = user.getNationalityidentity();
        this.identitynumber = user.getIdentitynumber();
        this.identityFront = user.getIdentityFront();
        this.identityBack = user.getIdentityBack();
        this.citizenissueDate = user.getCitizenissueDate();
        this.citizenissueplace = user.getCitizenissueplace();
        this.recent_photo = user.getRecent_photo();
        this.issuspend = user.isIssuspend();

    }
//
//    public User() {
//    }
//
//    @Override
//    public String toString() {
//        return "User{" + "id=" + id + ", fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", password=" + password + ", role=" + role + ", verfication=" + verfication + ", address=" + address + ", nationalityidentity=" + nationalityidentity + ", identitynumber=" + identitynumber + ", identityFront=" + identityFront + ", identityBack=" + identityBack + ", citizenissueDate=" + citizenissueDate + ", citizenissueplace=" + citizenissueplace + ", recent_photo=" + recent_photo + '}';
//    }

//    @OneToMany(mappedBy = "user")
//    private List<Notification> notification;
//
//
//    public List<Notification> getNotification() {
//        return notification;
//    }
//
//    public void setNotification(List<Notification> notification) {
//        this.notification = notification;
//    }
}
