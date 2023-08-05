/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name ="gunasho_table")
public class Gunasho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gunasho_ID")
    private Long gunashoId;
    @Column(name = "user_ID")
    private Long userId;
    @Column(name = "category")
    private String category;
    @Column(name = "sub_category")
    private String subcategory;
    @Column(name = "location")
    private String location;
    @Column(name = "incident")
    private String incident;
    @Column(name = "impact")
    private String impact;
    @Column(name = "detail")
    private String details;
    @Column(name = "date_applied")
    private LocalDateTime dateapplied;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "admin_message")
    private String adminmessage;
    @Column(name = "iscompleted")
    private String iscompleted;
    @Column(name = "date_completed")
    private Date datecompleted;
    @Column(name = "assigned_to")
    private String assignedto;
    @OneToMany(mappedBy = "gunasho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminNotification> adminNotifications;


    public String getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
    }



    public Date getDatecompleted() {
        return datecompleted;
    }

    public void setDatecompleted(Date datecompleted) {
        this.datecompleted = datecompleted;
    }

    public Long getGunashoId() {
        return gunashoId;
    }

    public void setGunashoId(Long gunashoId) {
        this.gunashoId = gunashoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getDateapplied() {
        return dateapplied;
    }

    public void setDateapplied(LocalDateTime dateapplied) {
        this.dateapplied = dateapplied;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public void setIscompleted(String completedStatus) {
        this.iscompleted = completedStatus;
    }

    public Gunasho(Long userId, String category, String subcategory, String location, String incident, String impact, String details, LocalDateTime dateapplied, String photoUrl, String iscompleted) {
        this.userId = userId;
        this.category = category;
        this.subcategory = subcategory;
        this.location = location;
        this.incident = incident;
        this.impact = impact;
        this.details = details;
        this.dateapplied = dateapplied;
        this.photoUrl = photoUrl;

        this.iscompleted = iscompleted;
    }

    public Gunasho(Gunasho gunaso) {
        this.gunashoId = gunaso.getGunashoId();
        this.userId = gunaso.getUserId();
        this.category = gunaso.getCategory();
        this.subcategory = gunaso.getSubcategory();
        this.location = gunaso.getLocation();
        this.incident = gunaso.getIncident();
        this.impact = gunaso.getImpact();
        this.details = gunaso.getDetails();
        this.dateapplied = gunaso.getDateapplied();
        this.photoUrl = gunaso.getPhotoUrl();
        this.adminmessage = gunaso.getAdminmessage();
        this.iscompleted = gunaso.getIscompleted();
    }

    public Gunasho() {
    }



}
