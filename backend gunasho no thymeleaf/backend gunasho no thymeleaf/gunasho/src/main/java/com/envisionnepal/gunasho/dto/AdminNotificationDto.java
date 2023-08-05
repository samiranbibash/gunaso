package com.envisionnepal.gunasho.dto;

import java.util.Date;

import com.envisionnepal.gunasho.entities.Gunasho;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AdminNotificationDto {
    private Integer adminNotificationId;


    private String title;

    private String information;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;

    private boolean seenStatus;

    private Long gunashoId;



}