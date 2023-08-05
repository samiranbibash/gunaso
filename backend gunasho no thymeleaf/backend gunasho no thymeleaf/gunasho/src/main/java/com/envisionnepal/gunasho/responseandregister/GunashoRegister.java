/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.responseandregister;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 *
 * @author User
 */
@Getter
@Setter
public class GunashoRegister {
    private String category;
    private String subcategory;
    private String location;
    private String incident;
    private String impact;
    private String details;
    private MultipartFile photoUrl;
    private String adminmessage;
    private String iscompleted;
    private String assignedto;

    private LocalDateTime dateapplied;

    public String getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
    }



    public String getAdminmessage() {
        return adminmessage;
    }

    public void setAdminmessage(String adminmessage) {
        this.adminmessage = adminmessage;
    }

    public String getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(String iscompleted) {
        this.iscompleted = iscompleted;
    }






    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public MultipartFile getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(MultipartFile photoUrl) {
        this.photoUrl = photoUrl;
    }

    public GunashoRegister(String category, String subcategory, String location, String incident, String impact, String details, MultipartFile photoUrl, String adminmessage, String iscompleted) {
        this.category = category;
        this.subcategory = subcategory;
        this.location = location;
        this.incident = incident;
        this.impact = impact;
        this.details = details;
        this.photoUrl = photoUrl;
        this.adminmessage = adminmessage;
        this.iscompleted = iscompleted;
    }


    public GunashoRegister( String category, String subcategory, String location, String incident, String impact, String details, MultipartFile photoUrl) {

        this.category = category;
        this.subcategory = subcategory;
        this.location = location;
        this.incident = incident;
        this.impact = impact;
        this.details = details;
        this.photoUrl = photoUrl;
    }

    public GunashoRegister() {
    }


}

