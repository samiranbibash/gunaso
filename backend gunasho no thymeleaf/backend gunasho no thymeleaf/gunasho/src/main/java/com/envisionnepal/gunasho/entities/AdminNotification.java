package com.envisionnepal.gunasho.entities;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AdminNotification")
public class AdminNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminNotificationId;


    private String title;

    private String information;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateAndTime;


    private boolean seenStatus;

//    @ManyToOne
//    @JoinColumn(name = "adminId")
//    private Admin admin;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gunasho_id", referencedColumnName = "gunasho_id")
    private Gunasho gunasho;
}