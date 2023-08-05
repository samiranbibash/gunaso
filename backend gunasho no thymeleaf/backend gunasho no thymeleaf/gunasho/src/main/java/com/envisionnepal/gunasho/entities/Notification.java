//package com.envisionnepal.gunasho.entities;
//
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table
//@AllArgsConstructor
//@NoArgsConstructor
//public class Notification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long notif_id;
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
////    @ManyToOne
////    @JoinColumn(name = "user_id", referencedColumnName = "id")
////    private User user;
//
//
//
//}
