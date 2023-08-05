//package com.envisionnepal.gunasho.dto;
//
//
//import java.util.Date;
//
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class NotificationDto {
//
//
//    private Integer notif_id;
//
//    private String title;
//
//    private String content;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateAndTime;
//
//    private boolean seen;
//
//
//
//
//}